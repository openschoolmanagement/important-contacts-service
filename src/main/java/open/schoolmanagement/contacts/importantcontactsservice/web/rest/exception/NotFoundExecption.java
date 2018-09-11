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

package open.schoolmanagement.contacts.importantcontactsservice.web.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Runtime exception that is thrown if an entity wasn't found.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundExecption extends RuntimeException {
  /**
   * Instantiates a new NotFoundExecption.
   */
  public NotFoundExecption() {
  }

  /**
   * Instantiates a new NotFoundExecption.
   *
   * @param message the message
   */
  public NotFoundExecption(String message) {
    super(message);
  }

  /**
   * Instantiates a new NotFoundExecption.
   *
   * @param message the message
   * @param cause   the cause
   */
  public NotFoundExecption(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Instantiates a new NotFoundExecption.
   *
   * @param cause the cause
   */
  public NotFoundExecption(Throwable cause) {
    super(cause);
  }

  /**
   * Instantiates a new NotFoundExecption.
   *
   * @param message            the message
   * @param cause              the cause
   * @param enableSuppression  the enable suppression
   * @param writableStackTrace the writable stack trace
   */
  public NotFoundExecption(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {

    super(message, cause, enableSuppression, writableStackTrace);
  }

  public static NotFoundExecption eventProcessingResultNotFound() {
    return new NotFoundExecption("Event processing result not found.");
  }
}
