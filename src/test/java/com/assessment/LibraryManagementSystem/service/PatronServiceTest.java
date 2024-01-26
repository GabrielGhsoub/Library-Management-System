package com.assessment.LibraryManagementSystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.assessment.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.assessment.LibraryManagementSystem.mapper.PatronMapper;
import com.assessment.LibraryManagementSystem.model.Patron;

public class PatronServiceTest {

	@InjectMocks
	private PatronService patronService;

	@Mock
	private PatronMapper patronMapper;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllPatrons() {
		Patron patron = new Patron();
		when(patronMapper.findAllPatrons()).thenReturn(Arrays.asList(patron));

		List<Patron> result = patronService.getAllPatrons();

		assertEquals(1, result.size());
		verify(patronMapper, times(1)).findAllPatrons();
	}

	@Test
	public void testGetPatronById() {
		Patron patron = new Patron();
		when(patronMapper.findPatronById(1)).thenReturn(patron);

		Patron result = patronService.getPatronById(1);

		assertNotNull(result);
		verify(patronMapper, times(1)).findPatronById(1);
	}

	@Test
	public void testGetPatronByIdNotFound() {
		when(patronMapper.findPatronById(1)).thenReturn(null);

		assertThrows(ResourceNotFoundException.class, () -> patronService.getPatronById(1));
		verify(patronMapper, times(1)).findPatronById(1);
	}

	@Test
	public void testAddPatron() {
		Patron patron = new Patron();
		patron.setName("Test");
		patron.setContactInformation("Test Contact");

		patronService.addPatron(patron);

		verify(patronMapper, times(1)).insertPatron(patron);
	}

	@Test
	public void testUpdatePatron() {
		Patron patron = new Patron();
		patron.setName("Test");
		patron.setContactInformation("Test Contact");
		when(patronMapper.findPatronById(1)).thenReturn(patron);

		patronService.updatePatron(1, patron);

		verify(patronMapper, times(1)).updatePatron(patron);
	}

	@Test
	public void testDeletePatron() {
		Patron patron = new Patron();
		when(patronMapper.findPatronById(1)).thenReturn(patron);

		patronService.deletePatron(1);

		verify(patronMapper, times(1)).deletePatron(1);
	}

	@Test
	public void testValidatePatron() {
		Patron patron = new Patron();
		patron.setName("");
		patron.setContactInformation(null);

		assertThrows(IllegalArgumentException.class, () -> patronService.addPatron(patron));
	}
}
