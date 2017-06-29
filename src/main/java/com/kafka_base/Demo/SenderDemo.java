package com.kafka_base.Demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wangdg
 * @ClassName:
 * @Description:
 * @date 2017-06-11 00:50:22
 */
@Component
public class SenderDemo {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendMessage(){
//        Message m = new Message();
//        m.setId(System.currentTimeMillis());
//        m.setMsg(UUID.randomUUID().toString());
//        m.setSendTime(new Date());
        kafkaTemplate.send("test-topic", "hello! i am wdg");

    }
}
