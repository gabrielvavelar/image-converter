package io.github.gabrielvavelar.image_converter_worker.model;

import io.github.gabrielvavelar.image_converter_worker.enums.ConversionStatus;
import io.github.gabrielvavelar.image_converter_worker.enums.ImageFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class ImageConversionTask {

    @Id
    private UUID id;

    private String originalName;
    private String inputPath;
    private String outputPath;

    @Enumerated(EnumType.STRING)
    private ImageFormat sourceFormat;

    @Enumerated(EnumType.STRING)
    private ImageFormat targetFormat;

    @Enumerated(EnumType.STRING)
    private ConversionStatus status;
}