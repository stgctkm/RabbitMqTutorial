import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

public class Worker {

    private final static String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(TASK_QUEUE_NAME, false, false, false, null);

        System.out.println(" [*] Waiting for message. To exit press CTRL + C");


        DeliverCallback deliverCallback = ((consumerTag, message) -> {
            String receivedMessage = new String(message.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + receivedMessage + "'");

            try {
                doWork(receivedMessage);
            } finally {
                System.out.println(" [x] Done!");
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            }

        });
        boolean autoAck = true;

        channel.basicConsume(TASK_QUEUE_NAME, autoAck, deliverCallback, consumerTag -> {});
    }

    private static void doWork(String task) {
        for (char ch: task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
//                    throw new RuntimeException(e);
                }
            }
        }
    }
}
