package io.github.gabrielvavelar.image_converter_api.service.messaging;

import java.util.UUID;

public interface MessagePublisherService {
    void sendToQueue(UUID taskId);
}
