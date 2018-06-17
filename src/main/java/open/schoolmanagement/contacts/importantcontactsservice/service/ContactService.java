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

package open.schoolmanagement.contacts.importantcontactsservice.service;

import java.util.Collection;
import java.util.Optional;
import open.schoolmanagement.contacts.importantcontactsservice.domain.Contact;
import open.schoolmanagement.contacts.importantcontactsservice.domain.Contact.ContactBuilder;
import open.schoolmanagement.contacts.importantcontactsservice.service.exception.ContactNotFoundException;

/**
 * Interface for the contact service.
 */
public interface ContactService {
  /**
   * Read all contacts.
   *
   * @return collection with all contacts
   */
  Collection<Contact> readAllContacts();

  /**
   * Read contact.
   *
   * @param contactId the contact id
   * @return an optional that contains the contact, or empty in case of an error
   */
  Optional<Contact> readContact(Long contactId);

  /**
   * Create a contact.
   *
   * @param contactBuilder the contact builder
   * @return an optional that contains the contact, or empty in case of an error
   */
  Optional<Contact> createContact(ContactBuilder contactBuilder);

  /**
   * Update a contact.
   *
   * @param contact the contact
   * @return an optional that contains the contact, or empty in case of an error
   */
  Optional<Contact> updateContact(Contact contact);

  /**
   * Add a related contact.
   *
   * @param contactId        the contact id
   * @param relationName     the relation name
   * @param relatedContactId the related contact id
   * @return an optional that contains the contact, or empty in case of an error
   * @throws ContactNotFoundException if any of the contacts where not found
   */
  Optional<Contact> addRelatedContact(Long contactId, String relationName, Long relatedContactId)
      throws ContactNotFoundException;

  /**
   * Remove related contact.
   *
   * @param contactId    the contact id
   * @param relationName the relation name
   * @return an optional that contains the contact, or empty in case of an error
   */
  Optional<Contact> removeRelatedContact(Long contactId, String relationName) throws ContactNotFoundException;

  /**
   * Add email address.
   *
   * @param contactId    the contact id
   * @param category     the category
   * @param emailAddress the email address
   * @return an optional that contains the contact, or empty in case of an error
   */
  Optional<Contact> addEmailAddress(Long contactId, String category, String emailAddress) throws ContactNotFoundException;

  /**
   * Remove email address.
   *
   * @param contactId the contact id
   * @param category  the category
   * @return an optional that contains the contact, or empty in case of an error
   */
  Optional<Contact> removeEmailAddress(Long contactId, String category) throws ContactNotFoundException;

  /**
   * Add phone number.
   *
   * @param contactId   the contact id
   * @param category    the category
   * @param phoneNumber the phone number
   * @return an optional that contains the contact, or empty in case of an error
   */
  Optional<Contact> addPhoneNumber(Long contactId, String category, String phoneNumber) throws ContactNotFoundException;

  /**
   * Remove phone number.
   *
   * @param contactId the contact id
   * @param category  the category
   * @return an optional that contains the contact, or empty in case of an error
   */
  Optional<Contact> removePhoneNumber(Long contactId, String category) throws ContactNotFoundException;

  /**
   * Delete contact.
   *
   * @param contactId the contact id
   */
  void deleteContact(Long contactId);
}
