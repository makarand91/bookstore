package com.sample.bookstore.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"isbn"})})
public class Book {

	public Book(String title, Date publicationdate, String isbn) {
		this.title = title;
		this.publicationdate = publicationdate;
		this.isbn = isbn;
	}
	
	public Book()
	{}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String title;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "book_author", joinColumns = {
			@JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false, updatable = false) })
	private Set<Author> author = new HashSet<Author>();

	private Date publicationdate;

	@Column(unique=true,length = 13)
	private String isbn;

}
