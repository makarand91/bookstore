package com.sample.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.bookstore.model.Author;
import com.sample.bookstore.model.Book;
import com.sample.bookstore.model.BookEntry;
import com.sample.bookstore.model.EventType;
import com.sample.bookstore.model.SeartchCriteria;
import com.sample.bookstore.repository.AuditService;
import com.sample.bookstore.repository.AuthorRepository;
import com.sample.bookstore.repository.BookRepository;
import com.sample.bookstore.utils.BookStroreUtils;
import com.sample.bookstore.utils.ValidationUtils;

@Service
public class BookStoreService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private AuditService auditService;

	public boolean createNewBook(BookEntry bookEntry) {

		Book book = BookStroreUtils.entryToBook(bookEntry);
		List<Book> alreadyexists = bookRepository.findByIsbn(bookEntry.getIsbn());
		if (alreadyexists == null || alreadyexists.size() == 0) {

			bookRepository.save(book);

			auditService.generateAuditEvent(EventType.ADD, bookEntry);
			return true;
		}
		return false;
	}

	public boolean updateBook(BookEntry bookEntry) {

		Book book = new Book(bookEntry.getTitle(), bookEntry.getPublicationdate(), bookEntry.getIsbn());
		bookRepository.save(book);
		String authors = bookEntry.getAuthor();
		if (authors != null) {

			List<Author> authorlist = Stream.of(authors.split(",")).map(elem -> new Author(elem))
					.collect(Collectors.toList());
			authorRepository.saveAll(authorlist);
			book.getAuthor().addAll(authorlist);
			bookRepository.save(book);
			auditService.generateAuditEvent(EventType.UPDATE, bookEntry);
		}

		return true;
	}

	private List<BookEntry> getBookByISBN(String isbn) {

		List<Book> books = bookRepository.findByIsbn(isbn);

		if (books != null && books.size() > 0) {
			return books.stream().map(book -> BookStroreUtils.bookToEntry(book)).collect(Collectors.toList());

		}

		return null;
	}

	private List<BookEntry> getBookByAuthor(String authorname) {

		List<Author> authors = authorRepository.findByName(authorname);

		if (authors != null && authors.size() > 0) {
			List<BookEntry> bookentry = authors.stream().flatMap(author -> author.getBook().stream())
					.map(book -> BookStroreUtils.bookToEntry(book)).collect(Collectors.toList());
			return bookentry;

		}

		return null;
	}

	public List<BookEntry> searchBook(SeartchCriteria searchCriteria, String value) {

		switch (searchCriteria) {
		case AUTHOR:
			return getBookByAuthor(value);
		case ISBN:
			return getBookByISBN(value);

		case TITLE:
			return getBookByTitle(value);

		}

		return null;
	}

	private List<BookEntry> getBookByTitle(String value) {
		List<Book> books = bookRepository.findByTitle(value);

		if (books != null && books.size() > 0) {
			return books.stream().map(book -> BookStroreUtils.bookToEntry(book)).collect(Collectors.toList());

		}

		return null;
	}

	public boolean deleteBookByISBN(String isbn) {

		List<Book> books = bookRepository.findByIsbn(isbn);

		if (books != null && books.size() > 0) {
			// Set<Author> authors = books.get(0).getAuthor();
			// authors.forEach(author -> authorRepository.delete(author));
			bookRepository.delete(books.get(0));
			auditService.generateAuditEvent(EventType.DELETE, BookStroreUtils.bookToEntry(books.get(0)));
			return true;

		}

		return false;
	}

	public boolean updateBook(String isbn, BookEntry entry) {

		List<Book> books = bookRepository.findByIsbn(isbn);
		Book inbook = BookStroreUtils.entryToBook(entry);

		if (books != null && books.size() > 0) {
			books.get(0).setTitle(inbook.getTitle());
			books.get(0).setPublicationdate(inbook.getPublicationdate());
			books.get(0).setAuthor(inbook.getAuthor());

			bookRepository.save(books.get(0));
			return true;
		}

		return false;
	}

}
