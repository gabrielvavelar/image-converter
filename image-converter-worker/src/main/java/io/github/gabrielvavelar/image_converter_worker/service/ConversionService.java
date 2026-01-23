package io.github.gabrielvavelar.image_converter_worker.service;

import io.github.gabrielvavelar.image_converter_worker.enums.ConversionStatus;
import io.github.gabrielvavelar.image_converter_worker.exception.ImageConversionException;
import io.github.gabrielvavelar.image_converter_worker.exception.ImageConversionTaskNotFoundException;
import io.github.gabrielvavelar.image_converter_worker.model.ImageConversionTask;
import io.github.gabrielvavelar.image_converter_worker.repository.ImageConversionTaskRepository;
import io.github.gabrielvavelar.image_converter_worker.service.storage.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConversionService {
    private final ImageConversionTaskRepository repository;
    private final FileStorageService storageService;
    private final ImageConverterService imageConverterService;

    public void convertImage(UUID taskId) {
        ImageConversionTask conversionTask = repository.findById(taskId)
                .orElseThrow(() -> new ImageConversionTaskNotFoundException("Task Not Found"));

        if(conversionTask.getStatus() != ConversionStatus.PENDING) {
            return;
        }

        conversionTask.setStatus(ConversionStatus.PROCESSING);
        repository.save(conversionTask);

        try(InputStream image = storageService
                .loadImage(conversionTask.getId(), conversionTask.getSourceFormat());) {

            byte[] convertedImage = imageConverterService
                    .convertImage(image, conversionTask.getTargetFormat());

            storageService.storeImage(convertedImage, conversionTask.getId(), conversionTask.getTargetFormat());

            conversionTask.setStatus(ConversionStatus.COMPLETED);
            repository.save(conversionTask);

        }
        catch (ImageConversionException | IOException e) {

            conversionTask.setStatus(ConversionStatus.FAILED);
            repository.save(conversionTask);
            throw new ImageConversionException("Image Conversion Failed", e);

        }
    }
}
