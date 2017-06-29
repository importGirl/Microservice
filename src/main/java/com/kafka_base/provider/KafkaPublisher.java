package com.kafka_base.provider;

import com.kafka_base.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author wangdg
 * @ClassName:
 * @Description:
 * @date 2017-06-11 00:50:22
 */
@Component
public class KafkaPublisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaPublisher.class);

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;


    public void sendMessage(String topic, Message m) {
        ListenableFuture<SendResult<String, Message>> future = kafkaTemplate
                .send(topic, m);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Message>>() {
            @Override
            public void onSuccess(SendResult<String, Message> result) {
                LOGGER.info("sent message='{}' with offset={}",m.toString(),result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                LOGGER.error("unable to send message='{}'",m.toString(), ex);
            }
        });
    }
}
