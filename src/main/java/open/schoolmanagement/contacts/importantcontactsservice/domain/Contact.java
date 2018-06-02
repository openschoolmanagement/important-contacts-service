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
import lombok.Builder;
import lombok.Getter;
import open.schoolmanagement.contacts.importantcontactsservice.domain.interfaces.Organization;
import open.schoolmanagement.contacts.importantcontactsservice.domain.interfaces.Person;
import org.springframework.lang.NonNull;

/**
 * A Contact.
 */
@Builder
@Entity
@Table(name = "contact")
public class Contact implements Person, Organization {
  @Id
  @GeneratedValue
  @Column(name = "contact_id", nullable = false)
  @NonNull
  private Long contactId;

  @Column(name = "organization")
  private String organization;

  @Getter
  @Column(name = "salutation")
  private String salutation;

  @Getter
  @Column(name = "academic_title")
  private String academicTitle;

  @Column(name = "firstname")
  private String firstname;

  @Column(name = "middlenames")
  private String middlenames;

  @Column(name = "lastname")
  private String lastname;

  @Getter
  @Column(name = "addressline1")
  private String addressline1;

  @Getter
  @Column(name = "addressline2")
  private String addressline2;

  @Getter
  @Column(name = "city")
  private String city;

  @Getter
  @Column(name = "zip_code")
  private String zipCode;

  @Getter
  @Column(name = "country")
  private String country;

  @Getter
  @Column(name = "notes", length = 4096)
  private String notes;

  @Getter
  @OneToMany(mappedBy = "contact",
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private Set<EmailAddress> emailAddresses;

  @Getter
  @OneToMany(mappedBy = "contact",
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private Set<PhoneNumber> phoneNumbers;

  @Getter
  @OneToMany(mappedBy = "contact",
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private Set<ContactRelation> relatedContacts;

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

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Contact contact = (Contact) o;
    return Objects.equals(contactId, contact.contactId) &&
        Objects.equals(organization, contact.organization) &&
        Objects.equals(academicTitle, contact.academicTitle) &&
        Objects.equals(firstname, contact.firstname) &&
        Objects.equals(middlenames, contact.middlenames) &&
        Objects.equals(lastname, contact.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contactId, organization, academicTitle, firstname, middlenames, lastname);
  }
}
