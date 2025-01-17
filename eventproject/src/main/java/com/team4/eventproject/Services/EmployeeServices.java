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
	static List<Employee> employees = new ArrayList<>();

	public EmployeeServices() {
		// Προσθήκη 3 εργαζομένων για τον έλεγχο της λειτουργίας του κώδικα
		Employee employee1 = new Employee("Γιώργος", "Παπαδόπουλος", "george.papadopoulos@example.com", 1L);
		Employee employee2 = new Employee("Μαρία", "Κωνσταντίνου", "maria.konstantinou@example.com", 2L);
		Employee employee3 = new Employee("Νίκος", "Αντωνίου", "nikos.antoniou@example.com", 3L);

		employees.add(employee1);
		employees.add(employee2);
		employees.add(employee3);
	}

	// Μέθοδος για την επιστροφή όλων των υπαλλήλων
	public List<Employee> getAllEmployees() {
		return employees;
	}

	// Αναζήτηση υπαλλήλου μέσω ID
	public static Employee findEmployeeById(Long employeeid) {
		if (employees == null || employees.isEmpty()) {
			System.out.println("Employee list is empty or not initialized!");
			return null;
		}

		for (Employee employee : employees) {
			if (employee.getId().equals(employeeid)) {
				return employee; // Επιστροφή του υπαλλήλου αν βρεθεί
			}
		}
		return null;
	}

	// Μέθοδος για την προσθήκη νέου υπαλλήλου
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}

	private List<ApprovalRequest> pendingRequests = new ArrayList<>();

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
	// Επεξεργάζεται ένα αίτημα δημιουργίας ή διαγραφής εκδηλώσεων
	public void handleApprovalRequest(Long approvalRequestId, String status, String comments, Long employeeId) {
		// Εύρεση του ApprovalRequest και του Employee από τα IDs
		ApprovalRequest request = ApprovalRequestServices.findById(approvalRequestId);
		Employee employee = findEmployeeById(employeeId);

		// Έλεγχος αν το αίτημα ή ο υπάλληλος δεν βρέθηκαν
		if (request == null) {
			throw new IllegalArgumentException("Η αίτηση με ID " + approvalRequestId + " δεν βρέθηκε.");
		}
		if (employee == null) {
			throw new IllegalArgumentException("Ο υπάλληλος με ID " + employeeId + " δεν βρέθηκε.");
		}

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

	// Επεξεργάζεται και αφαιρεί το αίτημα από τη λίστα εκκρεμών αιτημάτων
	public void processRequest(Long approvalRequestId, String status, String comments, Long employeeId) {
		// Εύρεση του ApprovalRequest και του Employee από τα IDs
		ApprovalRequest request = ApprovalRequestServices.findById(approvalRequestId);
		Employee employee = findEmployeeById(employeeId);

		// Αν το αίτημα ή ο υπάλληλος δεν βρεθούν, πετάμε εξαίρεση
		if (request == null) {
			throw new IllegalArgumentException("Η αίτηση με ID " + approvalRequestId + " δεν βρέθηκε.");
		}
		if (employee == null) {
			throw new IllegalArgumentException("Ο υπάλληλος με ID " + employeeId + " δεν βρέθηκε.");
		}

		// Επεξεργασία του αιτήματος
		handleApprovalRequest(approvalRequestId, status, comments, employeeId);

		// Αφαίρεση του αιτήματος από τη λίστα εκκρεμών αιτημάτων μετά την επεξεργασία
		pendingRequests.remove(request);
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