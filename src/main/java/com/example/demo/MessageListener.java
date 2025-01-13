package com.example.demo;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @JmsListener(destination = "queueOne")
    public void listenQueueOne(String message) {
        System.out.println("Received message from queueOne: " + message);
    }

    @JmsListener(destination = "queueTwo")
    public void listenQueueTwo(String message) {
        System.out.println("Received message from queueTwo: " + message);
    }
}
