package com.assessment.LibraryManagementSystem.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.assessment.LibraryManagementSystem.model.Book;
import com.assessment.LibraryManagementSystem.service.BookService;

public class BookControllerTest {

	@InjectMocks
	BookController bookController;

	@Mock
	BookService bookService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllBooksTest() {
		Book book = new Book();
		when(bookService.getAllBooks()).thenReturn(Arrays.asList(book));

		ResponseEntity<List<Book>> response = bookController.getAllBooks();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
		verify(bookService, times(1)).getAllBooks();
	}

	@Test
	public void getBookByIdTest() {
		Book book = new Book();
		when(bookService.getBookById(1)).thenReturn(book);

		ResponseEntity<Book> response = bookController.getBookById(1);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(bookService, times(1)).getBookById(1);
	}

	@Test
	public void getBookByIdNotFoundTest() {
		when(bookService.getBookById(1)).thenReturn(null);

		ResponseEntity<Book> response = bookController.getBookById(1);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(bookService, times(1)).getBookById(1);
	}

	@Test
	public void addBookTest() {
		Book book = new Book();
		when(bookService.addBook(any(Book.class))).thenReturn(book);

		ResponseEntity<Book> response = bookController.addBook(book);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(bookService, times(1)).addBook(any(Book.class));
	}

	@Test
	public void updateBookTest() {
		Book book = new Book();
		when(bookService.updateBook(anyInt(), any(Book.class))).thenReturn(book);

		ResponseEntity<Book> response = bookController.updateBook(1, book);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(bookService, times(1)).updateBook(anyInt(), any(Book.class));
	}

	@Test
	public void updateBookNotFoundTest() {
		Book book = new Book();
		when(bookService.updateBook(anyInt(), any(Book.class))).thenReturn(null);

		ResponseEntity<Book> response = bookController.updateBook(1, book);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(bookService, times(1)).updateBook(anyInt(), any(Book.class));
	}

	@Test
	public void deleteBookTest() {
		doNothing().when(bookService).deleteBook(anyInt());

		ResponseEntity<?> response = bookController.deleteBook(1);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(bookService, times(1)).deleteBook(anyInt());
	}

	@Test
	public void deleteBookNotFoundTest() {
		doThrow(new RuntimeException()).when(bookService).deleteBook(anyInt());

		ResponseEntity<?> response = bookController.deleteBook(1);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(bookService, times(1)).deleteBook(anyInt());
	}
}