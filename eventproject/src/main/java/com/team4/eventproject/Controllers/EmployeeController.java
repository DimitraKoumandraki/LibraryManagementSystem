package com.team4.eventproject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.team4.eventproject.ApprovalRequest;
import com.team4.eventproject.Event;
import com.team4.eventproject.Organizer;
import com.team4.eventproject.Services.EmployeeServices;
import com.team4.eventproject.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller για τη διαχείριση λειτουργιών υπαλλήλων.
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeServices employeeServices;

	@GetMapping("/all")
	public List<Employee> getAllEmployees() {
		return employeeServices.getAllEmployees();
	}
	
	// Αναζήτηση υπαλλήλου μέσω ID.
	@GetMapping("/id")
	public ResponseEntity<?> findEmployeeById(@PathVariable Long id) {
		Employee employee = employeeServices.findEmployeeById(id);
		if (employee != null) {
			return ResponseEntity.ok(employee);
		} else {
			return ResponseEntity.badRequest().body("Employee with ID " + id + " not found.");
		}
	}

	// Προσθήκη υπαλλήλου.
	@PostMapping
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
		employeeServices.addEmployee(employee);
		return ResponseEntity.ok("Employee added successfully.");
	}

	// Επιστρέφει όλα τα εκκρεμή αιτήματα.

	@GetMapping("/pending")
	public List<ApprovalRequest> getPendingRequests() {
		return employeeServices.getPendingRequests();
	}

	// Επεξεργάζεται ένα αίτημα.
	@PostMapping("/process")
	public ResponseEntity<String> processRequest(@RequestBody ApprovalRequest request, @RequestParam String status,
			@RequestParam String comments, @RequestBody Employee employee) {
		try {
			employeeServices.processRequest(request, status, comments, employee);
			return ResponseEntity.ok("Το αίτημα επεξεργάστηκε επιτυχώς.");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body("Σφάλμα: " + e.getMessage());
		}
	}

	// Διαγράφει μια εκδήλωση απευθείας.
	@DeleteMapping("/delete-event")
	public ResponseEntity<String> deleteEventDirectly(@RequestParam Long eventId, @RequestParam Long organizerId,
			@RequestBody Employee employee) {
		// Εύρεση Event και Organizer
		Event event = findEventById(eventId);
		Organizer organizer = findOrganizerById(organizerId);

		if (event != null && organizer != null) {
			boolean result = employeeServices.deleteEventDirectly(event, organizer, employee);
			return result ? ResponseEntity.ok("Η εκδήλωση διαγράφηκε επιτυχώς.")
					: ResponseEntity.badRequest().body("Η διαγραφή της εκδήλωσης απέτυχε.");
		}
		return ResponseEntity.badRequest().body("Η εκδήλωση ή ο διοργανωτής δεν βρέθηκαν.");
	}

	private Organizer findOrganizerById(Long organizerId) {
		return null;
	}
	
	// Μέθοδος για εύρεση εκδήλωσης βάσει ID.
	private Event findEventById(Long eventId) {
		// Δοκιμή
		return new Event(1L," Event1", "Music", "Description", "Location", 100, 1, 1, 2025, 10, 0, 120, null, "Pending");
	}

	
}