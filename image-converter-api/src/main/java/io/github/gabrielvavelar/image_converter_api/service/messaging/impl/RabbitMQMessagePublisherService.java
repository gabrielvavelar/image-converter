package io.github.gabrielvavelar.image_converter_api.service.messaging.impl;

import io.github.gabrielvavelar.image_converter_api.config.RabbitMQConfig;
import io.github.gabrielvavelar.image_converter_api.exception.RabbitMQMessageException;
import io.github.gabrielvavelar.image_converter_api.service.messaging.MessagePublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RabbitMQMessagePublisherService implements MessagePublisherService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendToQueue(UUID taskId) {
        try {
            rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, taskId.toString());
        } catch (AmqpException e) {
            throw new RabbitMQMessageException("Failed to send message to RabbitMQ queue"
                    + RabbitMQConfig.QUEUE_NAME,
                    e
            );
        }
    };
}
