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

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Builder
@Entity
@Table(name = "contact")
public class Contact {
  @Getter
  @Id
  @GeneratedValue
  @Column(name = "contact_id")
  private UUID id;

  @Getter
  @Column(name = "company")
  private String company;

  @Getter
  @Column(name = "salutation")
  private String salutation;

  @Getter
  @Column(name = "academic_title")
  private String academicTitle;

  @Getter
  @Column(name = "firstname")
  private String firstname;

  @Getter
  @Column(name = "middlenames")
  private String middlenames;

  @Getter
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
  private Collection<EmailAddress> emailAddresses;

  @Getter
  @OneToMany(mappedBy = "contact",
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private Collection<PhoneNumber> phoneNumbers;

  @Getter
  @OneToMany(mappedBy = "contact",
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private Collection<ContactRelation> relatedContacts;
}
