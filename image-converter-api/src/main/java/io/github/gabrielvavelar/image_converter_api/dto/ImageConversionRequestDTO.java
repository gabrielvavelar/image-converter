package io.github.gabrielvavelar.image_converter_api.dto;

import io.github.gabrielvavelar.image_converter_api.enums.ImageFormat;
import org.springframework.web.multipart.MultipartFile;

public record ImageConversionRequestDTO(

        MultipartFile imageFile,
        ImageFormat sourceFormat,
        ImageFormat targetFormat
){}