package com.team4.eventproject.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team4.eventproject.Event;
import com.team4.eventproject.Services.EventServices;
import com.team4.eventproject.Services.ReservationServices;

@RestController
@RequestMapping("events")
public class EventController {

	@Autowired
	private EventServices eventServices;

	// Αναζήτηση των event βάσει των κριτηρίων
	@GetMapping("/search")
	public List<Event> searchEvents(@RequestParam(required = false) Integer day,
			@RequestParam(required = false) Integer month, @RequestParam(required = false) Integer year,
			@RequestParam(required = false) String location, @RequestParam(required = false) String theme) {

		List<Event> allEvents = eventServices.getAllEvents();
		return EventServices.searchByCriteria(allEvents, day, month, year, location, theme);
	}

	// Επιστρέφει τις εκδηλώσεις που έχουν εκγριθεί
	@GetMapping("/approved")
	public List<Event> getAllApprovedEvents() {
		return eventServices.getAllApprovedEvents();
	}

	// Επιστρέφει όλες τις εκδηλώσεις
	@GetMapping("/all")
	public List<Event> getAllEvents() {
		return eventServices.getAllEvents();
	}

	@GetMapping("/id")
	public ResponseEntity<?> getEventById(@PathVariable Long id) {
		Event event = eventServices.findEventById(id);
		if (event != null) {
			return ResponseEntity.ok(event);
		} else {
			return ResponseEntity.status(404).body("Η εκδήλωση δεν βρέθηκε.");
		}
	}
	// Επιστρέφει αν υπάρχουν διαθέσιμες θέσεις για μία εκδήλωση.

		@GetMapping("/availability")
		public ResponseEntity<String> checkAvailability(@RequestParam Long id) {
		    // Αναζητούμε την εκδήλωση με το συγκεκριμένο ID
			Event event = eventServices.findEventById(id);
		    if (event == null) {
		        return ResponseEntity.badRequest().body("Η εκδήλωση με το ID " + id + " δεν βρέθηκε.");
		    }

		    // Έλεγχος αν υπάρχουν διαθέσιμες θέσεις
		    boolean hasSeats = ReservationServices.hasAvailableSeats(event);
		    
		    if (hasSeats) {
		        return ResponseEntity.ok("Υπάρχουν διαθέσιμες θέσεις για την εκδήλωση '" + event.getTitle() + "'.");
		    }
		    
		    return ResponseEntity.badRequest().body("Δεν υπάρχουν διαθέσιμες θέσεις για την εκδήλωση '" + event.getTitle() + "'.");
		}
}
