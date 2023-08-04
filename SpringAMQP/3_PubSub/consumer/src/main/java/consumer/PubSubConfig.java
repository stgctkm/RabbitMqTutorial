package consumer;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.BeanInfo;

@Configuration
public class PubSubConfig {

    // Exchangeがなければ作成する.
    // Producer側で作成、あらかじめ作成（GUI、CUI）でもOK
    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("tutorial3.fanout");
    }

    @Bean
    public Queue autoDeleteQueue1() {
        return new Queue("sub-1"); // named queue will not be deleted
//        return new AnonymousQueue(); // anonymous queue will be deleted on end of process
    }

    @Bean Queue autoDeleteQueue2() {
        return new Queue("sub-2");
//        return new AnonymousQueue();
    }

    @Bean
    public Binding binding1(FanoutExchange fanout, Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1).to(fanout);
    }

    @Bean
    public Binding binding2(FanoutExchange fanout, Queue autoDeleteQueue2) {
        return BindingBuilder.bind(autoDeleteQueue2).to(fanout);
    }

}