package nl.mengelmoestuintjes.gardening.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
            super(message);
        }
    public BadRequestException() {
        super("Something went wrong");
    }
}
