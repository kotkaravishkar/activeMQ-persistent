package com.example.demo;
import jakarta.jms.Message;
import jakarta.jms.Session;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class AcknowledgingListener {

    @JmsListener(destination = "queueOne")
    public void listenQueueOneWithAck(Message message, Session session) {
        try {
            System.out.println("Received message: " + message.getBody(String.class));
            // Perform processing...
            message.acknowledge(); // Manually acknowledge the message
        } catch (Exception e) {
            try {
                session.recover(); // Retry message delivery
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
