package com.team4.eventproject.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.team4.eventproject.Event;
import com.team4.eventproject.Organizer;

@Service
public class EventServices {

	/*
	 * Πραγματοποιεί αναζήτηση στη δεδομένη λίστα με βάση: θέμα, τοποθεσία ή τίτλος.
	 * Εάν ένα κριτήριο είναι κενό, θα αγνοηθεί στην αναζήτηση. Επιστρέφει μια λίστα
	 * που ταιριάζει με τα κριτήρια.
	 */

	 List<Event> events = new ArrayList<>();

	public EventServices() {

		// προσθήkη 2 events για τον έλεγχο της λειτουργίας του κώδικα
		Organizer organizer1 = new Organizer(12345678, "John", "Doe", "Music event organizer",  1L);
		

		Event event1 = new Event(1L, "Concert at the Park", "Music", "Enjoy an evening of live music", "Central Park", 500, 15, 6, 2025, 20, 30, 120, organizer1, "Active");
		Event event2 = new Event(2L, "Rock Night", "Rock", "A thrilling rock concert", "Stadium A", 800, 2, 7, 2025, 21, 0, 150, organizer1, "Active");

		
		events.add(event1);
		events.add(event2);

		organizer1.setEvents(events);  // Συνδέουμε τα events με τον οργανωτή
	}

	// Επιστρέφει τις εκδηλώσεις που έχουν εκγριθεί
	public List<Event> getAllApprovedEvents() {
	    List<Event> approvedEvents = new ArrayList<>();
	    for (Event event : events) {
	        if ("Approved".equalsIgnoreCase(event.getStatus())) {
	            approvedEvents.add(event);
	        }
	    }
	    return approvedEvents;
	}


	// Επιστρέφει όλεςτις εκδηλώσεις
	public List<Event> getAllEvents() {
		return events;
	}

	// Μέθοδος για αναζήτηση εκδήλωσης μέσω ID
	public Event findEventById(Long eventId) {
	    for (Event event : events) {
	        if (event.getId() != null && event.getId().equals(eventId)) {
	            return event;
	        }
	    }
	    return null; // Επιστρέφει null αν δεν βρεθεί το event
	}


	public static List<Event> searchByCriteria(List<Event> events, Integer day, Integer month, Integer year,
			String location, String theme) {

		List<Event> result = new ArrayList<>();

		for (Event event : events) {
			boolean matchesDate = true;

			// Αν το day, month, ή year είναι null, δεν γίνεται έλεγχος γι' αυτά
			if (day != null && event.getDay() != day) {
				matchesDate = false;
			}
			if (month != null && event.getMonth() != month) {
				matchesDate = false;
			}
			if (year != null && event.getYear() != year) {
				matchesDate = false;
			}

			// Αν η ημερομηνία ταιριάζει ή είναι null, προχωράμε με τα υπόλοιπα κριτήρια
			if (matchesDate && (location == null || event.getLocation().equalsIgnoreCase(location))
					&& (theme == null || event.getTheme().equalsIgnoreCase(theme))) {
				result.add(event);
			}
		}
		return result;
	}
}
