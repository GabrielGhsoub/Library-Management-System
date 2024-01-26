package com.assessment.LibraryManagementSystem.controller;

import com.assessment.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.assessment.LibraryManagementSystem.model.Patron;
import com.assessment.LibraryManagementSystem.service.PatronService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PatronControllerTest {

	@InjectMocks
	PatronController patronController;

	@Mock
	PatronService patronService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllPatronsTest() {
		Patron patron = new Patron();
		when(patronService.getAllPatrons()).thenReturn(Arrays.asList(patron));

		ResponseEntity<List<Patron>> response = patronController.getAllPatrons();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
	}

	@Test
	public void getPatronByIdTest() {
		Patron patron = new Patron();
		when(patronService.getPatronById(1)).thenReturn(patron);

		ResponseEntity<Patron> response = patronController.getPatronById(1);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(patron, response.getBody());
	}

	@Test
	public void getPatronByIdNotFoundTest() {
		when(patronService.getPatronById(1)).thenReturn(null);

		ResponseEntity<Patron> response = patronController.getPatronById(1);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	public void addPatronTest() {
		Patron patron = new Patron();
		when(patronService.addPatron(patron)).thenReturn(patron);

		ResponseEntity<Patron> response = patronController.addPatron(patron);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(patron, response.getBody());
	}

	@Test
	public void updatePatronTest() {
		Patron patron = new Patron();
		when(patronService.updatePatron(1, patron)).thenReturn(patron);

		ResponseEntity<Patron> response = patronController.updatePatron(1, patron);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(patron, response.getBody());
	}

	@Test
	public void updatePatronNotFoundTest() {
		Patron patron = new Patron();
		when(patronService.updatePatron(1, patron)).thenReturn(null);

		ResponseEntity<Patron> response = patronController.updatePatron(1, patron);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	public void deletePatronTest() {
		doNothing().when(patronService).deletePatron(1);

		ResponseEntity<Void> response = patronController.deletePatron(1);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void deletePatronNotFoundTest() {
		doThrow(ResourceNotFoundException.class).when(patronService).deletePatron(1);

		ResponseEntity<Void> response = patronController.deletePatron(1);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
}