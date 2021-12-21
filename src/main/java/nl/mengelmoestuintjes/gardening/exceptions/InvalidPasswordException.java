package nl.mengelmoestuintjes.gardening.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException( String message ) {
        super(message);
    }
    public InvalidPasswordException() {
        super("Password is invalid");
    }
}
