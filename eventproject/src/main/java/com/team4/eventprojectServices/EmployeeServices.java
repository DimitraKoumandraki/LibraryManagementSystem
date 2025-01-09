package com.team4.eventprojectServices;

import java.util.ArrayList;
import java.util.List;

import com.team4.eventproject.ApprovalRequest;
import com.team4.eventproject.Event;
import com.team4.eventproject.Organizer;
import com.team4.eventproject.Employee;

public class EmployeeServices {
	
	
	
	 private List<ApprovalRequest> pendingRequests = new ArrayList<>();

	    // Προσθήκη αιτήματος στη λίστα
	    public void addRequest(ApprovalRequest request) {
	        pendingRequests.add(request);
	    }

	    // Προβολή εκκρεμών αιτημάτων
	    public void viewPendingRequests() {
	        System.out.println("Εκκρεμή αιτήματα:");
	        for (ApprovalRequest request : pendingRequests) {
	            System.out.println("- " + request.getType() + ": " + request.getEvent().getTitle() +
	                               " (Υποβλήθηκε από: " + request.getSubmittedBy().getName() + ")");
	        }
	    }

	    // Επεξεργασία αιτήματος
	    public void processRequest(ApprovalRequest request, String status, String comments, Employee employee) {
	        handleApprovalRequest(request, status, comments, employee);
	        pendingRequests.remove(request); // Αφαίρεση από τη λίστα εκκρεμών αιτημάτων
	    }

	    // Μέθοδος που επεξεργάζεται αιτήματα
	    public void handleApprovalRequest(ApprovalRequest request, String status, String comments, Employee employee) {
	        if (!status.equals("Approved") && !status.equals("Rejected")) {
	            throw new IllegalArgumentException("Invalid status. Use 'Approved' or 'Rejected'.");
	        }
	        request.closeRequest(status, employee, comments);

	        if (status.equals("Approved")) {
	            if (request.getType().equals("Add")) {
	                request.getSubmittedBy().addEvent(request.getEvent());
	            } else if (request.getType().equals("Delete")) {
	                request.getSubmittedBy().removeEvent(request.getEvent());
	            }
	        }
	    }

	    // Διαγραφή εκδήλωσης
	    public boolean deleteEventDirectly(Event event, Organizer organizer, Employee employee) {
	        if (organizer.getEvents().contains(event)) {
	            boolean removed = organizer.removeEvent(event);
	            if (removed) {
	                System.out.println("Ο υπάλληλος " + employee.getName() + " διέγραψε την εκδήλωση " + event.getTitle() + ".");
	            }
	            return removed;
	        }
	        System.out.println("Η εκδήλωση " + event.getTitle() + " δεν υπάρχει.");
	        return false;
	    }
}