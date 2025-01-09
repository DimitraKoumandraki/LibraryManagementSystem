package com.team4.eventproject;
import java.time.LocalDateTime;

public class ApprovalRequest {
	private String type; // Ο τύπος είναι "add" ή "delete"
	private Event event;
	private Organizer submittedBy;// Ο διοργανωτής που υπέβαλλε το αίτημα
	private LocalDateTime createdAt; // Η ημερομηνία/ώρα που δημιουργήθηκε
	private String status;// Το αίτημα από "pending" γίνεται "approved" ή "rejected"
	private Employee handledBy;// Ο υπάλληλος που διαχειρίστηκε το αίτημα
	private LocalDateTime closedAt;// Η ημερομηνία/ώρα που ολοκληρώθηκε
	private String comments;

	public ApprovalRequest(String type, Event event, Organizer submittedBy, LocalDateTime createdAt) {
		this.type = type;
		this.event = event;
		this.submittedBy = submittedBy;
		this.createdAt = createdAt;
		this.status = "pending"; // Αρχική κατάσταση
		this.handledBy = null;
		this.closedAt = null;
		this.comments = "";
	}

	// Getters και Setters

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Organizer getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(Organizer submittedBy) {
		this.submittedBy = submittedBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Employee getHandledBy() {
		return handledBy;
	}

	public void setHandledBy(Employee handledBy) {
		this.handledBy = handledBy;
	}

	public LocalDateTime getClosedAt() {
		return closedAt;
	}

	public void setClosedAt(LocalDateTime closedAt) {
		this.closedAt = closedAt;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	// Μέθοδος για να κλείσει το αίτημα
	public void closeRequest(String status, Employee handledBy, String comments) {
		this.status = status;
		this.handledBy = handledBy;
		this.comments = comments;
		this.closedAt = LocalDateTime.now();
	}
}


