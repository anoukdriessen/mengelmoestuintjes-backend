package nl.mengelmoestuintjes.gardening.controller.exceptions;

import nl.mengelmoestuintjes.gardening.model.Post;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(long id) {
        super("Post with id " + id + " not found");
    }
    public PostNotFoundException(Post post) {
        super("Post " + post + " not found");
    }
    public PostNotFoundException() {
            super("Cannot find post");
        }
}
