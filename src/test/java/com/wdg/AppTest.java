package com.wdg;

import com.kafka_base.Demo.SenderDemo;
import com.kafka_base.common.FileUploadUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Unit test for simple App.
 */
public class AppTest extends BaseTest{

    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Autowired
    private SenderDemo sender;
    @Autowired
    private FileUploadUtil fileUploadUtil;
    @Test
    public void test1() throws Exception {
        kafkaTemplate.send("test-topic","hello");
    }

    @Test
    public void test2() throws Exception {
        fileUploadUtil.doSender();
        System.out.println("第二个---------->"+sender);
    }
}
