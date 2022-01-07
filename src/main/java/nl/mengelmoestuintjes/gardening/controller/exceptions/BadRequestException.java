package nl.mengelmoestuintjes.gardening.controller.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
            super(message);
        }
    public BadRequestException() {
        super("Something went wrong");
    }
}
