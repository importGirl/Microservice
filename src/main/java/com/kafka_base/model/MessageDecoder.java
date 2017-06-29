package com.kafka_base.model;

import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * @author wangdg
 * @ClassName:
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class MessageDecoder implements Deserializer<Message> {

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public Message deserialize(String s, byte[] bytes) {
        Message message = new Message();
        return message.toDocument(bytes);
    }

    @Override
    public void close() {

    }
}
