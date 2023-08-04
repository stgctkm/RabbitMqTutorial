package producer;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkQueuesConfig {

    @Bean
    public Queue hello() {
        return new Queue("amqp-hello");
    }

}