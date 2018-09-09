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

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * A Contact. This is a transient entity.
 */
@ToString
@EqualsAndHashCode(of = "id")
@Builder
@Entity
@Table(name = "contact")
public class Contact implements Serializable {
  /**
   * The contact id.
   */
  @Getter
  @Setter
  @NonNull
  @Id
  @Column(name = "id", nullable = false)
  private UUID id;

  /**
   * The organization of the contact.
   */
  @Getter
  @Setter
  @Column(name = "organization")
  private String organization;

  /**
   * The salutation of the contact.
   */
  @Getter
  @Setter
  @Column(name = "salutation")
  private String salutation;

  /**
   * The academic title of the contact.
   */
  @Getter
  @Setter
  @Column(name = "academic_title")
  private String academicTitle;

  /**
   * The given name of the contact.
   */
  @Getter
  @Setter
  @Column(name = "given_name")
  private String givenName;

  /**
   * The middle names of the contact.
   */
  @Getter
  @Setter
  @Column(name = "middle_names")
  private String middleNames;

  /**
   * The last name of the contact.
   */
  @Getter
  @Setter
  @Column(name = "last_name")
  private String lastName;

  /**
   * Notes for the contact.
   */
  @Getter
  @Setter
  @Column(name = "notes")
  private String notes;

  /**
   * The postal addresses of the contact.
   */
  @Getter
  @Setter
  @Builder.Default
  @OneToMany(mappedBy = "contact", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  @Fetch(FetchMode.JOIN)
  private Set<PostalAddress> postalAddresses = new HashSet<>();

  /**
   * The email addresses of the contact.
   */
  @Getter
  @Setter
  @Builder.Default
  @OneToMany(mappedBy = "contact", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  @Fetch(FetchMode.JOIN)
  private Set<EmailAddress> emailAddresses = new HashSet<>();

  /**
   * The phone numbers of the contact.
   */
  @Getter
  @Setter
  @Builder.Default
  @OneToMany(mappedBy = "contact", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  @Fetch(FetchMode.JOIN)
  private Set<PhoneNumber> phoneNumbers = new HashSet<>();

  /**
   * The dates of the contact.
   */
  @Getter
  @Setter
  @Builder.Default
  @OneToMany(mappedBy = "contact", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  @Fetch(FetchMode.JOIN)
  private Set<LabeledDate> dates = new HashSet<>();
}
