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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;

/**
 * A Phone number of a contact.
 */
@Builder
@Entity
@Table(name = "phone_number")
public class PhoneNumber {
  @Getter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "contact_id")
  private Contact contact;

  @Getter
  @Column(name = "category")
  private String category;

  @Getter
  @Column(name = "phone_number")
  private String phoneNumber;
}
