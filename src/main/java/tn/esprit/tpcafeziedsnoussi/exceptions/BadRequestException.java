package tn.esprit.tpcafeziedsnoussi.exceptions;

/**
 * Exception thrown when the client sends invalid data or malformed requests
 */
public class BadRequestException extends RuntimeException {
    
    public BadRequestException(String message) {
        super(message);
    }
    
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
