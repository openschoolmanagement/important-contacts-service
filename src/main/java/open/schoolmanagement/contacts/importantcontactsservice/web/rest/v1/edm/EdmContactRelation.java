package open.schoolmanagement.contacts.importantcontactsservice.web.rest.v1.edm;

import open.schoolmanagement.contacts.importantcontactsservice.domain.ContactRelation;

/**
 * The type EdmContact relation.
 */
public class EdmContactRelation {
  public String relationName;
  public Long relatedContactId;

  public EdmContactRelation() {}

  public EdmContactRelation(ContactRelation contactRelation) {
    this.relationName = contactRelation.relationName;
    this.relatedContactId = contactRelation.relatedContact.getContactId();
  }
}
