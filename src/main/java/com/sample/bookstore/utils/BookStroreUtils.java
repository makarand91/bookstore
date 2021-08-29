package com.sample.bookstore.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sample.bookstore.model.AuditEvent;
import com.sample.bookstore.model.Author;
import com.sample.bookstore.model.Book;
import com.sample.bookstore.model.BookEntry;
import com.sample.bookstore.model.EventType;

public class BookStroreUtils {

	public static BookEntry bookToEntry(Book book) {
		String authorString = "";
		if (book.getAuthor() != null) {
			authorString = book.getAuthor().stream().map(au -> au.getName()).collect(Collectors.joining(","));
		}

		BookEntry entry = new BookEntry(book.getTitle(), authorString, book.getIsbn(), book.getPublicationdate());

		return entry;
	}

    public static AuditEvent entryToAudit(EventType eventtype, BookEntry entry)
    {
    	AuditEvent event = new AuditEvent();
    	event.setEventType(eventtype);
    	event.setBook(entry);
    	return event;
    }
	
	
	public static Book entryToBook(BookEntry bookEntry) {
		Book book = new Book(bookEntry.getTitle(), bookEntry.getPublicationdate(), bookEntry.getIsbn());

		String authors = bookEntry.getAuthor();
		if (authors != null) {

			List<Author> authorlist = Stream.of(authors.split(",")).map(elem -> new Author(elem))
					.collect(Collectors.toList());

			book.getAuthor().addAll(authorlist);

		}

		return book;
	}

}
