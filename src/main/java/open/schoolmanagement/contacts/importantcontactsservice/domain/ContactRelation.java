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
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;

/**
 * A ralation between two contacts.
 */
@Builder
@Entity
@Table(name = "contact_relation")
public class ContactRelation {
  @Getter
  @Column(name = "contact_relation_id")
  @GeneratedValue
  @Id
  private UUID id;

  @Getter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "origin_contact_id")
  private Contact contact;

  @Getter
  @Column(name = "relation_name")
  private String relationName;

  @Getter
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "related_contact_id")
  private Contact relatedContact;

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    ContactRelation that = (ContactRelation) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(relationName, that.relationName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, relationName);
  }
}
