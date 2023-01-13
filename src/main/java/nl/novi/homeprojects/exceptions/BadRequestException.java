package nl.novi.homeprojects.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super();
    }
    public BadRequestException(String message) {
        super(message);
    }
}