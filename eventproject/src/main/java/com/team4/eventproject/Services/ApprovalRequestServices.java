package com.team4.eventproject.Services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.team4.eventproject.ApprovalRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.team4.eventproject.Event;
import com.team4.eventproject.Organizer;

@Service
public class ApprovalRequestServices {

	private static List<ApprovalRequest> approvalRequests;

	public ApprovalRequestServices() {

		approvalRequests = new ArrayList<>();

		Organizer organizer1 = new Organizer(12345678, "John", "Doe", "Music event organizer", 1L);
		Organizer organizer2 = new Organizer(12345677, "Mary", "Kert", "Fashion event organizer", 2L);

		Event event1 = new Event(1L, "Concert at the Park", "Music", "Enjoy an evening of live music", "Central Park",
				500, 15, 6, 2025, 20, 30, 120, organizer1, "Active");
		Event event2 = new Event(2L, "Rock Night", "Rock", "A thrilling rock concert", "Stadium A", 800, 2, 7, 2025, 21,
				0, 150, organizer1, "Active");

		// Προσθήκη αιτημάτων

		approvalRequests.add(new ApprovalRequest("add", event1, organizer1, LocalDateTime.now(), 1L));
		approvalRequests.add(new ApprovalRequest("delete", event2, organizer2, LocalDateTime.now(), 2L));

	}

	// Επιστρέφει όλα τα αιτήματα έγκρισης
	public List<ApprovalRequest> getAllRequests() {
		return approvalRequests;
	}

	// Αναζητά ένα Approval Request μέσω id από τη λίστα που δίνεται ως όρισμα
	public static ApprovalRequest findById( Long id) {
		for (ApprovalRequest request : approvalRequests) {
			if (request.getId().equals(id)) {
				return request;
			}
		}
		System.out.println("Το αίτημα με ID " + id + " δεν βρέθηκε.");
		return null;
	}
}
