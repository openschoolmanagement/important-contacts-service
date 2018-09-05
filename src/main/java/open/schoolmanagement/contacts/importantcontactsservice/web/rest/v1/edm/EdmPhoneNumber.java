package open.schoolmanagement.contacts.importantcontactsservice.web.rest.v1.edm;

import open.schoolmanagement.contacts.importantcontactsservice.domain.PhoneNumber;

/**
 * The type Phone number.
 */
public class EdmPhoneNumber {
  public String category;
  public String phoneNumber;

  public EdmPhoneNumber() {}

  public EdmPhoneNumber(PhoneNumber phoneNumber) {
    this.category = phoneNumber.category;
    this.phoneNumber = phoneNumber.phoneNumber;
  }
}
