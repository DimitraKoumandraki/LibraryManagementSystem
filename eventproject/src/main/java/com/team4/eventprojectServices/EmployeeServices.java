package com.team4.eventprojectServices;

import java.util.ArrayList;
import java.util.List;

import com.team4.eventproject.ApprovalRequest;
import com.team4.eventproject.Event;
import com.team4.eventproject.Organizer;
import com.team4.eventproject.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServices {

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