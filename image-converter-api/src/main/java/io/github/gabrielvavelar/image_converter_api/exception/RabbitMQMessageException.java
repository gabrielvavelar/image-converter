package io.github.gabrielvavelar.image_converter_api.exception;

public class RabbitMQMessageException extends TaskQueueException{

    public RabbitMQMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
