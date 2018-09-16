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

import java.util.Optional;
import open.schoolmanagement.contacts.importantcontactsservice.domain.Contact;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.events.Event;
import open.schoolmanagement.contacts.importantcontactsservice.persistence.service.PersistContactService;
import org.springframework.stereotype.Service;

@Service("persistContactService")
class PersistContactServiceImpl extends PersistContactService {
  @Override
  public Optional<Contact> save(Contact contact) {
    return Optional.empty();
  }

  @Override
  public void persistDomainObjectFromEvent(Event event) {

  }
}
