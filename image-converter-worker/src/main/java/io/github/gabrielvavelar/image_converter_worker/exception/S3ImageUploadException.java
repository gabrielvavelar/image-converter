package io.github.gabrielvavelar.image_converter_worker.exception;

public class S3ImageUploadException extends RuntimeException {
    public S3ImageUploadException(String message) {
        super(message);
    }
    public S3ImageUploadException(String message, Throwable cause) {
        super(message, cause);
    }
}
