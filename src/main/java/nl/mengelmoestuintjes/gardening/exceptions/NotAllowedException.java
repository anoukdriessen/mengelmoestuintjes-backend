package nl.mengelmoestuintjes.gardening.exceptions;

public class NotAllowedException extends RuntimeException {
    public NotAllowedException(String message) {
        super("NOT ALLOWED: " + message);
    }
    public NotAllowedException() {
        super("Not allowed");
    }

}
