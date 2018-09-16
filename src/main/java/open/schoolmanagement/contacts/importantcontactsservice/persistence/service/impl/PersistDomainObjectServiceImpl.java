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
import java.util.stream.Collectors;
import java.util.stream.Stream;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.events.ContactCreated;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.events.Event;
import open.schoolmanagement.contacts.importantcontactsservice.persistence.service.PersistContactService;
import open.schoolmanagement.contacts.importantcontactsservice.persistence.service.PersistDomainObjectService;
import org.springframework.stereotype.Service;

@Service("persistDomainObjectService")
class PersistDomainObjectServiceImpl extends PersistDomainObjectService {
  private final PersistContactService persistContactService;

  private final Map<Class<? extends Event>, PersistDomainObjectService> dispatcherMap;

  PersistDomainObjectServiceImpl(PersistContactService persistContactService) {
    this.persistContactService = persistContactService;

    dispatcherMap = Collections.unmodifiableMap(
        Stream
            .of(
                new SimpleEntry<>(ContactCreated.class, persistContactService))
            .collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));
  }

  @Override
  public void persistDomainObjectFromEvent(Event event) {
    dispatcherMap.get(event.getClass()).persistDomainObjectFromEvent(event);
  }
}
