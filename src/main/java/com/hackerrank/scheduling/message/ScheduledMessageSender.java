package com.hackerrank.scheduling.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledMessageSender {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Scheduled(fixedDelay = 5000)
    public void sendingInfoMessage() {
        jmsTemplate.convertAndSend("info@example.com", "info");
    }

    @Scheduled(fixedDelay = 8000)
    public void sendingTestMessage() {
        jmsTemplate.convertAndSend("test@example.com", "test");
    }
}
