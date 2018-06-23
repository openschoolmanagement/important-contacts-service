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

package open.schoolmanagement.contacts.importantcontactsservice.service.exception;

/**
 * This exception is thrown, if a contact wasn't found.
 */
public class ContactNotFoundException extends Exception {
  private Long contactId;

  /**
   * Instantiates a new ContactNotFoundException.
   *
   * @param contactId the contact id
   */
  public ContactNotFoundException(Long contactId) {
    super(String.format("Contqct with ID %s not found", contactId));
    this.contactId = contactId;
  }

  /**
   * Gets the contactId.
   *
   * @return the contactId
   */
  public Long getContactId() {
    return contactId;
  }
}
