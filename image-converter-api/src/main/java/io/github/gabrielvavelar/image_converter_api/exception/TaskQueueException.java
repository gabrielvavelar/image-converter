package io.github.gabrielvavelar.image_converter_api.exception;

public class TaskQueueException extends RuntimeException {

    public TaskQueueException(String message) {
        super(message);
    }

    public TaskQueueException(String message, Throwable cause) {
        super(message, cause);
    }
}
