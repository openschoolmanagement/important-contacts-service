/*
   Copyright 2018 Open School Management

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package open.schoolmanagement.contacts.importantcontactsservice.persistence.service.impl;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import open.schoolmanagement.contacts.importantcontactsservice.domain.BaseEntity;
import open.schoolmanagement.contacts.importantcontactsservice.domain.Contact;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.events.ContactCreated;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.events.Event;
import open.schoolmanagement.contacts.importantcontactsservice.persistence.repository.ContactRepository;
import open.schoolmanagement.contacts.importantcontactsservice.persistence.service.PersistContactService;
import org.springframework.stereotype.Service;

@Service("persistContactService")
class PersistContactServiceImpl extends PersistContactService {
  private final ContactRepository contactRepository;
  private final Map<Class<? extends Event>, Function<Event, Contact>> dispatcherMap;

  PersistContactServiceImpl(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;

    dispatcherMap = Collections.unmodifiableMap(
        Stream
            .of(
                new SimpleEntry<Class<? extends Event>, Function<Event, Contact>>(
                    ContactCreated.class, this::createContact))
            .collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));
  }

  @Override
  public BaseEntity persistDomainObjectFromEvent(Event event) {
    return dispatcherMap.get(event.getClass()).apply(event);
  }

  Contact createContact(Event event) {
    ContactCreated contactCreatedEvent = ContactCreated.class.cast(event);
    Contact contact = Contact.builder().id(event.getAggregateId()).build();

    return contactRepository.save(contact);
  }
}
