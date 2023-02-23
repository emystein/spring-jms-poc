package ar.com.flow.spring.jms;

import ar.com.flow.spring.jms.message.ScheduledMessageErrorHandler;
import ar.com.flow.spring.jms.message.ScheduledMessageReceiver;
import ar.com.flow.spring.jms.message.ScheduledMessageSender;
import ar.com.flow.spring.jms.model.MessageObject;
import org.awaitility.Duration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ApplicationTests {
    @SpyBean
    ScheduledMessageSender scheduledMessageSender;
    @SpyBean
    ScheduledMessageReceiver scheduledMessageReceiver;
    @SpyBean
    ScheduledMessageErrorHandler scheduledMessageErrorHandler;

    @Test
    public void checkEnableScheduling() {
        boolean hasAnnotation = false;

        Annotation annotation = AnnotationUtils.findAnnotation(Application.class, EnableScheduling.class);
        if (annotation != null) {
            hasAnnotation = true;
        }

        assertTrue(hasAnnotation);
    }

    @Test
    public void checkScheduledAnnotation() {
        boolean hasAnnotation = false;

        for (Method method : ScheduledMessageSender.class.getDeclaredMethods()) {
            Annotation annotation = AnnotationUtils.findAnnotation(method, Scheduled.class);
            if (annotation != null) {
                hasAnnotation = true;
                break;
            }
        }

        assertTrue(hasAnnotation);
    }


    @Test
    public void checkSendingInfoMessage() {
        await().atMost(Duration.TEN_SECONDS).untilAsserted(() -> {
            verify(scheduledMessageSender, atLeast(2)).sendingInfoMessage();
        });
    }

    @Test
    public void checkSendingTestMessage() {
        await().atMost(Duration.TEN_SECONDS).untilAsserted(() -> {
            verify(scheduledMessageSender, atLeast(1)).sendingTestMessage();
        });
    }

    @Test
    public void checkEnableJms() {
        boolean hasAnnotation = false;

        Annotation annotation = AnnotationUtils.findAnnotation(Application.class, EnableJms.class);
        if (annotation != null) {
            hasAnnotation = true;
        }

        assertTrue(hasAnnotation);
    }

    @Test
    public void checkReceivingMessage() {
        await().atMost(new Duration(20, TimeUnit.SECONDS)).untilAsserted(() -> {
            verify(scheduledMessageReceiver, atLeast(2)).receiveMessage(any(MessageObject.class));
        });
    }

    @Test
    public void checkingErrorHandler() {
        await().atMost(new Duration(20, TimeUnit.SECONDS)).untilAsserted(() -> {
            verify(scheduledMessageErrorHandler, atLeast(1)).handleError(any());
        });
    }
}