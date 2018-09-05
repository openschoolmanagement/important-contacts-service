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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * A Contact.
 */
@ToString
@EqualsAndHashCode(of = "addressId")
@Builder
@Entity
@Table(name = "postalAddress")
public class PostalAddress {
  /**
   * The address id.
   */
  @Getter
  @Id
  @GeneratedValue
  @Column(name = "addressId", nullable = false)
  private Long addressId;

  /**
   * The label of the address.
   */
  @Getter
  @Setter
  @NonNull
  @Column(name = "label", nullable = false)
  private String label;

  /**
   * The street name.
   */
  @Getter
  @Setter
  @Column(name = "street")
  private String street;

  /**
   * The city name.
   */
  @Getter
  @Setter
  @Column(name = "city")
  private String city;

  /**
   * The state name.
   */
  @Getter
  @Setter
  @Column(name = "state")
  private String state;

  /**
   * The postal code.
   */
  @Getter
  @Setter
  @Column(name = "postalCode")
  private String postalCode;

  /**
   * The country name.
   */
  @Getter
  @Setter
  @Column(name = "country")
  private String country;

  /**
   * The ISO country code, using the ISO 3166-1 alpha-2 standard .
   */
  @Getter
  @Setter
  @Column(name = "isoCountryCode")
  private String isoCountryCode;

  /**
   * The contact
   */
  @Getter
  @Setter
  @ManyToOne
  private Contact contact;
}
