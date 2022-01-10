package nl.mengelmoestuintjes.gardening.controller.exceptions;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(long id) {
        super("Task with id " + id + " not found");
    }
    public TaskNotFoundException() {
        super("Cannot find task");
    }
}
