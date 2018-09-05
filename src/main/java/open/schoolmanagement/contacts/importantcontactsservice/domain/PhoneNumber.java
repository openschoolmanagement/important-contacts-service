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
 * A phone number
 */
@ToString
@EqualsAndHashCode(of = "phoneNumberId")
@Builder
@Entity
@Table(name = "phoneNumber")
public class PhoneNumber {
  /**
   * The phone number id.
   */
  @Getter
  @Id
  @GeneratedValue
  @Column(name = "phoneNumberId", nullable = false)
  private Long phoneNumberId;

  /**
   * The label of the phone number.
   */
  @Getter
  @Setter
  @NonNull
  @Column(name = "label", nullable = false)
  private String label;

  /**
   * The phone number.
   */
  @Getter
  @Setter
  @Column(name = "phoneNumber")
  private String phoneNumber;

  /**
   * The contact
   */
  @Getter
  @Setter
  @ManyToOne
  private Contact contact;
}
