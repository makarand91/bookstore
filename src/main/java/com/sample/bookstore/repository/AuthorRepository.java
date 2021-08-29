package com.sample.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sample.bookstore.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Integer> {

	Optional<Author> findById(Integer id);
	
	List<Author> findByName(String name);
	
	
}
