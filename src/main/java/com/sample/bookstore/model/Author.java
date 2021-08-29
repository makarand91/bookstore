package com.sample.bookstore.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Author {
	
	public Author(String name)
	{
		this.name=name;
	}

	public Author()
	{
		
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Integer id;

	private String name;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "author", fetch = FetchType.LAZY)
	private Set<Book> book = new HashSet<Book>();

}
