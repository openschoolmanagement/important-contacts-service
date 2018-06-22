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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * A ralation between two contacts.
 */
@Entity
@Table(name = "contact_relation")
public class ContactRelation {
  @Column(name = "contact_relation_id")
  @GeneratedValue
  @Id
  private Long id;

  /**
   * The Contact.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "origin_contact_id")
  public Contact contact;

  /**
   * The Relation name.
   */
  @Column(name = "relation_name")
  public String relationName;

  /**
   * The Related contact.
   */
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "related_contact_id")
  public Contact relatedContact;

  private ContactRelation() {}

  /**
   * Instantiates a new ContactRelation.
   *
   * @param relationName   the relation name
   * @param relatedContact the related contact
   */
  public ContactRelation(String relationName, Contact relatedContact) {
    this.relationName = relationName;
    this.relatedContact = relatedContact;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ContactRelation that = (ContactRelation) o;
    return Objects.equals(getId(), that.getId())
        && Objects.equals(relationName, that.relationName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), relationName);
  }
}
