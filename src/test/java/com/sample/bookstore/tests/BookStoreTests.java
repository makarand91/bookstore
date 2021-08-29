package com.sample.bookstore.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.bookstore.BookStoreApp;
import com.sample.bookstore.model.BookEntry;
import com.sample.bookstore.model.SeartchCriteria;
import com.sample.bookstore.service.BookStoreService;

@SpringBootTest(classes = BookStoreApp.class, properties = {
		"spring.jpa.properties.hibernate.enable_lazy_load_no_trans:true" })
@DirtiesContext
@RunWith(SpringRunner.class)
public class BookStoreTests {

	@Autowired
	private BookStoreService service;

	@Test
	public void bookStroreCrudOperations() {
		BookEntry entry = new BookEntry();
		entry.setAuthor("Rudyard kp");
		entry.setIsbn("1234512345124");
		entry.setPublicationdate(new Date());
		entry.setTitle("The Jungle Book");

		service.createNewBook(entry);

		List<BookEntry> book = service.searchBook(SeartchCriteria.ISBN, "1234512345124");

		assertNotNull(book);
		assertNotNull(book.get(0));
		Hibernate.initialize(book.get(0));
		assertEquals(book.get(0).getIsbn(), "1234512345124");
		assertEquals(book.get(0).getAuthor(), "Rudyard kp");
		assertEquals(book.get(0).getTitle(), "The Jungle Book");

		service.deleteBookByISBN("1234512345124");

		List<BookEntry> book1 = service.searchBook(SeartchCriteria.ISBN, "1234512345124");
		assertNull(book1);


	}

}
