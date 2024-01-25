package com.example.wordcloudcoreserver.config;

import com.example.wordcloudcoreserver.Constants;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
    @Bean
    public Queue queue() {
        return new Queue(Constants.QUEUE);
    }
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(Constants.EXCHANGE);
    }
    @Bean
    public Binding binding(Queue queue , TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(Constants.ROUTING_KEY);
    }
}