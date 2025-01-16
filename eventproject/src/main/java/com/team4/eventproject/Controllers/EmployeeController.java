package com.team4.eventproject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.team4.eventproject.ApprovalRequest;
import com.team4.eventproject.Event;
import com.team4.eventproject.Organizer;
import com.team4.eventproject.Services.EmployeeServices;
import com.team4.eventproject.Services.EventServices;
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
	@GetMapping("/id/{id}")
	public String findEmployeeById(@PathVariable Long id) {
	    Employee employee = EmployeeServices.findEmployeeById(id); 
	    if (employee != null) {
	        return employee.toString();
	    } else {
	        return "Ο υπάλληλος με ID " + id + " δεν βρέθηκε.";
	    }
	}

	

	// Προσθήκη υπαλλήλου.
	@PostMapping("/add")
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
		employeeServices.addEmployee(employee);
		return ResponseEntity.ok("Ο υπάλληλος προστέθηκε με επιτυχία.");
	}

	// Επιστρέφει όλα τα εκκρεμή αιτήματα.

	@GetMapping("/pending")
	public List<ApprovalRequest> getPendingRequests() {
		return employeeServices.getPendingRequests();
	}

	// Επεξεργάζεται ένα αίτημα.
	@PostMapping("/process")
	public ResponseEntity<String> handleApprovalRequest(
	        @RequestParam Long approvalRequestId,  
	        @RequestParam String status,           
	        @RequestParam String comments,         
	        @RequestParam Long employeeId) {       
	    try {
	        // Κλήση της υπηρεσίας για επεξεργασία του αιτήματος
	        employeeServices.processRequest(approvalRequestId, status, comments, employeeId);

	        // Αν όλα πάνε καλά, επιστρέφουμε επιτυχία
	        return ResponseEntity.ok("Το αίτημα επεξεργάστηκε επιτυχώς.");
	    } catch (Exception e) {
	        // Αν υπάρχει κάποιο σφάλμα, επιστρέφουμε λάθος μήνυμα
	        return ResponseEntity.badRequest().body("Λάθος: " + e.getMessage());
	    }
	}


	// Διαγράφει μια εκδήλωση απευθείας.
	@DeleteMapping("/delete-event")
	public ResponseEntity<String> deleteEventDirectly(
	       @RequestBody Event event,//Η εκδήλωση που θέλουμε να διαγράψουμε.
	        @RequestParam Long organizerId,
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

	
}