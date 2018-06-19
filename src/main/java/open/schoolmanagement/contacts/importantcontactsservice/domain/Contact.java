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

package open.schoolmanagement.contacts.importantcontactsservice.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import open.schoolmanagement.contacts.importantcontactsservice.domain.interfaces.Organization;
import open.schoolmanagement.contacts.importantcontactsservice.domain.interfaces.Person;

/**
 * A Contact.
 */
@Entity
@Table(name = "contact")
public class Contact implements Person, Organization {
  @Id
  @GeneratedValue
  @Column(name = "contact_id", nullable = false)
  private Long contactId;

  @Column(name = "organization")
  private String organization;

  @Column(name = "salutation")
  private String salutation;

  @Column(name = "academic_title")
  private String academicTitle;

  @Column(name = "firstname")
  private String firstname;

  @Column(name = "middlenames")
  private String middlenames;

  @Column(name = "lastname")
  private String lastname;

  @Column(name = "addressline1")
  private String addressline1;

  @Column(name = "addressline2")
  private String addressline2;

  @Column(name = "city")
  private String city;

  @Column(name = "zip_code")
  private String zipCode;

  @Column(name = "country")
  private String country;

  @Column(name = "notes", length = 4096)
  private String notes;

  @OneToMany(mappedBy = "contact",
      fetch = FetchType.LAZY,
      cascade = CascadeType.MERGE,
      orphanRemoval = true)
  private Set<EmailAddress> emailAddresses = new HashSet<>();

  @OneToMany(mappedBy = "contact",
      fetch = FetchType.LAZY,
      cascade = CascadeType.MERGE,
      orphanRemoval = true)
  private Set<PhoneNumber> phoneNumbers  = new HashSet<>();

  @OneToMany(mappedBy = "contact",
      fetch = FetchType.LAZY,
      cascade = CascadeType.MERGE,
      orphanRemoval = true)
  private Set<ContactRelation> relatedContacts  = new HashSet<>();

  /**
   * Gets the related contacts.
   *
   * @return the related contacts
   */
  public Set<ContactRelation> getRelatedContacts() {
    return relatedContacts;
  }

  /**
   * Add a related contact.
   *
   * @param relatedContact the related contact
   * @param relationName   the relation name
   * @return the contact relation
   */
  public ContactRelation addRelatedContact(Contact relatedContact, String relationName) {
    ContactRelation contactRelation = new ContactRelation(relationName, relatedContact);

    contactRelation.contact = this;
    relatedContacts.add(contactRelation);
    return contactRelation;
  }

  /**
   * Remove related contact.
   *
   * @param relationName the relation name
   */
  public void removeRelatedContact(String relationName) {
    relatedContacts
        .stream()
        .filter(relation -> Objects.equals(relation.relationName, relationName))
        .findFirst()
        .ifPresent(this::removeRelatedContact);
  }

  /**
   * Remove related contact.
   *
   * @param contactRelation the contact relation
   */
  public void removeRelatedContact(ContactRelation contactRelation) {
    relatedContacts.remove(contactRelation);
  }

  /**
   * Add a new email adress.
   *
   * @param email    the email address
   * @param category the category
   * @return the email address object
   */
  public EmailAddress addEmailAdress(String email, String category) {
    EmailAddress emailAddress = new EmailAddress(email, category);

    emailAddress.contact = this;
    this.emailAddresses.add(emailAddress);
    return emailAddress;
  }

  /**
   * Remove email addressb y category.
   *
   * @param category the category
   */
  public void removeEmailAddressbyCategory(String category) {
    emailAddresses
        .stream()
        .filter(address -> Objects.equals(address.category, category))
        .findFirst()
        .ifPresent(this::removeEmailAddress);
  }

  /**
   * Remove email address.
   *
   * @param email the email
   */
  public void removeEmailAddress(String email) {
    emailAddresses
        .stream()
        .filter(address -> Objects.equals(address.emailAddress, email))
        .findFirst()
        .ifPresent(this::removeEmailAddress);
  }

  /**
   * Remove email address.
   *
   * @param emailAddress the email address
   */
  public void removeEmailAddress(EmailAddress emailAddress) {
    emailAddresses.remove(emailAddress);
  }

  /**
   * Add a phone number.
   *
   * @param phoneNumber the phone number
   * @param category    the category
   * @return the phone number
   */
  public PhoneNumber addPhoneNumber(String phoneNumber, String category) {
    PhoneNumber number = new PhoneNumber(phoneNumber, category);

    number.contact = this;
    phoneNumbers.add(number);
    return number;
  }

  /**
   * Remove phone number by category.
   *
   * @param category the category
   */
  public void removePhoneNumberByCategory(String category) {
    phoneNumbers
        .stream()
        .filter(number -> Objects.equals(number.category, category))
        .findFirst()
        .ifPresent(this::removePhoneNumber);
  }

  /**
   * Remove phone number.
   *
   * @param phoneNumber the phone number
   */
  public void removePhoneNumber(String phoneNumber) {
    phoneNumbers
        .stream()
        .filter(number -> Objects.equals(number.phoneNumber, phoneNumber))
        .findFirst()
        .ifPresent(this::removePhoneNumber);
  }

  /**
   * Remove phone number.
   *
   * @param phoneNumber the phone number
   */
  public void removePhoneNumber(PhoneNumber phoneNumber) {
    phoneNumbers.remove(phoneNumber);
  }

  @Override
  public Long getContactId() {
    return contactId;
  }

  @Override
  public String getOrganization() {
    return organization;
  }

  @Override
  public String getFirstname() {
    return firstname;
  }

  @Override
  public String getMiddlenames() {
    return middlenames;
  }

  @Override
  public String getLastname() {
    return lastname;
  }

  /**
   * Sets organization.
   *
   * @param organization the organization
   */
  public void setOrganization(String organization) {
    this.organization = organization;
  }

  /**
   * Gets salutation.
   *
   * @return the salutation
   */
  public String getSalutation() {
    return salutation;
  }

  /**
   * Sets salutation.
   *
   * @param salutation the salutation
   */
  public void setSalutation(String salutation) {
    this.salutation = salutation;
  }

  /**
   * Gets academic title.
   *
   * @return the academic title
   */
  public String getAcademicTitle() {
    return academicTitle;
  }

  /**
   * Sets academic title.
   *
   * @param academicTitle the academic title
   */
  public void setAcademicTitle(String academicTitle) {
    this.academicTitle = academicTitle;
  }

  /**
   * Sets firstname.
   *
   * @param firstname the firstname
   */
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  /**
   * Sets middlenames.
   *
   * @param middlenames the middlenames
   */
  public void setMiddlenames(String middlenames) {
    this.middlenames = middlenames;
  }

  /**
   * Sets lastname.
   *
   * @param lastname the lastname
   */
  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  /**
   * Gets addressline 1.
   *
   * @return the addressline 1
   */
  public String getAddressline1() {
    return addressline1;
  }

  /**
   * Sets addressline 1.
   *
   * @param addressline1 the addressline 1
   */
  public void setAddressline1(String addressline1) {
    this.addressline1 = addressline1;
  }

  /**
   * Gets addressline 2.
   *
   * @return the addressline 2
   */
  public String getAddressline2() {
    return addressline2;
  }

  /**
   * Sets addressline 2.
   *
   * @param addressline2 the addressline 2
   */
  public void setAddressline2(String addressline2) {
    this.addressline2 = addressline2;
  }

  /**
   * Gets city.
   *
   * @return the city
   */
  public String getCity() {
    return city;
  }

  /**
   * Sets city.
   *
   * @param city the city
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * Gets zip code.
   *
   * @return the zip code
   */
  public String getZipCode() {
    return zipCode;
  }

  /**
   * Sets zip code.
   *
   * @param zipCode the zip code
   */
  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  /**
   * Gets country.
   *
   * @return the country
   */
  public String getCountry() {
    return country;
  }

  /**
   * Sets country.
   *
   * @param country the country
   */
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * Gets notes.
   *
   * @return the notes
   */
  public String getNotes() {
    return notes;
  }

  /**
   * Sets notes.
   *
   * @param notes the notes
   */
  public void setNotes(String notes) {
    this.notes = notes;
  }

  /**
   * Return contach builder.
   *
   * @return the contact builder
   */
  public static ContactBuilder builder() {
    return ContactBuilder.builder();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Contact contact = (Contact) o;
    return Objects.equals(contactId, contact.contactId)
        && Objects.equals(organization, contact.organization)
        && Objects.equals(academicTitle, contact.academicTitle)
        && Objects.equals(firstname, contact.firstname)
        && Objects.equals(middlenames, contact.middlenames)
        && Objects.equals(lastname, contact.lastname);
  }

  @Override
  public int hashCode() {

    return Objects.hash(contactId, organization, academicTitle, firstname, middlenames, lastname);
  }

  /**
   * The type Contact builder.
   */
  public static final class ContactBuilder {
    private String organization;
    private String salutation;
    private String academicTitle;
    private String firstname;
    private String middlenames;
    private String lastname;
    private String addressline1;
    private String addressline2;
    private String city;
    private String zipCode;
    private String country;
    private String notes;

    private ContactBuilder() {
    }

    /**
     * A contact contact builder.
     *
     * @return the contact builder
     */
    public static ContactBuilder builder() {
      return new ContactBuilder();
    }

    /**
     * With organization contact builder.
     *
     * @param organization the organization
     * @return the contact builder
     */
    public ContactBuilder withOrganization(String organization) {
      this.organization = organization;
      return this;
    }

    /**
     * With salutation contact builder.
     *
     * @param salutation the salutation
     * @return the contact builder
     */
    public ContactBuilder withSalutation(String salutation) {
      this.salutation = salutation;
      return this;
    }

    /**
     * With academic title contact builder.
     *
     * @param academicTitle the academic title
     * @return the contact builder
     */
    public ContactBuilder withAcademicTitle(String academicTitle) {
      this.academicTitle = academicTitle;
      return this;
    }

    /**
     * With firstname contact builder.
     *
     * @param firstname the firstname
     * @return the contact builder
     */
    public ContactBuilder withFirstname(String firstname) {
      this.firstname = firstname;
      return this;
    }

    /**
     * With middlenames contact builder.
     *
     * @param middlenames the middlenames
     * @return the contact builder
     */
    public ContactBuilder withMiddlenames(String middlenames) {
      this.middlenames = middlenames;
      return this;
    }

    /**
     * With lastname contact builder.
     *
     * @param lastname the lastname
     * @return the contact builder
     */
    public ContactBuilder withLastname(String lastname) {
      this.lastname = lastname;
      return this;
    }

    /**
     * With addressline 1 contact builder.
     *
     * @param addressline1 the addressline 1
     * @return the contact builder
     */
    public ContactBuilder withAddressline1(String addressline1) {
      this.addressline1 = addressline1;
      return this;
    }

    /**
     * With addressline 2 contact builder.
     *
     * @param addressline2 the addressline 2
     * @return the contact builder
     */
    public ContactBuilder withAddressline2(String addressline2) {
      this.addressline2 = addressline2;
      return this;
    }

    /**
     * With city contact builder.
     *
     * @param city the city
     * @return the contact builder
     */
    public ContactBuilder withCity(String city) {
      this.city = city;
      return this;
    }

    /**
     * With zip code contact builder.
     *
     * @param zipCode the zip code
     * @return the contact builder
     */
    public ContactBuilder withZipCode(String zipCode) {
      this.zipCode = zipCode;
      return this;
    }

    /**
     * With country contact builder.
     *
     * @param country the country
     * @return the contact builder
     */
    public ContactBuilder withCountry(String country) {
      this.country = country;
      return this;
    }

    /**
     * With notes contact builder.
     *
     * @param notes the notes
     * @return the contact builder
     */
    public ContactBuilder withNotes(String notes) {
      this.notes = notes;
      return this;
    }

    /**
     * Build contact.
     *
     * @return the contact
     */
    public Contact build() {
      Contact contact = new Contact();
      contact.setOrganization(organization);
      contact.setSalutation(salutation);
      contact.setAcademicTitle(academicTitle);
      contact.setFirstname(firstname);
      contact.setMiddlenames(middlenames);
      contact.setLastname(lastname);
      contact.setAddressline1(addressline1);
      contact.setAddressline2(addressline2);
      contact.setCity(city);
      contact.setZipCode(zipCode);
      contact.setCountry(country);
      contact.setNotes(notes);
      return contact;
    }
  }
}
