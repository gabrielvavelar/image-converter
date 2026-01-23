package io.github.gabrielvavelar.image_converter_worker.exception;

public class ImageConversionException extends RuntimeException {
    public ImageConversionException(String message) {
        super(message);
    }

    public ImageConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}
