package com.assessment.LibraryManagementSystem.model;

import lombok.Data;
import java.util.Date;

@Data
public class BorrowingRecord {
	private int id;
	private int bookId;
	private int patronId;
	private Date borrowedDate;
	private Date returnDate; // Can be null if the book hasn't been returned yet
}