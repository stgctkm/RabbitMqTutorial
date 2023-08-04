package producer;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Sender {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    TopicExchange topic;

    AtomicInteger index = new AtomicInteger(0);
    AtomicInteger count = new AtomicInteger(0);

    private final String[] keys = {
            "quick.orange.rabbit", "lazy.orange.elephant", "quick.orange.fox",
            "lazy.brown.fox", "lazy.pink.rabbit", "quick.brown.fox"};

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        StringBuilder builder = new StringBuilder("Topics ");

        if (this.index.incrementAndGet() == keys.length) {
            this.index.set(0);
        }

        String routingKey = keys[index.get()];

        builder.append(routingKey).append(' ');
        builder.append(count.incrementAndGet());

        String message = builder.toString();
        template.convertAndSend(topic.getName(), routingKey, message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}