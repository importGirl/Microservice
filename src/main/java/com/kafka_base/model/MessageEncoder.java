package com.kafka_base.model;


import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * @author wangdg
 * @ClassName:
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class MessageEncoder implements Serializer<Message> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, Message message) {
        return message.toBytes();
    }

    @Override
    public void close() {

    }
}
