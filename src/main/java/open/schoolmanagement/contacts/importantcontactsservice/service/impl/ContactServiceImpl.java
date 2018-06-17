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

package open.schoolmanagement.contacts.importantcontactsservice.service.impl;

import java.util.Collection;
import java.util.Optional;
import open.schoolmanagement.contacts.importantcontactsservice.domain.Contact;
import open.schoolmanagement.contacts.importantcontactsservice.domain.Contact.ContactBuilder;
import open.schoolmanagement.contacts.importantcontactsservice.repository.ContactRepository;
import open.schoolmanagement.contacts.importantcontactsservice.service.ContactService;
import open.schoolmanagement.contacts.importantcontactsservice.service.exception.ContactNotFoundException;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
  private ContactRepository contactRepository;

  @Autowired
  private ContactServiceImpl(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }

  @Override
  public Collection<Contact> readAllContacts() {
    return IteratorUtils.toList(contactRepository.findAll().iterator());
  }

  @Override
  public Optional<Contact> readContact(Long contactId) {
    return contactRepository.findById(contactId);
  }

  @Override
  public Optional<Contact> createContact(ContactBuilder contactBuilder) {
    return Optional.ofNullable(contactRepository.save(contactBuilder.build()));
  }

  @Override
  public Optional<Contact> updateContact(Contact contact) {
    return Optional.ofNullable(contactRepository.save(contact));
  }

  @Override
  public Optional<Contact> addRelatedContact(
      Long contactId, String relationName, Long relatedContactId) throws ContactNotFoundException {

    Contact contact = readContact(contactId)
        .orElseThrow(() -> new ContactNotFoundException(contactId));
    Contact relatedContact = readContact(relatedContactId)
        .orElseThrow(() -> new ContactNotFoundException(relatedContactId));

    contact.addRelatedContact(relatedContact, relationName);

    return updateContact(contact);
  }

  @Override
  public Optional<Contact> removeRelatedContact(Long contactId, String relationName)
      throws ContactNotFoundException {

    Contact contact = readContact(contactId)
        .orElseThrow(() -> new ContactNotFoundException(contactId));

    contact.removeRelatedContact(relationName);

    return updateContact(contact);
  }

  @Override
  public Optional<Contact> addEmailAddress(Long contactId, String category, String emailAddress)
      throws ContactNotFoundException {

    Contact contact = readContact(contactId)
        .orElseThrow(() -> new ContactNotFoundException(contactId));

    contact.addEmailAdress(emailAddress, category);
    return updateContact(contact);
  }

  @Override
  public Optional<Contact> removeEmailAddress(Long contactId, String category)
      throws ContactNotFoundException {

    Contact contact = readContact(contactId)
        .orElseThrow(() -> new ContactNotFoundException(contactId));

    contact.removeEmailAddressbyCategory(category);
    return updateContact(contact);
  }

  @Override
  public Optional<Contact> addPhoneNumber(Long contactId, String category, String phoneNumber)
      throws ContactNotFoundException {

    Contact contact = readContact(contactId)
        .orElseThrow(() -> new ContactNotFoundException(contactId));

    contact.addPhoneNumber(phoneNumber, category);
    return updateContact(contact);
  }

  @Override
  public Optional<Contact> removePhoneNumber(Long contactId, String category)
      throws ContactNotFoundException {

    Contact contact = readContact(contactId)
        .orElseThrow(() -> new ContactNotFoundException(contactId));

    contact.removePhoneNumberByCategory(category);
    return updateContact(contact);
  }

  @Override
  public void deleteContact(Long contactId) {
    contactRepository.deleteById(contactId);
  }
}
