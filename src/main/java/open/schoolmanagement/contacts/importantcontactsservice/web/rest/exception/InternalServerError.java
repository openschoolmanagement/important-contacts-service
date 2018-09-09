package open.schoolmanagement.contacts.importantcontactsservice.web.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception that is thrown in case of an internal server error.
 */
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerError extends ServerRuntimeException {
  /**
   * Instantiates a new InternalServerError.
   */
  public InternalServerError() {
  }

  /**
   * Instantiates a new InternalServerError.
   *
   * @param message the message
   */
  public InternalServerError(String message) {
    super(message);
  }

  /**
   * Instantiates a new InternalServerError.
   *
   * @param message the message
   * @param cause   the cause
   */
  public InternalServerError(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Instantiates a new InternalServerError.
   *
   * @param cause the cause
   */
  public InternalServerError(Throwable cause) {
    super(cause);
  }

  /**
   * Instantiates a new InternalServerError.
   *
   * @param message            the message
   * @param cause              the cause
   * @param enableSuppression  the enable suppression
   * @param writableStackTrace the writable stack trace
   */
  public InternalServerError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
