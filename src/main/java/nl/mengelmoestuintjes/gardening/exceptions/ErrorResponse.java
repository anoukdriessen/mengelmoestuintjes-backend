package nl.mengelmoestuintjes.gardening.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponse {

    private HttpStatus status;
    private String body;
    private LocalDateTime timeStamp;

    public ErrorResponse(HttpStatus status, String body, LocalDateTime timeStamp) {
        this.status = status;
        this.body = body;
        this.timeStamp = timeStamp;
    }

    public HttpStatus getStatus() {
        return status;
    }
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
    public String getBody() {
        return body + " " + this.getTimeStamp();
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
