package com.hackerrank.scheduling.message;

import com.hackerrank.scheduling.model.MessageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ScheduledMessageReceiver {
    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "info@example.com")
    public void receiveMessage(MessageObject message) {
//        if message.getToAddress().equals("")
        System.out.println(message.getMessage());
    }
}
