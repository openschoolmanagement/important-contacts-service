package open.schoolmanagement.contacts.importantcontactsservice.domain.interfaces;

/**
 * Access a contact as an Organization.
 */
public interface Organization {
  /**
   * Gets contact id.
   *
   * @return the contact id
   */
  Long getContactId();

  /**
   * Gets organization.
   *
   * @return the organization
   */
  String getOrganization();
}
