package nl.mengelmoestuintjes.gardening.controller.exceptions;

public class InvalidException extends RuntimeException {
    public InvalidException( String field ) {
        super("Invalid field " + field);
    }
    public InvalidException() {
        super("Field is invalid");
    }
}
