package com.assessment.LibraryManagementSystem.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.assessment.LibraryManagementSystem.mapper.BookMapper;
import com.assessment.LibraryManagementSystem.mapper.BorrowingRecordMapper;
import com.assessment.LibraryManagementSystem.mapper.PatronMapper;
import com.assessment.LibraryManagementSystem.model.BorrowingRecord;

@Service
public class BorrowingRecordService {

	@Autowired
	private BorrowingRecordMapper borrowingRecordMapper;

	@Autowired
	private BookMapper bookMapper;

	@Autowired
	private PatronMapper patronMapper;

	public List<BorrowingRecord> getAllBorrowingRecords() {
		return borrowingRecordMapper.findAllBorrowingRecords();
	}

	public BorrowingRecord getBorrowingRecordById(int id) {
		return borrowingRecordMapper.findBorrowingRecordById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Borrowing record not found with id: " + id));
	}

	@Transactional
	public BorrowingRecord borrowBook(int bookId, int patronId, Date borrowedDate) {
		// Input validation
		validateBookAndPatron(bookId, patronId);

		// Create a new borrowing record
		BorrowingRecord record = new BorrowingRecord();
		record.setBookId(bookId);
		record.setPatronId(patronId);
		record.setBorrowedDate(borrowedDate);

		borrowingRecordMapper.insertBorrowingRecord(record);
		return record;
	}

	@Transactional
	public BorrowingRecord returnBook(int bookId, int patronId, Date returnDate) {
		BorrowingRecord record = Optional.ofNullable(borrowingRecordMapper.findActiveBorrowingRecord(bookId, patronId))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"No active borrowing record found for bookId: " + bookId + " and patronId: " + patronId));

		record.setReturnDate(returnDate);
		borrowingRecordMapper.updateBorrowingRecord(record);
		return record;
	}

	private void validateBookAndPatron(int bookId, int patronId) {
		if (bookMapper.findBookById(bookId) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with id: " + bookId);
		}
		if (patronMapper.findPatronById(patronId) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patron not found with id: " + patronId);
		}
	}
}
