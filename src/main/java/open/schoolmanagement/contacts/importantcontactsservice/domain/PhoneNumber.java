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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * A Phone number of a contact.
 */
@Entity
@Table(name = "phone_number")
public class PhoneNumber {
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "contact_id")
  public Contact contact;

  @Column(name = "category")
  public String category;

  @Column(name = "phone_number")
  @Id
  public String phoneNumber;

  private PhoneNumber() {}

  /**
   * Instantiates a new Phone number.
   *
   * @param phoneNumber the phone number
   * @param category    the category
   */
  public PhoneNumber(String phoneNumber, String category) {
    this.phoneNumber = phoneNumber;
    this.category = category;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PhoneNumber that = (PhoneNumber) o;
    return Objects.equals(category, that.category)
        && Objects.equals(phoneNumber, that.phoneNumber);
  }

  @Override
  public int hashCode() {

    return Objects.hash(category, phoneNumber);
  }
}
