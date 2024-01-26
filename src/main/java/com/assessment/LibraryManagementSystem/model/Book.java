package com.assessment.LibraryManagementSystem.model;

import java.sql.Date;

import lombok.Data;


@Data
public class Book {
	private int id;
	private String title;
	private String author;
	private Date publicationYear;
	private String isbn;
}
