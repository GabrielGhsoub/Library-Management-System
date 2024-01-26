package com.assessment.LibraryManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.assessment.LibraryManagementSystem.mapper.PatronMapper;
import com.assessment.LibraryManagementSystem.model.Patron;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {
	@Autowired
	private PatronMapper patronMapper;

	public List<Patron> getAllPatrons() {
		return patronMapper.findAllPatrons();
	}

	public Patron getPatronById(int id) {
		return Optional.ofNullable(patronMapper.findPatronById(id))
				.orElseThrow(() -> new ResourceNotFoundException("Patron not found with ID: " + id));
	}

	@Transactional
	public Patron addPatron(Patron patron) {
		validatePatron(patron);
		patronMapper.insertPatron(patron);
		return patron;
	}

	@Transactional
	public Patron updatePatron(int id, Patron patron) {
		validatePatron(patron);
		getPatronById(id); // Ensure the patron exists, throws exception if not found.
		patron.setId(id);
		patronMapper.updatePatron(patron);
		return patron;
	}

	@Transactional
	public void deletePatron(int id) {
		getPatronById(id); // Ensure the patron exists, throws exception if not found.
		patronMapper.deletePatron(id);
	}

	private void validatePatron(Patron patron) {
		if (patron.getName() == null || patron.getName().trim().isEmpty()) {
			throw new IllegalArgumentException("Patron name cannot be empty");
		}

		if (patron.getContactInformation() == null) {
			throw new IllegalArgumentException("Invalid contact information");
		}

	}
}
