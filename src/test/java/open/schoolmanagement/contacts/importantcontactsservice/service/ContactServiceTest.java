package open.schoolmanagement.contacts.importantcontactsservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Collection;
import open.schoolmanagement.contacts.importantcontactsservice.ImportantContactsServiceApplication;
import open.schoolmanagement.contacts.importantcontactsservice.domain.Contact;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImportantContactsServiceApplication.class)
public class ContactServiceTest {
  @Autowired
  private ContactService contactService;

  @Test
  public void find_all_contacts_on_empty_database_returns_emptyList() {
    Collection<Contact> contacts = contactService.readAllContacts();

    assertThat(contacts.size()).isEqualTo(0);
  }
}

