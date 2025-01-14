package com.team4.eventproject.Services;

import java.util.ArrayList;
import java.util.List;

import com.team4.eventproject.ApprovalRequest;
import com.team4.eventproject.Event;
import com.team4.eventproject.Organizer;
import com.team4.eventproject.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServices {

	// Δήλωση της λίστας υπαλλήλων
	private List<Employee> employees;

	// Constructor για την αρχικοποίηση
	public EmployeeServices() {
		employees = new ArrayList<>(); // Αρχικοποίηση της λίστας

		// Προσθήκη mock δεδομένων
		employees.add(new Employee("Γιώργος", "Παπαδόπουλος", "george.papadopoulos@example.com", 1L));
		employees.add(new Employee("Μαρία", "Κωνσταντίνου", "maria.konstantinou@example.com", 2L));
		employees.add(new Employee("Νίκος", "Αντωνίου", "nikos.antoniou@example.com", 3L));
	}

	// Μέθοδος για την επιστροφή όλων των υπαλλήλων
	public List<Employee> getAllEmployees() {
		return employees;
	}

	// Αναζήτηση υπαλλήλου μέσω ID
	public Employee findEmployeeById(Long id) {
		for (Employee employee : employees) {
			if (employee.getId().equals(id)) {
				return employee; // Επιστροφή του υπαλλήλου αν βρεθεί
			}
		}
		System.out.println("Ο υπάλληλος με ID " + id + " δεν βρέθηκε.");
		return null; // Επιστροφή null αν δεν βρεθεί υπάλληλος
	}

	// Μέθοδος για την προσθήκη νέου υπαλλήλου
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}

	// Λίστα εκκρεμών αιτημάτων
	private List<ApprovalRequest> pendingRequests = new ArrayList<>();

	// Προσθήκη αιτήματος στη λίστα
	public void addRequest(ApprovalRequest request) {
		pendingRequests.add(request);
	}

	// Επιστρέφει τη λίστα εκκρεμών αιτημάτων
	public List<ApprovalRequest> getPendingRequests() {
		return pendingRequests;
	}

	// Προβολή εκκρεμών αιτημάτων
	public void viewPendingRequests() {
		System.out.println("Εκκρεμή αιτήματα:");
		for (ApprovalRequest request : pendingRequests) {
			System.out.println("- " + request.getType() + ": " + request.getEvent().getTitle() + " (Υποβλήθηκε από: "
					+ request.getSubmittedBy().getName() + ")");
		}
	}

	// Επεξεργάζεται ένα αίτημα και ενημερώνει την κατάστασή του.
	// Αφαιρεί το αίτημα από τη λίστα εκκρεμών αιτημάτων μόλις επεξεργαστεί.
	public void processRequest(ApprovalRequest request, String status, String comments, Employee employee) {
		handleApprovalRequest(request, status, comments, employee);
		pendingRequests.remove(request); // Αφαίρεση από τη λίστα εκκρεμών αιτημάτων
	}

	// Επεξεργάζεται ένα αίτημα δημιουργίας ή διαγραφής εκδηλώσεων.

	public void handleApprovalRequest(ApprovalRequest request, String status, String comments, Employee employee) {
		// Ενημέρωση της κατάστασης του αιτήματος
		request.closeRequest(status, employee, comments);

		// Αν το αίτημα εγκρίθηκε, επεξεργαζόμαστε την εκδήλωση
		if ("Approved".equalsIgnoreCase(status)) {
			if ("Add".equalsIgnoreCase(request.getType())) {
				request.getSubmittedBy().addEvent(request.getEvent());
			} else if ("Delete".equalsIgnoreCase(request.getType())) {
				request.getSubmittedBy().removeEvent(request.getEvent());
			}
		}
	}

	// Διαγράφει μία εκδήλωση απευθείας από τη λίστα ενός διοργανωτή.
	public boolean deleteEventDirectly(Event event, Organizer organizer, Employee employee) {
		if (!organizer.getEvents().remove(event)) {
			System.out.println("Η εκδήλωση " + event.getTitle() + " δεν υπάρχει.");
			return false;
		}
		System.out.println("Ο υπάλληλος " + employee.getName() + " διέγραψε την εκδήλωση " + event.getTitle() + ".");
		return true;
	}
}