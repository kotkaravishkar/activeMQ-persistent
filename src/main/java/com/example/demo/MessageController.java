package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping("/send")
    public String sendMessage(@RequestBody MessageRequest messageRequest) {
        jmsTemplate.convertAndSend(messageRequest.getQueue(), messageRequest.getMessage());
        return "Message sent to " + messageRequest.getQueue();
    }
}

class MessageRequest {
    private String queue;
    private String message;

    public String getQueue() {
       return this.queue = queue;
    }

    public String getMessage() {
      return  this.message = message;
    }
}
