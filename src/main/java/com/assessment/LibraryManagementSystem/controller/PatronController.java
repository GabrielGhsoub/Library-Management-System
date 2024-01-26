package com.assessment.LibraryManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.assessment.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.assessment.LibraryManagementSystem.model.Patron;
import com.assessment.LibraryManagementSystem.service.PatronService;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {

	@Autowired
	private PatronService patronService;

	// GET /api/patrons
	@GetMapping
	public ResponseEntity<List<Patron>> getAllPatrons() {
		List<Patron> patrons = patronService.getAllPatrons();
		return ResponseEntity.ok(patrons);
	}

	// GET /api/patrons/{id}
	@GetMapping("/{id}")
	public ResponseEntity<Patron> getPatronById(@PathVariable int id) {
		Patron patron = patronService.getPatronById(id);
		if (patron != null) {
			return ResponseEntity.ok(patron);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// POST /api/patrons
	@PostMapping
	public ResponseEntity<Patron> addPatron(@RequestBody Patron patron) {
		Patron newPatron = patronService.addPatron(patron);
		return ResponseEntity.ok(newPatron);
	}

	// PUT /api/patrons/{id}
	@PutMapping("/{id}")
	public ResponseEntity<Patron> updatePatron(@PathVariable int id, @RequestBody Patron patron) {
		Patron updatedPatron = patronService.updatePatron(id, patron);
		if (updatedPatron != null) {
			return ResponseEntity.ok(updatedPatron);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// DELETE /api/patrons/{id}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePatron(@PathVariable int id) {
		try {
			patronService.deletePatron(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
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
