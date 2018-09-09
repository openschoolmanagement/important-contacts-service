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
 * Exception that is thrown when the key for a create command has already been used.
 */
@ResponseStatus(code = HttpStatus.CONFLICT)
public class DuplicateKeyException extends ServerRuntimeException {
  /**
   * Instantiates a new DuplicateKeyException.
   */
  public DuplicateKeyException() {
    super();
  }

  /**
   * Instantiates a new DuplicateKeyException.
   *
   * @param message the message
   */
  public DuplicateKeyException(String message) {
    super(message);
  }

  /**
   * Instantiates a new DuplicateKeyException.
   *
   * @param message the message
   * @param cause   the cause
   */
  public DuplicateKeyException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Instantiates a new DuplicateKeyException.
   *
   * @param cause the cause
   */
  public DuplicateKeyException(Throwable cause) {
    super(cause);
  }

  /**
   * Instantiates a new DuplicateKeyException.
   *
   * @param message            the message
   * @param cause              the cause
   * @param enableSuppression  the enable suppression
   * @param writableStackTrace the writable stack trace
   */
  public DuplicateKeyException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public static DuplicateKeyException duplicateContactId() {
    return new DuplicateKeyException("Duplicate Contact Id.");
  }
}
