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
	                                 @RequestParam(required = false) Integer month,
	                                 @RequestParam(required = false) Integer year,
	                                 @RequestParam(required = false) String location,
	                                 @RequestParam(required = false) String theme) {

	    List<Event> allEvents = eventServices.getAllEvents();
	    List<Event> filteredEvents = new ArrayList<>();

	    // Έλεγχος αν υπάρχουν διαθέσιμες εκδηλώσεις
	    if (allEvents == null || allEvents.isEmpty()) {
	        return filteredEvents; // Επιστροφή κενής λίστας
	    }

	    // Λούπα για να φιλτράρουμε τις εκδηλώσεις
	    for (Event event : allEvents) {
	        boolean matches = true;

	        // Έλεγχος για κάθε κριτήριο
	        if (day != null && !event.getDay().equals(day)) {
	            matches = false;
	        }
	        if (month != null && !event.getMonth().equals(month)) {
	            matches = false;
	        }
	        if (year != null && !event.getYear().equals(year)) {
	            matches = false;
	        }
	        if (location != null && !event.getLocation().equalsIgnoreCase(location)) {
	            matches = false;
	        }
	        if (theme != null && !event.getTheme().equalsIgnoreCase(theme)) {
	            matches = false;
	        }

	        // Αν ταιριάζει, προσθέτουμε στη λίστα
	        if (matches) {
	            filteredEvents.add(event);
	        }
	    }

	    return filteredEvents;
	}



	// Επιστρέφει τις εκδηλώσεις που έχουν εγκριθεί
	@GetMapping("/approved")
	public String getAllApprovedEvents() {
		List<Event> approvedEvents = eventServices.getAllApprovedEvents();
		if (approvedEvents.isEmpty()) {
			return "Δεν υπάρχουν εγκεκριμένες εκδηλώσεις.";
		}
		return approvedEvents.toString(); 
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
			return event.toString(); 
		} else {
			return "Η εκδήλωση με ID " + id + " δεν βρέθηκε.";
		}
	}
   
	@GetMapping("/{id}/availability")
	public String checkAvailability(@PathVariable Long id) {
		return eventServices.checkAvailability(id);
	}
}
