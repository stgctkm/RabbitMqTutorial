package consumer;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkQueuesConfig {

    // Queueがなければ作成する.
    // @RabbitListenerで指定しているqueueを作成する
    // Producer側で作成、あらかじめ作成（GUI、CUI）でもOK
    @Bean
    public Queue hello() {
        return new Queue("amqp-hello");
    }

    @Bean
    Receiver receiver1() {
        return new Receiver(1);
    }

    @Bean
    Receiver receiver2() {
        return new Receiver(2);
    }
}