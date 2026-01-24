package io.github.gabrielvavelar.image_converter_api.dto;

import io.github.gabrielvavelar.image_converter_api.enums.ConversionStatus;
import io.github.gabrielvavelar.image_converter_api.enums.ImageFormat;
import io.github.gabrielvavelar.image_converter_api.model.ImageConversionTask;

import java.util.UUID;

public record ImageConversionsResponseDTO(
        UUID id,
        ConversionStatus status,
        ImageFormat sourceFormat,
        ImageFormat targetFormat,
        String originalName) {

    public static ImageConversionsResponseDTO from(ImageConversionTask task) {
        return new ImageConversionsResponseDTO(
                task.getId(),
                task.getStatus(),
                task.getSourceFormat(),
                task.getTargetFormat(),
                task.getOriginalName()
        );
    }
}
