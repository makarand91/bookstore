package com.sample.bookstore.model;

import java.util.Date;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookEntry {
	
	@NotBlank(message ="title cannot be blank")
	private String title;
	

	@NotBlank(message ="author cannot be blank")
	private String author;
	

    @Size(min=13,max=13,message="isbn must 13 digit long")
	private String isbn;
	

	@NotBlank(message ="publication date cannot be blank")
	private Date publicationdate; 

}
