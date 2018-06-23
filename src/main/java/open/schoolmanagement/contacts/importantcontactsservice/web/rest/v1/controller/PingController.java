package open.schoolmanagement.contacts.importantcontactsservice.web.rest.v1.controller;

import static open.schoolmanagement.contacts.importantcontactsservice.config.Constants.API_V1;
import static open.schoolmanagement.contacts.importantcontactsservice.config.Constants.CONTACT_SERVICE;
import static open.schoolmanagement.contacts.importantcontactsservice.config.Constants.PING_URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The  Ping REST controller.
 */
@RestController
public class PingController {
  /**
   * This endpoint can be used to implement a health check.
   *
   * @return a response entity with the value 42 and HTTP status code OK
   */
  @RequestMapping(path = API_V1 + CONTACT_SERVICE + PING_URI)
  public ResponseEntity<String> ping() {
    return new ResponseEntity<String>("42", HttpStatus.OK);
  }
}
