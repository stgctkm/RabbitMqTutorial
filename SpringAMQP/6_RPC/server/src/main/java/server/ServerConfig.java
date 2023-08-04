package server;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {

    // Exchangeがなければ作成する.
    // Producer側で作成、あらかじめ作成（GUI、CUI）でもOK
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("tutorial6.rpc");
    }

    @Bean
    public Queue requestQueue() {
        return new Queue("tutorial6.rpc.requests");
    }

    @Bean
    public Binding binding(DirectExchange exchange, Queue requestQueue) {
        return BindingBuilder.bind(requestQueue)
                .to(exchange)
                .with("rpc");
    }

}