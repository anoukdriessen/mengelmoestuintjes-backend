package nl.mengelmoestuintjes.gardening.controller.exceptions;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(long id) {
        super("Post with id " + id + " not found");
    }
    public PostNotFoundException() {
            super("Cannot find post");
        }
}
