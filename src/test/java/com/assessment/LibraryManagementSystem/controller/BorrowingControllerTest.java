package com.assessment.LibraryManagementSystem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.assessment.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.assessment.LibraryManagementSystem.model.BorrowingRecord;
import com.assessment.LibraryManagementSystem.service.BorrowingRecordService;

public class BorrowingControllerTest {

	@InjectMocks
	BorrowingController borrowingController;

	@Mock
	BorrowingRecordService borrowingRecordService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testBorrowBook() {
		when(borrowingRecordService.borrowBook(anyInt(), anyInt(), any(Date.class))).thenReturn(new BorrowingRecord());

		ResponseEntity<?> response = borrowingController.borrowBook(1, 1);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(borrowingRecordService, times(1)).borrowBook(anyInt(), anyInt(), any(Date.class));
	}

	@Test
	public void testReturnBook() {
		when(borrowingRecordService.returnBook(anyInt(), anyInt(), any(Date.class))).thenReturn(new BorrowingRecord());

		ResponseEntity<?> response = borrowingController.returnBook(1, 1);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(borrowingRecordService, times(1)).returnBook(anyInt(), anyInt(), any(Date.class));
	}

}