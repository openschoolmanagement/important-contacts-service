package open.schoolmanagement.contacts.importantcontactsservice.web.rest.v1.edm;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import open.schoolmanagement.contacts.importantcontactsservice.domain.Contact;

/**
 * This class is the EDM definition for providing contacts to and from the REST service.
 */
public class EdmContact {
  /**
   * The EdmContact id.
   */
  public Long contactId;
  /**
   * The Organization.
   */
  public String organization;
  /**
   * The Salutation.
   */
  public String salutation;
  /**
   * The Academic title.
   */
  public String academicTitle;
  /**
   * The Firstname.
   */
  public String firstname;
  /**
   * The Middlenames.
   */
  public String middlenames;
  /**
   * The Lastname.
   */
  public String lastname;
  /**
   * The Addressline 1.
   */
  public String addressline1;
  /**
   * The Addressline 2.
   */
  public String addressline2;
  /**
   * The City.
   */
  public String city;
  /**
   * The Zip code.
   */
  public String zipCode;
  /**
   * The Country.
   */
  public String country;
  /**
   * The Notes.
   */
  public String notes;
  /**
   * The Email addresses.
   */
  public Set<EdmEmailAddress> emailAddresses = new HashSet<>();
  /**
   * The Phone numbers.
   */
  public Set<EdmPhoneNumber> phoneNumbers  = new HashSet<>();
  /**
   * The Related contacts.
   */
  public Set<EdmContactRelation> relatedContacts  = new HashSet<>();

  public EdmContact() {}

  /**
   * Instantiates a new EDM EdmContact by copying the doamin object.
   *
   * @param contact the contact
   */
  public EdmContact(Contact contact) {
    this.contactId = contact.getContactId();
    this.organization = contact.getOrganization();
    this.salutation = contact.getSalutation();
    this.academicTitle = contact.getAcademicTitle();
    this.firstname = contact.getFirstname();
    this.middlenames = contact.getMiddlenames();
    this.lastname = contact.getLastname();
    this.addressline1 = contact.getAddressline1();
    this.addressline2 = contact.getAddressline2();
    this.city = contact.getCity();
    this.zipCode = contact.getZipCode();
    this.country = contact.getCountry();
    this.notes = contact.getNotes();

    this.emailAddresses = contact
        .getEmailAddresses()
        .stream()
        .map(EdmEmailAddress::new)
        .collect(Collectors.toSet());
    this.phoneNumbers = contact
        .getPhoneNumbers()
        .stream()
        .map(EdmPhoneNumber::new)
        .collect(Collectors.toSet());
    this.relatedContacts = contact
        .getRelatedContacts()
        .stream()
        .map(EdmContactRelation::new)
        .collect(Collectors.toSet());
  }
}
