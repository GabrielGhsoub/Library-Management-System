package com.assessment.LibraryManagementSystem.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.assessment.LibraryManagementSystem.mapper.BookMapper;
import com.assessment.LibraryManagementSystem.model.Book;

@Service
public class BookService {

	@Autowired
	private BookMapper bookMapper;

	public List<Book> getAllBooks() {
		return bookMapper.findAllBooks();
	}

	@Cacheable(value = "booksCache", key = "#id")
	public Book getBookById(int id) {
		return Optional.ofNullable(bookMapper.findBookById(id))
				.orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));
	}

	@CachePut(value = "booksCache", key = "#result.id")
	@Transactional
	public Book addBook(Book book) {
		validateBook(book);
		checkIfIsbnExists(book.getIsbn(), null); // Check if ISBN exists for new book
		bookMapper.insertBook(book);
		return book;
	}

	@CachePut(value = "booksCache", key = "#id")
	@Transactional
	public Book updateBook(int id, Book book) {
		validateBook(book);
		checkIfIsbnExists(book.getIsbn(), id); // Check if ISBN exists for updated book
		getBookById(id); // Ensuring the book exists
		book.setId(id);
		bookMapper.updateBook(book);
		return book;
	}

	@CacheEvict(value = "booksCache", key = "#id")
	@Transactional
	public void deleteBook(int id) {
		getBookById(id); // Ensuring the book exists
		bookMapper.deleteBook(id);
	}

	private void validateBook(Book book) {
		if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
			throw new IllegalArgumentException("Book title is required.");
		}
		if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
			throw new IllegalArgumentException("Book author is required.");
		}
		validatePublicationYear(book.getPublicationYear());
		if (!isValidISBN(book.getIsbn())) {
			throw new IllegalArgumentException("Invalid ISBN.");
		}
	}

	private void validatePublicationYear(Date publicationYear) {
		if (publicationYear == null) {
			throw new IllegalArgumentException("Publication year cannot be null.");
		}

		// Convert java.sql.Date to LocalDate
		LocalDate localDate = publicationYear.toLocalDate();
		Year year = Year.of(localDate.getYear());

		Year earliestYear = Year.of(1450);
		Year currentYear = Year.now();

		// Check if the year is before the earliest year or after the current year
		if (year.isBefore(earliestYear) || year.isAfter(currentYear)) {
			throw new IllegalArgumentException("Invalid publication year.");
		}
	}

	private boolean isValidISBN(String isbn) {
		if (isbn == null) {
			return false;
		}
		isbn = isbn.replaceAll("-", ""); // Remove hyphens if present
		if (isbn.length() == 10) {
			return isValidISBN10(isbn);
		} else if (isbn.length() == 13) {
			return isValidISBN13(isbn);
		}
		return false;
	}

	private boolean isValidISBN10(String isbn) {
		// ISBN-10: 9 or 10 digits, and can end with X or x
		String regex = "^(\\d{9}[\\d|X|x])$";
		return isbn.matches(regex);
	}

	private boolean isValidISBN13(String isbn) {
		// ISBN-13: 13 digits, starts with 978 or 979
		String regex = "^(978|979)\\d{10}$";
		return isbn.matches(regex);
	}

	private void checkIfIsbnExists(String isbn, Integer id) {
		Book existingBook = bookMapper.findBookByIsbn(isbn);
		if (existingBook != null && (id == null || existingBook.getId() != id)) {
			throw new IllegalArgumentException("ISBN already exists.");
		}
	}

}
