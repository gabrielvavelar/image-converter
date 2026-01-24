package io.github.gabrielvavelar.image_converter_api.service;

import io.github.gabrielvavelar.image_converter_api.dto.ImageConversionRequestDTO;
import io.github.gabrielvavelar.image_converter_api.enums.ImageFormat;
import io.github.gabrielvavelar.image_converter_api.factory.ImageConversionTaskFactory;
import io.github.gabrielvavelar.image_converter_api.repository.ImageConversionTaskRepository;
import io.github.gabrielvavelar.image_converter_api.service.messaging.MessagePublisherService;
import io.github.gabrielvavelar.image_converter_api.service.storage.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageConversionService {

    private final ImageConversionTaskRepository repository;
    private final FileStorageService storageService;
    private final ImageConversionTaskFactory factory;
    private final MessagePublisherService messagePublisherService;

    @Transactional
    public void convertImage(ImageConversionRequestDTO requestDTO) {

        UUID taskId = UUID.randomUUID();

        String inputPath = storageService.saveImage(requestDTO.imageFile(), taskId, requestDTO.sourceFormat());

        repository.save(factory.createImageConversionTask(requestDTO, taskId, inputPath));

        messagePublisherService.sendToQueue(taskId);

    }

    public InputStream loadConvertedImage(UUID id, ImageFormat format) {
        return storageService.loadImage(id, format);
    }

}
