package com.example.demo;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class Config {

    private static final String BROKER_URL = "tcp://localhost:61616"; // ActiveMQ broker URL
    private static final String DEFAULT_QUEUE = "demo-queue"; // Default queue name

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        // Creates a connection factory to connect to ActiveMQ broker
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(BROKER_URL);
        return factory;
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory(ActiveMQConnectionFactory factory) {
        // Wraps the ActiveMQ connection factory for better performance
        return new CachingConnectionFactory(factory);
    }

    @Bean
    public JmsTemplate jmsTemplate(CachingConnectionFactory connectionFactory) {
        // JmsTemplate simplifies sending messages to ActiveMQ
        JmsTemplate template = new JmsTemplate(connectionFactory);
        template.setDefaultDestinationName(DEFAULT_QUEUE);
        return template;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(CachingConnectionFactory connectionFactory) {
        // Configures message listeners to handle different acknowledgment modes
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setSessionAcknowledgeMode(2); // AUTO_ACKNOWLEDGE mode
        return factory;
    }
}
