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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import open.schoolmanagement.contacts.importantcontactsservice.ImportantContactsServiceApplication;
import open.schoolmanagement.contacts.importantcontactsservice.domain.Contact;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImportantContactsServiceApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ContactServiceTest {

  @Autowired
  private ContactService contactService;

  @Before
  public void beforeTestCase() {
    initMocks(this);
  }

  @Test
  public void find_all_contacts_on_empty_database_returns_empty_list() {
    Collection<Contact> contacts = contactService.readAllContacts();

    assertThat(contacts.size()).isEqualTo(0);
  }

  @Test
  public void find_contact_on_empty_database_returns_empty_optional() {
    Optional<Contact> contact = contactService.readContact(2L);

    assertThat(contact).isEmpty();
  }

  @Test
  public void create_contact_is_successful() throws Exception {
    Contact createdContact = contactService.createContact(createFirstContactBuilder())
        .orElseThrow(Exception::new);
    Collection<Contact> contacts = contactService.readAllContacts();

    Contact foundContact = contacts
        .stream()
        .filter(createdContact::equals)
        .findFirst()
        .orElseThrow(Exception::new);
  }

  @Test
  @Transactional
  public void add_related_contact_is_successful() throws Exception {
    String relationName = "Spouse";
    Long createdContact1Id = saveContact(createFirstContactBuilder());
    Long createdContact2Id = saveContact(createSecondContactBuilder());

    contactService.addRelatedContact(createdContact1Id, relationName, createdContact2Id);

    Contact lookedUpContact = contactService.readContact(createdContact1Id)
        .orElseThrow(Exception::new);

    lookedUpContact
        .getRelatedContacts()
        .stream()
        .filter(relation ->
            Objects.equals(relation.relationName, relationName)
                && Objects.equals(relation.relatedContact.getContactId(), createdContact2Id))
        .findFirst()
        .orElseThrow(Exception::new);
  }

  @Test
  @Transactional
  public void remove_related_contact_is_successful() throws Exception {
    String relationName = "Spouse";
    Long createdContact1Id = saveContact(createFirstContactBuilder());
    Long createdContact2Id = saveContact(createSecondContactBuilder());

    contactService.addRelatedContact(createdContact1Id, relationName, createdContact2Id);
    contactService.removeRelatedContact(createdContact1Id, relationName);

    Contact lookedUpContact = contactService.readContact(createdContact1Id)
        .orElseThrow(Exception::new);

    assertThat(lookedUpContact.getRelatedContacts().size()).isEqualTo(0);
  }

  @Test
  @Transactional
  public void add_email_address_is_successful() throws Exception {
    String category = "Private";
    String email = "john.doe@home.com";
    Long createdContact1Id = saveContact(createFirstContactBuilder());

    contactService.addEmailAddress(createdContact1Id, category, email);

    Contact lookedUpContact = contactService.readContact(createdContact1Id)
        .orElseThrow(Exception::new);

    lookedUpContact
        .getEmailAddresses()
        .stream()
        .filter(emailAddress ->
            Objects.equals(emailAddress.category, category)
                && Objects.equals(emailAddress.emailAddress, email))
        .findFirst()
        .orElseThrow(Exception::new);
  }

  @Test
  @Transactional
  public void remove_email_address_is_successful() throws Exception {
    String category = "Private";
    String email = "john.doe@home.com";
    Long createdContact1Id = saveContact(createFirstContactBuilder());

    contactService.addEmailAddress(createdContact1Id, category, email);
    contactService.removeEmailAddress(createdContact1Id, category);

    Contact lookedUpContact = contactService.readContact(createdContact1Id)
        .orElseThrow(Exception::new);

    assertThat(lookedUpContact.getEmailAddresses().size()).isEqualTo(0);
  }

  @Test
  @Transactional
  public void add_phone_numner_is_successful() throws Exception {
    String category = "Private";
    String phone = "123456789";
    Long createdContact1Id = saveContact(createFirstContactBuilder());

    contactService.addPhoneNumber(createdContact1Id, category, phone);

    Contact lookedUpContact = contactService.readContact(createdContact1Id)
        .orElseThrow(Exception::new);

    lookedUpContact
        .getPhoneNumbers()
        .stream()
        .filter(number ->
            Objects.equals(number.category, category)
                && Objects.equals(number.phoneNumber, phone))
        .findFirst()
        .orElseThrow(Exception::new);
  }

  @Test
  @Transactional
  public void remove_phone_numner_is_successful() throws Exception {
    String category = "Private";
    String phone = "123456789";
    Long createdContact1Id = saveContact(createFirstContactBuilder());

    contactService.addPhoneNumber(createdContact1Id, category, phone);
    contactService.removePhoneNumber(createdContact1Id, category);

    Contact lookedUpContact = contactService.readContact(createdContact1Id)
        .orElseThrow(Exception::new);

    assertThat(lookedUpContact.getPhoneNumbers().size()).isEqualTo(0);
  }

  @Test
  @Transactional
  public void delete_contact_is_successful() throws Exception {
    Long createdContact1Id = saveContact(createFirstContactBuilder());
    Long createdContact2Id = saveContact(createSecondContactBuilder());

    assertThat(contactService.readAllContacts().size()).isEqualTo(2);

    contactService.deleteContact(createdContact1Id);

    assertThat(contactService.readAllContacts().size()).isEqualTo(1);
    assertThat(contactService.readContact(createdContact1Id)).isEmpty();
  }


  private Long saveContact(Contact.ContactBuilder contactBuilder) throws Exception {
    return contactService.createContact(contactBuilder)
        .orElseThrow(Exception::new)
        .getContactId();
  }

  private Contact.ContactBuilder createFirstContactBuilder() {
    return Contact
        .builder()
        .withFirstname("Max")
        .withLastname("Mustermann")
        .withAddressline1("Musterstraße 1")
        .withCity("Musterstadt")
        .withZipCode("12345");
  }

  private Contact.ContactBuilder createSecondContactBuilder() {
    return Contact
        .builder()
        .withFirstname("Eva")
        .withLastname("Zwerg")
        .withAddressline1("Riesenstraße 1")
        .withCity("Trollheim")
        .withZipCode("54321");
  }
}

