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
