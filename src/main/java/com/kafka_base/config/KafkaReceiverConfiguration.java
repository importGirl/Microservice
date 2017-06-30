package com.kafka_base.config;

import com.kafka_base.Demo.Listener;
import com.kafka_base.consumer.KafkaReceiver;
import com.kafka_base.model.MessageDecoder;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangdg
 * @ClassName: KafkaReceiverConfiguration
 * @Description: kafka消费者
 * @date 2017-06-11 00:50:22
 */
@Configuration
@EnableKafka
public class KafkaReceiverConfiguration {

	@Value("${kafka.bootstrap.servers}")
	private String bootstrapServers;

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				MessageDecoder.class);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
//		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "audit");
		return props;
	}

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.setConcurrency(3);
		factory.getContainerProperties().setAckMode(
				AbstractMessageListenerContainer.AckMode.MANUAL_IMMEDIATE);

		/*
		 * factory.setAckDiscarded(true); factory.setRecordFilterStrategy(new
		 * RecordFilterStrategy(){
		 * 
		 * @Override public boolean filter(ConsumerRecord consumerRecord) {
		 * return false; } });
		 */

		factory.setConcurrency(3);
		return factory;
	}

	@Bean
	public Listener listener () {
		return new Listener();
	}

	@Bean
	public KafkaReceiver kafkaReceiver(){
		return new KafkaReceiver();
	}
}
