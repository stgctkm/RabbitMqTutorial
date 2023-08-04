package producer;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicsConfig {

    @Bean
    public TopicExchange topic() {
        return new TopicExchange("tutorial5.topic");
    }

}