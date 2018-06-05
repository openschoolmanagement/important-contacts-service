package open.schoolmanagement.contacts.importantcontactsservice.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ContactTest {
  @Test
  public void equals_of_same_contact_is_true() {
    Contact contact1 = Contact.builder().withFirstname("A").build();
    Contact contact2 = Contact.builder().withFirstname("A").build();

    assertThat(contact1).isEqualTo(contact2);
  }

  @Test
  public void equals_of_different_contact_is_false() {
    Contact contact1 = Contact.builder().withFirstname("A").build();
    Contact contact2 = Contact.builder().withFirstname("B").build();

    assertThat(contact1).isNotEqualTo(contact2);
  }

  @Test
  public void equals_of_and_other_object_is_false() {
    Contact contact1 = Contact.builder().withFirstname("A").build();
    String str = "str";

    assertThat(contact1).isNotEqualTo(str);
  }

  @Test
  public void hashCode_of_same_contact_is_equal() {
    Contact contact1 = Contact.builder().withFirstname("A").build();
    Contact contact2 = Contact.builder().withFirstname("A").build();

    assertThat(contact1.hashCode()).isEqualTo(contact2.hashCode());
  }

  @Test
  public void hashCode_of_different_contact_is_not_equal() {
    Contact contact1 = Contact.builder().withFirstname("A").build();
    Contact contact2 = Contact.builder().withFirstname("B").build();

    assertThat(contact1.hashCode()).isNotEqualTo(contact2.hashCode());
  }

  @Test
  public void contact_creation_is_successful() {
    Contact contact = createContact();

    assertThat(contact.getSalutation()).isEqualTo("Mr.");
    assertThat(contact.getAcademicTitle()).isEqualTo("Dr.");
    assertThat(contact.getFirstname()).isEqualTo("Michael");
    assertThat(contact.getMiddlenames()).isEqualTo("William");
    assertThat(contact.getLastname()).isEqualTo("Brogdon");
    assertThat(contact.getAddressline1()).isEqualTo("1341 Meadow View Drive");
    assertThat(contact.getAddressline2()).isEqualTo("Flat 6");
    assertThat(contact.getCity()).isEqualTo("Bristol");
    assertThat(contact.getZipCode()).isEqualTo("CT 06010");
  }

  @Test
  public void add_related_contact_is_successful() {
    Contact contact = createContact();
    Contact otherContact = createOtherContact();

    contact.addRelatedContact(otherContact, "Relation");

    assertThat(contact.getRelatedContacts().size()).isEqualTo(1);
    assertThat(contact.getRelatedContacts().stream().findFirst().get().contact).isEqualTo(contact);
    assertThat(contact.getRelatedContacts().stream().findFirst().get().relatedContact).isEqualTo(otherContact);
    assertThat(contact.getRelatedContacts().stream().findFirst().get().relationName).isEqualTo("Relation");
  }

  @Test
  public void remove_related_contact_by_relation_name_is_successful() {
    Contact contact = createContact();
    Contact otherContact = createOtherContact();

    contact.addRelatedContact(otherContact, "Relation");

    assertThat(contact.getRelatedContacts().size()).isEqualTo(1);

    contact.removeRelatedContact("Relation");

    assertThat(contact.getRelatedContacts().size()).isEqualTo(0);
  }

  @Test
  public void remove_related_contact_is_successful() {
    Contact contact = createContact();
    Contact otherContact = createOtherContact();

    ContactRelation relation = contact.addRelatedContact(otherContact, "Relation");

    assertThat(contact.getRelatedContacts().size()).isEqualTo(1);

    contact.removeRelatedContact(relation);

    assertThat(contact.getRelatedContacts().size()).isEqualTo(0);
  }

  private Contact createContact() {
    return Contact
        .builder()
        .withSalutation("Mr.")
        .withAcademicTitle("Dr.")
        .withFirstname("Michael")
        .withMiddlenames("William")
        .withLastname("Brogdon")
        .withAddressline1("1341 Meadow View Drive")
        .withAddressline2("Flat 6")
        .withCity("Bristol")
        .withZipCode("CT 06010")
        .withCountry("USA")
        .withNotes("Stiffler")
        .build();
  }

  private Contact createOtherContact() {
    return Contact
        .builder()
        .withSalutation("Mr.")
        .withFirstname("Charles")
        .withMiddlenames("Tiberius")
        .withLastname("Kent")
        .withAddressline1("2765 Spinnaker Lane")
        .withAddressline2("Flat 1B")
        .withCity("Joliet")
        .withZipCode("IL 60435")
        .withCountry("USA")
        .withNotes("Stiffler's friend")
        .build();
  }
}
