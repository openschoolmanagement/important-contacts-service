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

package open.schoolmanagement.contacts.importantcontactsservice.web.rest.v1.controller.cqrs.contact.command;

import static open.schoolmanagement.contacts.importantcontactsservice.config.Constants.API_V1_IMPORTANT_CONTACTS_URI;
import static open.schoolmanagement.contacts.importantcontactsservice.config.Constants.WRITE_URI;

import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Command for creating a contact.
 */
@AllArgsConstructor
@NoArgsConstructor
public class CreateContact implements Serializable {
  /**
   * URI for the command
   */
  public static final String URI = API_V1_IMPORTANT_CONTACTS_URI + WRITE_URI + "createcontact";

  /**
   * The id for the new contact.
   */
  @Getter
  @Setter
  private UUID id;
}
