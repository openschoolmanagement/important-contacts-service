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

package open.schoolmanagement.contacts.importantcontactsservice.web.rest.v1.controller.cqrs.contact;

import java.time.Duration;
import java.util.UUID;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.events.ContactCreated;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.events.Event;
import open.schoolmanagement.contacts.importantcontactsservice.web.rest.v1.controller.cqrs.contact.commands.CreateContact;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * REST Controller the provides the Contact related commands.
 */
@RestController
public class ContactController extends CommandController {

  /**
   * Handler for the CreateContact command.
   *
   * @param createContact the create contact command
   */
  @RequestMapping(method = RequestMethod.POST, path = CreateContact.URI)
  public ResponseEntity<UUID> createContact(
      @RequestBody(required = true) CreateContact createContact) {

    return Mono
        .just(createContact)
        .map(CreateContact::getId)
        .map(usedKeyService::validateKeyIsNotUsed)
        .map(keyOptional -> keyOptional.orElseThrow(RuntimeException::new))
        .doOnNext(usedKeyService::markKeyAsUsed)
        .map(ContactCreated::new)
        .doOnNext(eventSource::accept)
        .map(Event::getEventId)
        .map(ResponseEntity.accepted()::body)
        .block(Duration.ZERO);
  }
}
