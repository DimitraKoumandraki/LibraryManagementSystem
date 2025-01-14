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

	public ResponseEntity<String> handleApprovalRequest(@RequestBody ApprovalRequest request,
			@RequestParam String status, @RequestParam String comments, @RequestBody Employee employee) {
		try {
			// Κλήση της υπηρεσίας για επεξεργασία του αιτήματος
			employeeServices.handleApprovalRequest(request, status, comments, employee);

			// Επιστροφή επιτυχίας αν ολοκληρωθεί χωρίς σφάλματα
			return ResponseEntity.ok("Το αίτημα επεξεργάστηκε επιτυχώς.");
		} catch (Exception e) {
			// Επιστροφή σφάλματος αν προκύψει κάποιο πρόβλημα
			return ResponseEntity.badRequest().body("Λάθος: " + e.getMessage());
		}
	}

	// Διαγράφει μια εκδήλωση απευθείας.
	@DeleteMapping("/delete-event")
	public ResponseEntity<String> deleteEventDirectly(
	       @RequestBody Event event,//Η εκδήλωση που θέλουμε να διαγράψουμε.
	        @RequestParam Long organizerId,//Το ID του διοργανωτή της εκδήλωσης.
	        @RequestBody Employee employee)// Ο υπάλληλος που εκτελεί τη διαγραφή.
	{

	    // Εύρεση του διοργανωτή βάσει ID
	    Organizer organizer = findOrganizerById(organizerId);

	    // Έλεγχος αν ο διοργανωτής υπάρχει
	    if (organizer == null) {
	        return ResponseEntity.badRequest().body("Ο διοργανωτής με ID " + organizerId + " δεν βρέθηκε.");
	    }

	    // Εκτέλεση της διαγραφής
	    boolean result = employeeServices.deleteEventDirectly(event, organizer, employee);

	    // Επιστροφή κατάλληλου μηνύματος βάσει αποτελέσματος
	    if (result) {
	        return ResponseEntity.ok("Η εκδήλωση '" + event.getTitle() + "' διαγράφηκε επιτυχώς.");
	    } else {
	        return ResponseEntity.badRequest().body("Η εκδήλωση '" + event.getTitle() + "' δεν βρέθηκε στη λίστα του διοργανωτή.");
	    }
	}

	private Organizer findOrganizerById(Long organizerId) {
		return null;
	}

	// Μέθοδος για εύρεση εκδήλωσης βάσει ID.
	//Την δηλώνω τοπικά γιατί εξυπηρετεί την παραπάνω μέθοδο 
	private Event findEventById(Long eventId) {
		// Δοκιμή
		return new Event(1L, " Event1", "Music", "Description", "Location", 100, 1, 1, 2025, 10, 0, 120, null,
				"Pending");
	}

}