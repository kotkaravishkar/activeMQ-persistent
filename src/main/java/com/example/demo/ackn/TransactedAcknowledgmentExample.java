import javax.jms.*;

public class TransactedAcknowledgmentExample {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = factory.createConnection();
        connection.start();

        // Transacted session (true means transaction enabled)
        Session session = connection.createSession(true, Session.SESSION_TRANSACTED);

        // Queue and Message Consumer
        Destination queue = session.createQueue("test.queue");
        MessageConsumer consumer = session.createConsumer(queue);

        // Receive Message
        try {
            Message message = consumer.receive();
            if (message instanceof TextMessage) {
                System.out.println("Received: " + ((TextMessage) message).getText());
                // Commit the transaction (acknowledges the message)
                session.commit();
            }
        } catch (Exception e) {
            // Rollback if an error occurs
            session.rollback();
            e.printStackTrace();
        }

        // Cleanup
        consumer.close();
        session.close();
        connection.close();
    }
}
