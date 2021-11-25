package nl.mengelmoestuintjes.library.exceptions;

/**
 * Class to handle the RecordNotFound Exception
 * @author anoukdriessen
 */
public class RecordNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    // constructor without param
    public RecordNotFoundException() {
        super();
    }

    // constructor with message param
    public RecordNotFoundException(String msg) {
        super(msg);
    }
}
