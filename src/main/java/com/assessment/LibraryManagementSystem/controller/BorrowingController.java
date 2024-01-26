package com.assessment.LibraryManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.assessment.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.assessment.LibraryManagementSystem.model.BorrowingRecord;
import com.assessment.LibraryManagementSystem.service.BorrowingRecordService;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class BorrowingController {
	@Autowired
	private BorrowingRecordService borrowingService;

	@PostMapping("/borrow/{bookId}/patron/{patronId}")
	public ResponseEntity<?> borrowBook(@PathVariable int bookId, @PathVariable int patronId) {
		Date borrowedDate = new Date(); // Current date as borrowed date
		BorrowingRecord record = borrowingService.borrowBook(bookId, patronId, borrowedDate);
		return new ResponseEntity<>(record, HttpStatus.OK);
	}

	@PutMapping("/return/{bookId}/patron/{patronId}")
	public ResponseEntity<?> returnBook(@PathVariable int bookId, @PathVariable int patronId) {
		Date returnDate = new Date(); // Current date as return date
		BorrowingRecord record = borrowingService.returnBook(bookId, patronId, returnDate);
		return new ResponseEntity<>(record, HttpStatus.OK);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
