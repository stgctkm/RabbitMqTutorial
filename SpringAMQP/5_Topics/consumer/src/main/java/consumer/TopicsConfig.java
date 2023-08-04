package consumer;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicsConfig {

    // Exchangeがなければ作成する.
    // Producer側で作成、あらかじめ作成（GUI、CUI）でもOK
    @Bean
    public TopicExchange topic() {
        return new TopicExchange("tutorial5.topic");
    }

    @Bean
    public Queue autoDeleteQueue1() {
        return new AnonymousQueue(); // anonymous queue will be deleted on end of process
    }

    @Bean Queue autoDeleteQueue2() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding1a(TopicExchange topic, Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1)
                .to(topic)
                .with("*.orange.*");
    }

    @Bean
    public Binding binding1b(TopicExchange topic, Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1)
                .to(topic)
                .with("*.*.rabbit");
    }

    @Bean
    public Binding binding2a(TopicExchange topic, Queue autoDeleteQueue2) {
        return BindingBuilder.bind(autoDeleteQueue2)
                .to(topic)
                .with("lazy.#");
    }


}