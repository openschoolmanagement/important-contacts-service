package open.schoolmanagement.contacts.importantcontactsservice.domain.interfaces;

import java.util.UUID;

/**
 * Access a Contact as a Person.
 */
public interface Person {
  /**
   * Gets contact id.
   *
   * @return the contact id
   */
  UUID getContactId();

  /**
   * Gets firstname.
   *
   * @return the firstname
   */
  String getFirstname();

  /**
   * Gets middlenames.
   *
   * @return the middlenames
   */
  String getMiddlenames();

  /**
   * Gets lastname.
   *
   * @return the lastname
   */
  String getLastname();
}
