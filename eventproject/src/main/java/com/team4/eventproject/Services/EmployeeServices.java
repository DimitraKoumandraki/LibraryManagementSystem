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
	private List<Event> allEvents;
	private List<Organizer> organizers;

	// Constructor για την αρχικοποίηση
	public EmployeeServices() {

		allEvents = new ArrayList<>();
		organizers = new ArrayList<>();
		employees = new ArrayList<>(); // Αρχικοποίηση της λίστας

		Organizer org1 = new Organizer();
		Organizer org2 = new Organizer();

		Event event1 = new Event(1L,"Fashion Show - Fall Collection", "Fashion",
				"A stunning presentation of the latest fall trends.", "Thessaloniki Art Center", 300, 10, 6, 2025, 19,
				30, 180, org2, "Pending");

		Event event2 = new Event(2L,"Yoga Retreat - Wellness and Relaxation", "Health",
				"A weekend of yoga, meditation, and relaxation.", "Thessaloniki Concert Hall", 150, 12, 6, 2025, 18, 0,
				120, org2, "Approved");

		org1.addEvent(event1);
		org2.addEvent(event2);

		allEvents.add(event1);
		allEvents.add(event2);
		organizers.add(org1);
		organizers.add(org2);

		// Προσθήκη mock δεδομένων
		employees.add(new Employee("Γιώργος", "Παπαδόπουλος", "george.papadopoulos@example.com", 1L));
		employees.add(new Employee("Μαρία", "Κωνσταντίνου", "maria.konstantinou@example.com", 2L));
		employees.add(new Employee("Νίκος", "Αντωνίου", "nikos.antoniou@example.com", 3L));
	}

	// Μέθοδος για την επιστροφή όλων των υπαλλήλων
	public List<Employee> getAllEmployees() {
		return employees;
	}

	public List<Event> getAllEvents() {
		return allEvents;
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
		if (!status.equals("Approved") && !status.equals("Rejected")) {
			throw new IllegalArgumentException("Invalid status. Use 'Approved' or 'Rejected'.");
		}
		// Ενημέρωση της κατάστασης του αιτήματος
		request.closeRequest(status, employee, comments);

		// Αν η κατάσταση είναι "Approved", επεξεργαζόμαστε την εκδήλωση
		if (status.equals("Approved")) {
			// Προσθήκη της εκδήλωσης στη λίστα του διοργανωτή
			if (request.getType().equals("Add")) {
				request.getSubmittedBy().addEvent(request.getEvent());
			} else if (request.getType().equals("Delete")) {
				// Διαγραφή της εκδήλωσης από τη λίστα του διοργανωτή
				request.getSubmittedBy().removeEvent(request.getEvent());
			}
		}
	}

	// Διαγράφει μία εκδήλωση απευθείας από τη λίστα ενός διοργανωτή.
	public boolean deleteEventDirectly(Event event, Organizer organizer, Employee employee) {
		// Επιστρέφει μια boolean τιμή αν η εκδήλωση υπάρχει στη λίστα του διοργανωτή
		if (organizer.getEvents().contains(event)) {

			// Διαγραφή της εκδήλωση
			boolean removed = organizer.removeEvent(event);
			if (removed) {
				System.out.println(
						"Ο υπάλληλος " + employee.getName() + " διέγραψε την εκδήλωση " + event.getTitle() + ".");
			}
			return removed;
		}
		System.out.println("Η εκδήλωση " + event.getTitle() + " δεν υπάρχει.");
		return false;
	}
}