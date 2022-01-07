package nl.mengelmoestuintjes.gardening.controller.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("User with username " + username + " not found");
    }
    public UserNotFoundException() {
        super("Cannot find user");
    }

}
