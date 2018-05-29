package open.schoolmanagement.contacts.importantcontactsservice.domain.interfaces;

import java.util.UUID;

/**
 * Access a contact as an Organization.
 */
public interface Organization {
  /**
   * Gets contact id.
   *
   * @return the contact id
   */
  UUID getContactId();

  /**
   * Gets organization.
   *
   * @return the organization
   */
  String getOrganization();
}
