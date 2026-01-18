package io.github.gabrielvavelar.image_converter_api.factory;

import io.github.gabrielvavelar.image_converter_api.dto.ImageConversionRequestDTO;
import io.github.gabrielvavelar.image_converter_api.enums.ConversionStatus;
import io.github.gabrielvavelar.image_converter_api.model.ImageConversionTask;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ImageConversionTaskFactory {
    public ImageConversionTask createImageConversionTask(
            ImageConversionRequestDTO requestDTO,
            UUID taskId,
            String inputPath) {

        ImageConversionTask conversionTask = new ImageConversionTask();
        conversionTask.setId(taskId);
        conversionTask.setOriginalName(requestDTO.imageFile().getOriginalFilename());
        conversionTask.setInputPath(inputPath);
        conversionTask.setOutputPath(null);
        conversionTask.setSourceFormat(requestDTO.sourceFormat());
        conversionTask.setTargetFormat(requestDTO.targetFormat());
        conversionTask.setStatus(ConversionStatus.PENDING);

        return conversionTask;
    }
}