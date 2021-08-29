package com.sample.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sample.bookstore.model.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {

	List<Book> findByTitle(String name);
	
	List<Book> findByIsbn(String isbn);

	Optional<Book> findById(Integer id);
}
