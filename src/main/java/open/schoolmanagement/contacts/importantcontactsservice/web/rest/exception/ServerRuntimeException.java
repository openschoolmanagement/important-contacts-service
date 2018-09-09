package open.schoolmanagement.contacts.importantcontactsservice.web.rest.exception;

/**
 * This is the base class for all own runtime exceptions.
 */
public class ServerRuntimeException extends RuntimeException {
  /**
   * Instantiates a new ServerRuntimeException.
   */
  public ServerRuntimeException() {
  }

  /**
   * Instantiates a new ServerRuntimeException.
   *
   * @param message the message
   */
  public ServerRuntimeException(String message) {
    super(message);
  }

  /**
   * Instantiates a new ServerRuntimeException.
   *
   * @param message the message
   * @param cause   the cause
   */
  public ServerRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Instantiates a new ServerRuntimeException.
   *
   * @param cause the cause
   */
  public ServerRuntimeException(Throwable cause) {
    super(cause);
  }

  /**
   * Instantiates a new ServerRuntimeException.
   *
   * @param message            the message
   * @param cause              the cause
   * @param enableSuppression  the enable suppression
   * @param writableStackTrace the writable stack trace
   */
  public ServerRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
