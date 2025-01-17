import javax.jms.*;

public class ClientAcknowledgmentExample {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = factory.createConnection();
        connection.start();

        // Session with CLIENT_ACKNOWLEDGE
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

        // Queue and Message Consumer
        Destination queue = session.createQueue("test.queue");
        MessageConsumer consumer = session.createConsumer(queue);

        // Receive Message
        Message message = consumer.receive();
        if (message instanceof TextMessage) {
            System.out.println("Received: " + ((TextMessage) message).getText());
            // Explicitly acknowledge the message
            message.acknowledge();
        }

        // Cleanup
        consumer.close();
        session.close();
        connection.close();
    }
}
