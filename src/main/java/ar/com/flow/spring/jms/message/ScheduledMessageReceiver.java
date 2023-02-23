package ar.com.flow.spring.jms.message;

import ar.com.flow.spring.jms.model.MessageObject;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ScheduledMessageReceiver {
    @JmsListener(destination = "info@example.com")
    public void receiveMessage(MessageObject message) {
        System.out.println(message.getMessage());
    }
}
