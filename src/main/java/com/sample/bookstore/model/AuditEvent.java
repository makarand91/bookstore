package com.sample.bookstore.model;

import java.util.List;

import lombok.Data;

@Data
public class AuditEvent {
	private BookEntry book;
	private EventType eventType;

}
