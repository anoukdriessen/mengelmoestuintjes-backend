package nl.mengelmoestuintjes.gardening.controller.exceptions;

public class RecordNotFoundException extends RuntimeException{

    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException() {
        super("Not found");
    }
}
