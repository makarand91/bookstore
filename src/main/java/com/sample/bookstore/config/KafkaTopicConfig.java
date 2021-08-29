package com.sample.bookstore.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {
	@Value("${kafka.partition.factor:3}")
	private int kafkaPartitionFactor;

	@Value("${kafka.replication.factor:1}")
	private short kafkaReplicationFactor;

	@Value("${audit.event.topic:sample.audit.event}")
	private String eventTopic;

	@Bean
	public NewTopic sampleTopic() {
		return new NewTopic(eventTopic, kafkaPartitionFactor, kafkaReplicationFactor);
	}
}
