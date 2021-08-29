package com.sample.bookstore.service;

import java.io.IOException;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sample.bookstore.model.AuditEvent;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class AuditConsumerService {

	@KafkaListener(topics = "${audit.event.topic}", groupId = "${kafka.groupid}", concurrency = "${kafka.topic.concurrency:1}")
	public void processCompany(List<AuditEvent> contents,
			@Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
			@Header(KafkaHeaders.OFFSET) List<Long> offsets)
			throws JsonParseException, JsonMappingException, IOException {

		for (int i = 0; i < contents.size(); i++) {
			log.info("Audit event received : " + contents.get(0));
		}

	}

}
