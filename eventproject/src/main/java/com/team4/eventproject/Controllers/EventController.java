package com.team4.eventproject.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team4.eventproject.Event;
import com.team4.eventproject.Services.EventServices;
import com.team4.eventproject.Services.ReservationServices;

@RestController
@RequestMapping("/events")
public class EventController {

	@Autowired
	private EventServices eventServices;

	// Αναζήτηση των event βάσει των κριτηρίων
	@GetMapping("/search")
	public List<Event> searchEvents(@RequestParam(required = false) Integer day,
			@RequestParam(required = false) Integer month, @RequestParam(required = false) Integer year,
			@RequestParam(required = false) String location, @RequestParam(required = false) String theme) {

		List<Event> allEvents = eventServices.getAllEvents();
		List<Event> EventsAfterFilter = new ArrayList<>();

		for (Event event : allEvents) {
			boolean matches = false;

			// Έλεγχος για την ημέρα του event
			if (day != null && event.getDay().equals(day)) {
				matches = true;
			}

			// Έλεγχος για τον μήνα
			if (month != null && event.getMonth().equals(month)) {
				matches = true;
			}

			// Έλεγχος για το έτος
			if (year != null && event.getYear().equals(year)) {
				matches = true;
			}

			// Έλεγχος για την τοποθεσία
			if (location != null && event.getLocation().equalsIgnoreCase(location)) {
				matches = true;
			}

			// Έλεγχος για το θέμα
			if (theme != null && event.getTheme().equalsIgnoreCase(theme)) {
				matches = true;
			}

			// Αν υπάρχει ταύτιση, προσθήκη στη λίστα
			if (matches) {
				EventsAfterFilter.add(event);
			}
		}

		return EventsAfterFilter;
	}

	// Επιστρέφει τις εκδηλώσεις που έχουν εγκριθεί
	@GetMapping("/approved")
	public String getAllApprovedEvents() {
		List<Event> approvedEvents = eventServices.getAllApprovedEvents();
		if (approvedEvents.isEmpty()) {
			return "Δεν υπάρχουν εγκεκριμένες εκδηλώσεις.";
		}
		return approvedEvents.toString(); // Επιστρέφει τα εγκεκριμένα events
	}

	// Επιστρέφει όλες τις εκδηλώσεις
	@GetMapping("/all")
	public String getAllEvents() {
		List<Event> allEvents = eventServices.getAllEvents();
		if (allEvents == null || allEvents.isEmpty()) {
			return "Δεν υπάρχουν διαθέσιμες εκδηλώσεις.";
		}
		return allEvents.toString();
	}

	// Επιστρέφει μία εκδήλωση βάσει ID
	@GetMapping("/{id}")
	public String getEventById(@PathVariable Long id) {
		Event event = EventServices.findEventById(id);
		if (event != null) {
			return event.toString(); // Επιστρέφει το event ως String
		} else {
			return "Η εκδήλωση με ID " + id + " δεν βρέθηκε.";
		}
	}
   
	@GetMapping("/{id}/availability")
	public String checkAvailability(@PathVariable Long id) {
		return eventServices.checkAvailability(id);
	}
}
