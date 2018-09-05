package open.schoolmanagement.contacts.importantcontactsservice.web.rest.v1.controller;

import static open.schoolmanagement.contacts.importantcontactsservice.config.Constants.API_V1;
import static open.schoolmanagement.contacts.importantcontactsservice.config.Constants.CONTACT_SERVICE;
import static open.schoolmanagement.contacts.importantcontactsservice.config.Constants.CSRF_URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The CSRF cobtroller is a stub to provide CSRF tokens to clients.
 */
@RestController
public class CsrfController {
  /**
   * This endpoint can be used to implement a health check.
   *
   * @return a response entity with the value 42 and HTTP status code OK
   */
  @RequestMapping(method = RequestMethod.GET, path = API_V1 + CONTACT_SERVICE + CSRF_URI)
  public ResponseEntity<String> csrfStub() {
    return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
  }
}
