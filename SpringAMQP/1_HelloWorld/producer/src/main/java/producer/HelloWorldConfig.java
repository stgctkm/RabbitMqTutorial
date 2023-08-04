package producer;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Profile({"tut1","hello-world"})
@Configuration
public class HelloWorldConfig {

    @Bean
    public Queue hello() {
        return new Queue("amqp-hello");
    }

//    @Profile("sender")
//    @Bean
//    public Sender sender() {
//        return new Sender();
//    }
}