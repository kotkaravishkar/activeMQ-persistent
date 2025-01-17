package com.example.demo.ackn;
}
import javax.jms.*;

public class AutoAcknowledgmentExample {
    public static void main(String[] args) throws Exception {
        // Connection Factory
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = factory.createConnection();
        connection.start();

        // Session with AUTO_ACKNOWLEDGE
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Queue and Message Consumer
        Destination queue = session.createQueue("test.queue");
        MessageConsumer consumer = session.createConsumer(queue);

        // Receive Message
        Message message = consumer.receive();
        if (message instanceof TextMessage) {
            System.out.println("Received: " + ((TextMessage) message).getText());
        }

        // Cleanup
        consumer.close();
        session.close();
        connection.close();
    }
}
