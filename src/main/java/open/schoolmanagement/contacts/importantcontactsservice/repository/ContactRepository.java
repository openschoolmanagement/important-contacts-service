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

package open.schoolmanagement.contacts.importantcontactsservice.repository;

import java.util.Collection;
import open.schoolmanagement.contacts.importantcontactsservice.domain.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * CRUD repository for contacts.
 */
public interface ContactRepository extends CrudRepository<Contact, Long> {
  /**
   * Find contacts by organization.
   *
   * @param organization the organization to filter by
   * @return a collection with the contacts that match the organization
   */
  @Query("SELECT c FROM Contact c where c.organization LIKE %?1")
  Collection<Contact> findContactsByOrganization(String organization);

  /**
   * Find contacts by name.
   *
   * @param firstname   the firstname
   * @param middlenames the middlenames
   * @param lastname    the lastname
   * @return a collection with the contacts that match the given name
   */
  @Query("SELECT c FROM Contact c where c.firstname LIKE %?1 "
      + "AND c.middlenames LIKE %?2 "
      + "AND c.lastname LIKE %?3")
  Collection<Contact> findContactsByName(String firstname, String middlenames, String lastname);
}
