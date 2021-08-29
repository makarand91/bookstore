package com.sample.bookstore.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.sample.bookstore.model.AuditEvent;
import com.sample.bookstore.model.BookEntry;
import com.sample.bookstore.model.EventType;
import com.sample.bookstore.utils.BookStroreUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuditService {

	@Autowired
	KafkaTemplate<String, AuditEvent> kafkaTemplate;

	@Value("${audit.event.topic:sample.audit.event}")
	private String eventTopic;

	public void generateAuditEvent(EventType type, BookEntry entry) {

		log.info("Sending audit event to kafka event " +type);
		AuditEvent event = BookStroreUtils.entryToAudit(type, entry);

		kafkaTemplate.send(eventTopic,entry.getIsbn(), event);

	}

}
