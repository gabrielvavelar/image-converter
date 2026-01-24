package io.github.gabrielvavelar.image_converter_api.exception;

public class S3ImageRetriveException extends RuntimeException {
    public S3ImageRetriveException(String message, Throwable cause) {
        super(message, cause);
    }
}
