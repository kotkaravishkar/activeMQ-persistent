import javax.jms.*;

public class MessageProducerExample {
    public static void main(String[] args) throws Exception {
        // Connection Factory
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = factory.createConnection();
        connection.start();

        // Create a session (false means no transaction)
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create a queue
        Destination queue = session.createQueue("test.queue");

        // Create a message producer
        MessageProducer producer = session.createProducer(queue);

        // Send messages
        for (int i = 1; i <= 5; i++) {
            String text = "Message " + i;
            TextMessage message = session.createTextMessage(text);
            producer.send(message);
            System.out.println("Sent: " + text);
        }

        // Cleanup
        producer.close();
        session.close();
        connection.close();
    }
}
