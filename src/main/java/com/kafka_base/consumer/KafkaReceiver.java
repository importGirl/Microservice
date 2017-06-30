package com.kafka_base.consumer;

import com.kafka_base.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangdg
 * @ClassName:
 * @Description:
 * @date 2017-06-11 00:50:22
 */
@Component
public class KafkaReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaReceiver.class);
    private static final String SIGNUP_TOPIC = "myTopic";

    private CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = SIGNUP_TOPIC)
    public void receiveSignupDetails(Message m) {
        System.out.println("---------------------->" + m);
        LOGGER.info("received Sign-up Details='{}'", m.toString());
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
