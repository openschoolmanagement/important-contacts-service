package open.schoolmanagement.contacts.importantcontactsservice.web.rest.v1.controller;

import static open.schoolmanagement.contacts.importantcontactsservice.config.Constants.ALL_CONTACTS_URI;
import static open.schoolmanagement.contacts.importantcontactsservice.config.Constants.API_V1;
import static open.schoolmanagement.contacts.importantcontactsservice.config.Constants.CONTACT_SERVICE;

import java.util.List;
import java.util.stream.Collectors;
import open.schoolmanagement.contacts.importantcontactsservice.service.ContactService;
import open.schoolmanagement.contacts.importantcontactsservice.web.rest.v1.edm.EdmContact;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * TheContacts controller provides the endpoints for contacts.
 */
@RestController
public class ContactsController {
  private final ContactService contactService;

  /**
   * Instantiates a new Contacts controller.
   *
   * @param contactService the contact service
   */
  public ContactsController(final ContactService contactService) {
    this.contactService = contactService;
  }

  /**
   * Read all contacts.
   */
  @RequestMapping(method = RequestMethod.GET, path = API_V1 + CONTACT_SERVICE + ALL_CONTACTS_URI)
  public ResponseEntity<List<EdmContact>> readAllContacts() {
    return new ResponseEntity<>(
        contactService
            .readAllContacts()
            .stream()
            .map(EdmContact::new)
            .collect(Collectors.toList()),
        HttpStatus.OK);
  }
}
