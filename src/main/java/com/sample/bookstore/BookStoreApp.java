package com.sample.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.sample.bookstore"})
public class BookStoreApp {

	public static void main(String[] args) {

		SpringApplication.run(BookStoreApp.class, args);
	}

}
