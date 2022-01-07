package nl.mengelmoestuintjes.gardening.controller.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException( String username ) {
        super("Invalid password for user with username " + username);
    }
    public InvalidPasswordException() {
        super("Password is invalid");
    }
}
