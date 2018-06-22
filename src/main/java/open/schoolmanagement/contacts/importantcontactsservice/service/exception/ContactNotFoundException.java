package open.schoolmanagement.contacts.importantcontactsservice.service.exception;

/**
 * This exception is thrown, if a contact wasn't found.
 */
public class ContactNotFoundException extends Exception {
  private Long contactId;

  /**
   * Instantiates a new ContactNotFoundException.
   *
   * @param contactId the contact id
   */
  public ContactNotFoundException(Long contactId) {
    super(String.format("Contqct with ID %s not found", contactId));
    this.contactId = contactId;
  }

  /**
   * Gets the contactId.
   *
   * @return the contactId
   */
  public Long getContactId() {
    return contactId;
  }
}
