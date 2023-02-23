package com.hackerrank.scheduling.message;

import com.hackerrank.scheduling.model.MessageObject;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TestScheduledMessageReceiver {
    @JmsListener(destination = "test@example.com")
    public void receiveMessage(MessageObject message) {
        throw new RuntimeException("Message sent to test@example.com");
    }
}
