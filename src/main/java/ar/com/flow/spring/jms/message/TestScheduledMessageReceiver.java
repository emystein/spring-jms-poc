package ar.com.flow.spring.jms.message;

import ar.com.flow.spring.jms.model.MessageObject;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TestScheduledMessageReceiver {
    @JmsListener(destination = "test@example.com", containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(MessageObject message) {
        throw new RuntimeException("Message sent to test@example.com");
    }
}
