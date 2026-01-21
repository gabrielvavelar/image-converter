package io.github.gabrielvavelar.image_converter_worker.exception;

public class S3ImageRetriveException extends RuntimeException {

    public S3ImageRetriveException(String message, Throwable cause) {
        super(message, cause);
    }
}
