package com.kafka_base;

import com.kafka_base.model.Message;
import com.kafka_base.provider.KafkaPublisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * Hello world!
 *
 */
@Configuration
@SpringBootApplication
@AutoConfigurationPackage
@EnableAutoConfiguration
//@Configuration
@ComponentScan
public class App{

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext app = SpringApplication.run(App.class, args);

        KafkaPublisher sender = app.getBean(KafkaPublisher.class);
        sender.sendMessage("myTopic",new Message(1L,"发送成功",new Date()));

        while(true){
            sender.sendMessage("myTopic",new Message(1L,"发送成功",new Date()));
            Thread.sleep(200);
        }
    }
}