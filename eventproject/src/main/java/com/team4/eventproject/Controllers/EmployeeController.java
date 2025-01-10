package com.team4.eventproject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.team4.eventproject.ApprovalRequest;
import com.team4.eventproject.Event;
import com.team4.eventproject.Organizer;
import com.team4.eventproject.Services.EmployeeServices;
import com.team4.eventproject.Employee;

import java.util.List;

/**
 * REST Controller για τη διαχείριση λειτουργιών υπαλλήλων.
 */
@RestController
@RequestMapping("employees")
public class EmployeeController {

	@Autowired
	private EmployeeServices employeeServices;

	@GetMapping("/all")
	public List<Employee> getAllEmployees() {
		return employeeServices.getAllEmployees();
	}

	/**
	 * Επιστρέφει όλα τα εκκρεμή αιτήματα.
	 *
	 * @return Λίστα με τα εκκρεμή αιτήματα.
	 */
	@GetMapping("/pending")
	public List<ApprovalRequest> getPendingRequests() {
		return employeeServices.getPendingRequests();
	}

	/**
	 * Επεξεργάζεται ένα αίτημα.
	 *
	 * @param request  Το αίτημα που θα επεξεργαστεί.
	 * @param status   Η κατάσταση του αιτήματος (Approved/Rejected).
	 * @param comments Σχόλια για την απόφαση.
	 * @param employee Ο υπάλληλος που επεξεργάζεται το αίτημα.
	 * @return Μήνυμα επιτυχίας ή αποτυχίας.
	 */
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

	/**
	 * Διαγράφει μια εκδήλωση απευθείας.
	 *
	 * @param eventId     Το ID της εκδήλωσης που θα διαγραφεί.
	 * @param organizerId Το ID του διοργανωτή της εκδήλωσης.
	 * @param employee    Ο υπάλληλος που εκτελεί τη διαγραφή.
	 * @return Μήνυμα επιτυχίας ή αποτυχίας.
	 */
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

	/**
	 * Mock Μέθοδος για εύρεση εκδήλωσης βάσει ID.
	 *
	 * @param eventId Το ID της εκδήλωσης.
	 * @return Το αντικείμενο Event.
	 */
	private Event findEventById(Long eventId) {
		// Mock υλοποίηση
		return new Event("Mock Event", "Music", "Description", "Location", 100, 1, 1, 2025, 10, 0, 120, null,
				"Pending");
	}

	/**
	 * Mock Μέθοδος για εύρεση διοργανωτή βάσει ID.
	 *
	 * @param organizerId Το ID του διοργανωτή.
	 * @return Το αντικείμενο Organizer.
	 */
	private Organizer findOrganizerById(Long organizerId) {
		// Mock υλοποίηση
		return new Organizer("Mock Organizer", "Surname", "123456", "Description");
	}
}