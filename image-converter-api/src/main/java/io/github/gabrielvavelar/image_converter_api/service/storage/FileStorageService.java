package io.github.gabrielvavelar.image_converter_api.service.storage;

import io.github.gabrielvavelar.image_converter_api.enums.ImageFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FileStorageService {

    String saveImage(MultipartFile file, UUID id, ImageFormat extension);
}
