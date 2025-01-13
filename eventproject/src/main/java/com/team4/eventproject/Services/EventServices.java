package com.team4.eventproject.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.team4.eventproject.Event;

@Service
public class EventServices {

	/*
	 * Πραγματοποιεί αναζήτηση στη δεδομένη λίστα με βάση: θέμα, τοποθεσία ή τίτλος.
	 * Εάν ένα κριτήριο είναι κενό, θα αγνοηθεί στην αναζήτηση. Επιστρέφει μια λίστα
	 * που ταιριάζει με τα κριτήρια.
	 */

	private List<Event> events = new ArrayList<>();

	public EventServices() {

		// προσθήkη 4 events για τον έλεγχο της λειτουργίας του κώδικα
		events.add(new Event(1L,"Event1", "Music1", " POP", "Location1", 300, 1, 1, 2028, 20, 0, 150, null, "Approved"));
		events.add(new Event(2L,"Event2", "concert", " Rock", "Location2", 390, 4, 4, 2025, 22, 0, 123, null, "Approved"));
		events.add(new Event(3L,"Event3", "Music2", " Pop", "Location1", 300, 1, 1, 2028, 20, 0, 150, null, "Rending"));
		events.add(
				new Event(4L,"Event4", "Music3", " Rock n ROll", "Loc3", 190, 10, 10, 2025, 20, 0, 123, null, "Approved"));

	}

	// Επιστρέφει τις εκδηλώσεις που έχουν εκγριθεί
	public List<Event> getAllApprovedEvents() {
		return events.stream().filter(event -> "Approved".equalsIgnoreCase(event.getStatus())).toList();
	}

	// Επιστρέφει όλεςτις εκδηλώσεις
	public List<Event> getAllEvents() {
		return events;
	}

	// Μέθοδος για αναζήτηση εκδήλωσης μέσω ID
	public Event findEventById(Long eventId) {
		for (Event event : events) {
			if (event.getId().equals(eventId)) {
				return event;
			}
		}
		return null; // Επιστρέφει null αν δεν βρεθεί η εκδήλωση
	}

	// Μέθοδος όπου επιστρέφει null για την ευρυθμη λειτουργία του EventController
	public List<Event> getEvent() {
		return null;
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
