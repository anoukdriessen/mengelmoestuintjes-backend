package nl.mengelmoestuintjes.library.controller;

import nl.mengelmoestuintjes.library.exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Class to handle the Bad Requests
 * @author anoukdriessen
 */
@ControllerAdvice
public class ExceptionController {

    // Record not Found
    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception (RecordNotFoundException e) {
        return new ResponseEntity<> (e.getMessage(), HttpStatus.NOT_FOUND);
    }
    /*
      Usage:
      if (...) { // determine if id exists
          throw new RecordNotFoundException('ID cannot be found');
      }
     */
}
