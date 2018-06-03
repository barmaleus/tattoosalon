package by.rekuts.tattoosalon.exception;

public class SalonException extends Exception {
    public SalonException() {
    }

    public SalonException(String message) {
        super(message);
    }

    public SalonException(String message, Throwable cause) {
        super(message, cause);
    }

    public SalonException(Throwable cause) {
        super(cause);
    }

    public SalonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
