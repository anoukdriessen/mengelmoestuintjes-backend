package nl.mengelmoestuintjes.gardening.controller.exceptions;

public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException(String username) {
        super("User with credentials " + username + " is not authorized");
    }
    public NotAuthorizedException() {
        super("Not authorized");
    }

}
