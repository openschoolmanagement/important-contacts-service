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
    Contact createdContact1 = contactService.createContact(createFirstContactBuilder())
        .orElseThrow(Exception::new);
    Contact createdContact2 = contactService.createContact(createSecondContactBuilder())
        .orElseThrow(Exception::new);

    contactService.addRelatedContact(
        createdContact1.getContactId(), relationName, createdContact2.getContactId());

    Contact lookedUpContact = contactService.readContact(createdContact1.getContactId())
        .orElseThrow(Exception::new);

    lookedUpContact
        .getRelatedContacts()
        .stream()
        .filter(relation ->
            Objects.equals(relation.relationName, relationName)
                && Objects.equals(relation.relatedContact, createdContact2))
        .findFirst()
        .orElseThrow(Exception::new);
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

