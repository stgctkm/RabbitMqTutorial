package producer;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PubSubConfig {

    @Bean
    FanoutExchange fanout() {
        return new FanoutExchange("tutorial3.fanout");
    }

}