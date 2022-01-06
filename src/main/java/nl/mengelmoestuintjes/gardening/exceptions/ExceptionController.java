package nl.mengelmoestuintjes.gardening.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@ControllerAdvice
public class ExceptionController {
    private ErrorResponse response;
    private LocalDateTime timeStamp;

    public ErrorResponse thisResponse(HttpStatus status, String message) {
        timeStamp = LocalDateTime.now();
        return new ErrorResponse(
                status,
                message,
                timeStamp);
    }

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException e) {
        this.response = thisResponse( HttpStatus.NOT_FOUND, e.getMessage() );
        return ResponseEntity
                .status(response.getStatus())
                .body(response.getBody());
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> exception(BadRequestException e) {
        this.response = thisResponse( HttpStatus.BAD_REQUEST, e.getMessage()  );
        return ResponseEntity
                .status(response.getStatus())
                .body(response.getBody());
    }

    @ExceptionHandler(value = InvalidPasswordException.class)
    public ResponseEntity<Object> exception(InvalidPasswordException e) {
        this.response = thisResponse( HttpStatus.UNAUTHORIZED, e.getMessage() );
        return ResponseEntity
                .status(response.getStatus())
                .body(response.getBody());
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> exception(UserNotFoundException e) {
        this.response = thisResponse( HttpStatus.NOT_FOUND, e.getMessage() );
        return ResponseEntity
                .status(response.getStatus())
                .body(response.getBody());
    }

    @ExceptionHandler(value = NotAuthorizedException.class)
    public ResponseEntity<Object> exception(NotAuthorizedException e ) {
        this.response = thisResponse( HttpStatus.UNAUTHORIZED, e.getMessage() );
        return ResponseEntity
                .status(response.getStatus())
                .body(response.getBody());
    }

    @ExceptionHandler(value = NotAllowedException.class)
    public ResponseEntity<Object> exception( NotAllowedException e ) {
        this.response = thisResponse( HttpStatus.BAD_REQUEST, e.getMessage() );
        return ResponseEntity
                .status(response.getStatus())
                .body(response.getBody());
    }


}