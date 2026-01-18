package io.github.gabrielvavelar.image_converter_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_NAME = "image.conversion.queue";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }
}
