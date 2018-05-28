package open.schoolmanagement.contacts.importantcontactsservice.repository;

import java.util.Collection;
import java.util.UUID;
import open.schoolmanagement.contacts.importantcontactsservice.domain.Contact;
import org.springframework.data.repository.CrudRepository;

/**
 * CRUD repository for contacts.
 */
public interface ContactRepository extends CrudRepository<Contact, UUID> {
  /**
   * Find contacts by organization.
   *
   * @param organization the organization to filter by
   * @return a collection with the contacts that match the organization
   */
  Collection<Contact> findContactsByOrganization(String organization);

  /**
   * Find contacts by name.
   *
   * @param firstname   the firstname
   * @param middlenames the middlenames
   * @param lastname    the lastname
   * @return a collection with the contacts that match the given name
   */
  Collection<Contact> findContactsByName(String firstname, String middlenames, String lastname);
}
