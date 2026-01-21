package io.github.gabrielvavelar.image_converter_worker.consumer;

import io.github.gabrielvavelar.image_converter_worker.config.RabbitMQConfig;
import io.github.gabrielvavelar.image_converter_worker.service.ImageConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ImageConversionConsumer {
    private final ImageConversionService service;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void listen(String taskId) {
        service.convertImage(UUID.fromString(taskId));
    }
}
