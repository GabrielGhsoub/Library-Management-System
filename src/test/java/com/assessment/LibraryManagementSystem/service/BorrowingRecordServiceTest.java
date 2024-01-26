package com.assessment.LibraryManagementSystem.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import com.assessment.LibraryManagementSystem.mapper.BookMapper;
import com.assessment.LibraryManagementSystem.mapper.BorrowingRecordMapper;
import com.assessment.LibraryManagementSystem.mapper.PatronMapper;
import com.assessment.LibraryManagementSystem.model.Book;
import com.assessment.LibraryManagementSystem.model.BorrowingRecord;
import com.assessment.LibraryManagementSystem.model.Patron;

public class BorrowingRecordServiceTest {

	@InjectMocks
	private BorrowingRecordService borrowingRecordService;

	@Mock
	private BorrowingRecordMapper borrowingRecordMapper;

	@Mock
	private BookMapper bookMapper;

	@Mock
	private PatronMapper patronMapper;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllBorrowingRecords() {
		borrowingRecordService.getAllBorrowingRecords();
		verify(borrowingRecordMapper, times(1)).findAllBorrowingRecords();
	}

	@Test
	public void testGetBorrowingRecordById() {
		when(borrowingRecordMapper.findBorrowingRecordById(anyInt())).thenReturn(Optional.of(new BorrowingRecord()));
		assertNotNull(borrowingRecordService.getBorrowingRecordById(1));
	}

	@Test
	public void testGetBorrowingRecordByIdNotFound() {
		when(borrowingRecordMapper.findBorrowingRecordById(anyInt())).thenReturn(Optional.empty());
		assertThrows(ResponseStatusException.class, () -> borrowingRecordService.getBorrowingRecordById(1));
	}

	@Test
	public void testBorrowBook() {
		when(bookMapper.findBookById(anyInt())).thenReturn(new Book());
		when(patronMapper.findPatronById(anyInt())).thenReturn(new Patron());
		assertNotNull(borrowingRecordService.borrowBook(1, 1, new Date()));
	}

	@Test
	public void testBorrowBookBookNotFound() {
		when(bookMapper.findBookById(anyInt())).thenReturn(null);
		assertThrows(ResponseStatusException.class, () -> borrowingRecordService.borrowBook(1, 1, new Date()));
	}

	@Test
	public void testBorrowBookPatronNotFound() {
		when(bookMapper.findBookById(anyInt())).thenReturn(new Book());
		when(patronMapper.findPatronById(anyInt())).thenReturn(null);
		assertThrows(ResponseStatusException.class, () -> borrowingRecordService.borrowBook(1, 1, new Date()));
	}

	@Test
	public void testReturnBook() {
		when(borrowingRecordMapper.findActiveBorrowingRecord(anyInt(), anyInt())).thenReturn(new BorrowingRecord());
		assertNotNull(borrowingRecordService.returnBook(1, 1, new Date()));
	}

	@Test
	public void testReturnBookNotFound() {
		when(borrowingRecordMapper.findActiveBorrowingRecord(anyInt(), anyInt())).thenReturn(null);
		assertThrows(ResponseStatusException.class, () -> borrowingRecordService.returnBook(1, 1, new Date()));
	}
}
