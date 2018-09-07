/*
   Copyright 2018 Open School Management

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package open.schoolmanagement.contacts.importantcontactsservice.web.rest.v1.controller;


import static open.schoolmanagement.contacts.importantcontactsservice.config.Constants.API_V1_IMPORTANT_CONTACTS_URI;
import static open.schoolmanagement.contacts.importantcontactsservice.config.Constants.PUBLIC_URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The  Ping REST controller.
 */
@RestController
public class PingController {
  /**
   * The URI of this controller.
   */
  public static final String URI = API_V1_IMPORTANT_CONTACTS_URI + PUBLIC_URI + "ping";

  /**
   * This endpoint can be used to implement a health check.
   *
   * @return a response entity with the value 42 and HTTP status code OK
   */
  @RequestMapping(path = URI, method = RequestMethod.GET)
  public ResponseEntity<String> ping() {
    return new ResponseEntity<String>("42", HttpStatus.OK);
  }
}