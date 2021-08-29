package com.sample.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.bookstore.model.BookEntry;
import com.sample.bookstore.model.SeartchCriteria;
import com.sample.bookstore.service.BookStoreService;
import com.sample.bookstore.utils.ValidationUtils;

@RestController
@RequestMapping("bookstore")
public class BookStroreController {
	@Autowired
	private BookStoreService bookstoreService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<String> createBook(@RequestBody BookEntry bookentry) {

		try {
			if (ValidationUtils.isValidISBN(bookentry)) {
				boolean created = bookstoreService.createNewBook(bookentry);
				if (created)
					return ResponseEntity.status(201).body("created");
				else
					return ResponseEntity.status(409).body("book already exists");
			} else {
				return ResponseEntity.status(422).body("invalid isbn");
			}

		} catch (Exception e) {
			return ResponseEntity.status(500).body("Sorry !!!, We are not able to proces your request at this time");

		}
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> getBook(@RequestParam SeartchCriteria criteria, @RequestParam String value) {
		try {
			List<BookEntry> entry = bookstoreService.searchBook(criteria, value);

			return ResponseEntity.ok(entry);
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Sorry !!!, We are not able to proces your request at this time");

		}

	}

	@RequestMapping(value = "/{isbn}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable String isbn) {

		try {
			boolean entry = bookstoreService.deleteBookByISBN(isbn);

			if (entry)
				return ResponseEntity.ok("deleted");
			return ResponseEntity.ok("Not found");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Sorry !!!, We are not able to proces your request at this time");

		}
	}

	@RequestMapping(value = "/{isbn}", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@RequestBody BookEntry bookentry, @PathVariable String isbn) {
		try {
			boolean entry = bookstoreService.updateBook(isbn, bookentry);

			if (entry)
				return ResponseEntity.ok("updated");
			return ResponseEntity.ok("Not found");

		} catch (Exception e) {
			return ResponseEntity.status(500).body("Sorry !!!, We are not able to proces your request at this time");

		}

	}

	/*
	 * @RequestMapping(value = "/update", method = RequestMethod.PUT) public String
	 * updateBook(@RequestBody BookEntry bookentry) {
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/delete", method = RequestMethod.DELETE) public
	 * String deleteBook(@RequestParam Long isbn) {
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/search", method = RequestMethod.GET) public
	 * List<BookEntry> deleteBook(@RequestParam Long isbn) {
	 * 
	 * }
	 */

}
