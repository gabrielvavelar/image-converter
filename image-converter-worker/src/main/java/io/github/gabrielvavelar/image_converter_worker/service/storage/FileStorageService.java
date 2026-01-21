package io.github.gabrielvavelar.image_converter_worker.service.storage;

import io.github.gabrielvavelar.image_converter_worker.enums.ImageFormat;

import java.io.IOException;
import java.util.UUID;
import java.io.InputStream;

public interface FileStorageService {
    InputStream loadImage(UUID id, ImageFormat extension);
}
