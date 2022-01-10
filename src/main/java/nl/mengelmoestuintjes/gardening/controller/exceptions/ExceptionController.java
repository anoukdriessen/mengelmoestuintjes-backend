package nl.mengelmoestuintjes.gardening.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@ControllerAdvice
public class ExceptionController {

    public ResponseEntity<Object> createResponse(HttpStatus status, String message) {
        message += " timestamp: " + LocalDateTime.now();
        return ResponseEntity
                .status( status )
                .body( message );
    }

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException e) {
        return createResponse( HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(value = PostNotFoundException.class)
    public ResponseEntity<Object> exception(PostNotFoundException e) {
        return createResponse( HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(value = TaskNotFoundException.class)
    public ResponseEntity<Object> exception(TaskNotFoundException e) {
        return createResponse( HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> exception(BadRequestException e) {
        return createResponse( HttpStatus.BAD_REQUEST, e.getMessage());

    }

    @ExceptionHandler(value = InvalidException.class)
    public ResponseEntity<Object> exception(InvalidException e) {
        return createResponse( HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> exception(UserNotFoundException e) {
        return createResponse( HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(value = NotAuthorizedException.class)
    public ResponseEntity<Object> exception(NotAuthorizedException e ) {
        return createResponse( HttpStatus.UNAUTHORIZED, e.getMessage());
    }
}