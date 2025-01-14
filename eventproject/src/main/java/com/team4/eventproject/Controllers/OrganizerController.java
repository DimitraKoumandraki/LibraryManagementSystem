package com.team4.eventproject.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.team4.eventproject.Event;
import com.team4.eventproject.Organizer;
import com.team4.eventproject.Services.OrganizerServices;

@RestController
@RequestMapping("/organizer")
public class OrganizerController {

	@Autowired
	private OrganizerServices organizerServices;

	// Επιστρέφει όλους τους διοργανωτές.

	@GetMapping
	public List<Organizer> getAllOrganizers() {
		return organizerServices.getAllOrganizers();
	}

	// Επιστρέφει έναν διοργανωτή βάσει του ID του.

	@GetMapping("/id")
	public ResponseEntity<?> findOrganizerById(@PathVariable Long id) {
		Organizer organizer = organizerServices.findOrganizerById(id);
		if (organizer != null) {
			return ResponseEntity.ok(organizer);
		} else {
			return ResponseEntity.badRequest().body("Ο διοργανωτής με ID " + id + " δεν βρέθηκε.");
		}
	}

	// Προσθέτει έναν νέο διοργανωτή.

	@PostMapping
	public ResponseEntity<String> addOrganizer(@RequestBody Organizer organizer) {
		organizerServices.addOrganizer(organizer);
		return ResponseEntity.ok("Ο διοργανωτής προστέθηκε επιτυχώς.");
	}

	// Προσθήκη νέας εκδήλωσης
	@PostMapping("/addEvent")
	public String addEvent(@RequestBody Event event) {
		organizerServices.addEvent(event);
		return "Η εκδήλωση " + event.getTitle() + " προστέθηκε επιτυχώς!";
	}

	// Αφαίρεση μίας εκδήλωσης
	@DeleteMapping("/removeEvent")
	public String removeEvent(@RequestBody Event event) {
		boolean removed = organizerServices.removeEvent(event);
		if (removed) {
			return "Η εκδήλωση " + event.getTitle() + " αφαιρέθηκε επιτυχώς!";
		} else {
			return "Η εκδήλωση " + event.getTitle() + " δεν βρέθηκε.";
		}
	}

	// Αίτημα έγκρισης για προσθήκη εκδήλωσης
	@PostMapping("/requestAddEvent")
	public String requestAddEvent(@RequestBody Event event) {
		organizerServices.requestAddEvent(event);
		return "Υποβλήθηκε αίτημα έγκρισης για την εκδήλωση " + event.getTitle() + ".";
	}

	// Προβολή όλων των εκδηλώσεων του διοργανωτή
	@GetMapping("/allevents")
	public List<Event> getAllEvents() {
		return organizerServices.getOrganizer().getEvents();
	}

	@GetMapping("/events")
	public ResponseEntity<?> findEventById(@PathVariable Long eventId) {
		Event event = organizerServices.findEventById(eventId);

		if (event != null) {
			return ResponseEntity.ok(event);
		} else {
			return ResponseEntity.badRequest().body("Η εκδήλωση με ID " + eventId + " δεν βρέθηκε.");
		}
	}
}
