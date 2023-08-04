package consumer;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutingConfig {

    // Exchangeがなければ作成する.
    // Producer側で作成、あらかじめ作成（GUI、CUI）でもOK
    @Bean
    public DirectExchange direct() {
        return new DirectExchange("tutorial4.direct");
    }

    @Bean
    public Queue autoDeleteQueue1() {
//        return new Queue("routing-1"); // named queue will not be deleted
        return new AnonymousQueue(); // anonymous queue will be deleted on end of process
    }

    @Bean Queue autoDeleteQueue2() {
//        return new Queue("routing-2");
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding1a(DirectExchange direct, Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1)
                .to(direct)
                .with("orange");
    }

    @Bean
    public Binding binding1b(DirectExchange direct, Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1)
                .to(direct)
                .with("black");
    }

    @Bean
    public Binding binding2a(DirectExchange direct, Queue autoDeleteQueue2) {
        return BindingBuilder.bind(autoDeleteQueue2)
                .to(direct)
                .with("green");
    }

    @Bean
    public Binding binding2b(DirectExchange direct, Queue autoDeleteQueue2) {
        return BindingBuilder.bind(autoDeleteQueue2)
                .to(direct)
                .with("black");
    }

}