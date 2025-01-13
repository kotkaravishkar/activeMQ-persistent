package com.example.demo;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.jms.Queue;

@Configuration
public class Config {

    @Bean
    public Queue queueOne() {
        return new ActiveMQQueue("queueOne");
    }

    @Bean
    public Queue queueTwo() {
        return new ActiveMQQueue("queueTwo");
    }
}
