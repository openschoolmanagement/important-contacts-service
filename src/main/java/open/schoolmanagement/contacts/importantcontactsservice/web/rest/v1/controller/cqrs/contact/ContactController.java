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

import java.util.Optional;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.events.ContactCreated;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.events.Event;
import open.schoolmanagement.contacts.importantcontactsservice.web.rest.exception.DuplicateKeyException;
import open.schoolmanagement.contacts.importantcontactsservice.web.rest.exception.InternalServerError;
import open.schoolmanagement.contacts.importantcontactsservice.web.rest.exception.NoKeyException;
import open.schoolmanagement.contacts.importantcontactsservice.web.rest.exception.ServerRuntimeException;
import open.schoolmanagement.contacts.importantcontactsservice.web.rest.v1.controller.cqrs.contact.command.CreateContact;
import open.schoolmanagement.contacts.importantcontactsservice.web.rest.v1.controller.cqrs.contact.response.EventId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
  public ResponseEntity<EventId> createContact(
      @RequestBody(required = true) CreateContact createContact) {

    try {
      Event contactCreated =
          new ContactCreated(
              Optional.ofNullable(createContact)
                  .map(CreateContact::getId)
                  .map(usedKeyService::validateKeyIsNotUsed)
                  .map(optionalKey ->
                      optionalKey.orElseThrow(DuplicateKeyException::duplicateContactId))
                  .orElseThrow(NoKeyException::noContactId));

      usedKeyService.markKeyAsUsed(createContact.getId());
      eventSource.accept(contactCreated);

      return ResponseEntity
          .accepted()
          .body(EventId
              .builder()
              .eventId(contactCreated.getEventId())
              .build());
    } catch(ServerRuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new InternalServerError("Error processing the command >CreateContact<. " +
          "Please contact the administrator.", ex);
    }
  }
}
