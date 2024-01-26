package com.assessment.LibraryManagementSystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.assessment.LibraryManagementSystem.mapper.BookMapper;
import com.assessment.LibraryManagementSystem.model.Book;

public class BookServiceTest {

	@InjectMocks
	private BookService bookService;

	@Mock
	private BookMapper bookMapper;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllBooks() {
		Book book1 = new Book();
		Book book2 = new Book();
		when(bookMapper.findAllBooks()).thenReturn(Arrays.asList(book1, book2));

		List<Book> result = bookService.getAllBooks();

		assertEquals(2, result.size());
		verify(bookMapper, times(1)).findAllBooks();
	}

	@Test
	public void testGetBookById() {
		Book book = new Book();
		book.setId(1);
		when(bookMapper.findBookById(1)).thenReturn(book);

		Book result = bookService.getBookById(1);

		assertNotNull(result);
		assertEquals(1, result.getId());
		verify(bookMapper, times(1)).findBookById(1);
	}

	@Test
	public void testAddBook() {
		Book book = new Book();
		book.setId(1);
		book.setTitle("Test Title");
		book.setAuthor("Test Author");
		book.setPublicationYear(new Date(System.currentTimeMillis()));
		book.setIsbn("9781234567890");

		doNothing().when(bookMapper).insertBook(any(Book.class));

		Book result = bookService.addBook(book);

		assertNotNull(result);
		assertEquals(1, result.getId());
		verify(bookMapper, times(1)).insertBook(any(Book.class));
	}

	@Test
	public void testUpdateBook() {
		Book book = new Book();
		book.setId(1);
		book.setTitle("Test Title");
		book.setAuthor("Test Author");
		book.setPublicationYear(new Date(System.currentTimeMillis()));
		book.setIsbn("9781234567890");

		doNothing().when(bookMapper).updateBook(any(Book.class));
		when(bookMapper.findBookById(1)).thenReturn(book);

		Book result = bookService.updateBook(1, book);

		assertNotNull(result);
		assertEquals(1, result.getId());
		verify(bookMapper, times(1)).updateBook(any(Book.class));
	}

	@Test
	public void testDeleteBook() {
		Book book = new Book();
		book.setId(1);
		when(bookMapper.findBookById(1)).thenReturn(book);

		doNothing().when(bookMapper).deleteBook(1);

		bookService.deleteBook(1);

		verify(bookMapper, times(1)).deleteBook(1);
	}
}