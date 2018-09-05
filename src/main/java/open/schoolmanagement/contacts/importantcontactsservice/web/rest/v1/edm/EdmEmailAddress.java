package open.schoolmanagement.contacts.importantcontactsservice.web.rest.v1.edm;

import open.schoolmanagement.contacts.importantcontactsservice.domain.EmailAddress;

/**
 * The type Email address.
 */
public class EdmEmailAddress {
  public String emailAddress;
  public String category;

  public EdmEmailAddress() {}

  public EdmEmailAddress(EmailAddress emailAddress) {
    this.emailAddress = emailAddress.emailAddress;
    this.category = emailAddress.category;
  }
}
