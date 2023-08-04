package producer;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
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
    DirectExchange direct;

    AtomicInteger index = new AtomicInteger(0);
    AtomicInteger count = new AtomicInteger(0);

    private final String[] keys = {"orange", "black", "green"};

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        StringBuilder builder = new StringBuilder("Routing ");

        if (this.index.incrementAndGet() == 3) {
            this.index.set(0);
        }

        String routingKey = keys[index.get()];

        builder.append(routingKey).append(' ');
        builder.append(count.get());

        String message = builder.toString();
        template.convertAndSend(direct.getName(), routingKey, message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}