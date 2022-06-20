package sdb.server.auth.exceptions;

public class RoleDoesNotExistException extends RuntimeException {

    public RoleDoesNotExistException() {
    }

    public RoleDoesNotExistException(String message) {
        super(message);
    }

    public RoleDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public RoleDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleDoesNotExistException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
