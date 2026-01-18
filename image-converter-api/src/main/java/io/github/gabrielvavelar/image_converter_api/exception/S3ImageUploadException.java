package io.github.gabrielvavelar.image_converter_api.exception;

public class S3ImageUploadException extends ImageSaveException{
    public S3ImageUploadException(String message, Throwable cause) {
        super(message, cause);
    }
}
