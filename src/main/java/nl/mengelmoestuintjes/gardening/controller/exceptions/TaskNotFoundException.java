package nl.mengelmoestuintjes.gardening.controller.exceptions;

import nl.mengelmoestuintjes.gardening.model.Task;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(long id) {
        super("Task with id " + id + " not found");
    }
    public TaskNotFoundException(Task task) {
        super("Task " + task + " not found");
    }
    public TaskNotFoundException() {
        super("Cannot find task");
    }
}
