package com.team4.eventproject.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team4.eventproject.Event;
import com.team4.eventproject.Reservation;
import com.team4.eventproject.Visitor;
import com.team4.eventproject.Services.EventServices;
import com.team4.eventproject.Services.ReservationServices;
import com.team4.eventproject.Services.VisitorServices;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

	@Autowired
	private ReservationServices reservationServices;

	@Autowired
	private VisitorServices visitorServices; // Προσθήκη VisitorServices
	@Autowired
	private EventServices eventServices;

	// Επιστρέφει τις κρατήσεις που έχει κάνει ένας επισκέπτης.

	@GetMapping("/by-visitor")
	public ResponseEntity<?> getReservationsByVisitor(@RequestParam(required = false) String visitorEmail) {
		if (visitorEmail == null || visitorEmail.isEmpty()) {
			return ResponseEntity.badRequest().body("Το email του επισκέπτη είναι υποχρεωτικό.");
		}

		List<Reservation> reservations = reservationServices.getReservationsByVisitor(visitorEmail);

		if (reservations.isEmpty()) {
			return ResponseEntity.ok("Δεν βρέθηκαν κρατήσεις για τον επισκέπτη με email: " + visitorEmail);
		}

		return ResponseEntity.ok(reservations);
	}

	// Αναζήτηση κράτησης μέσω ID.

	@GetMapping("/id/{id}")
	public String findReservationById(@PathVariable Long id) {
		Reservation reservation = reservationServices.findReservationById(id);
		if (reservation != null) {
			return reservation.toString();
		} else {
			return "Η κράτηση με ID " + id + " δεν βρέθηκε.";
		}
	}

	// Δημιουργεί μία νέα κράτηση.
	@PostMapping("/create")
	public ResponseEntity<String> createReservation(@RequestParam Long visitorId, @RequestParam Long eventId) {
		// Αναζητούμε τον Visitor και το Event μέσω των id
		Visitor visitor = visitorServices.findVisitorById(visitorId); // Χρησιμοποιούμε τον VisitorServices για να
																		// βρούμε τον Visitor
		Event event = eventServices.findEventById(eventId);

		// Αν δεν βρεθεί ο Visitor ή το Event, επιστρέφουμε σφάλμα
		if (visitor == null) {
			return ResponseEntity.badRequest().body("Δεν βρέθηκε επισκέπτης με το ID " + visitorId);
		}

		if (event == null) {
			return ResponseEntity.badRequest().body("Δεν βρέθηκε εκδήλωση με το ID " + eventId);
		}

		// Καλούμε την υπηρεσία για τη δημιουργία της κράτησης
		boolean success = reservationServices.createReservation(visitor, event);

		// Επιστρέφουμε την κατάλληλη απάντηση
		if (success) {
			return ResponseEntity.ok("Η κράτηση για την εκδήλωση '" + event.getTitle() + "' ολοκληρώθηκε επιτυχώς.");
		} else {
			return ResponseEntity.badRequest().body("Αποτυχία: Δεν ήταν δυνατή η δημιουργία της κράτησης.");
		}
	}

	@DeleteMapping("/cancel")
	public ResponseEntity<String> cancelReservation(@RequestBody Visitor visitor, @RequestBody Event event) {
		// Αντί να διαγράψουμε, θα ενημερώσουμε το status της κράτησης.
		for (Reservation reservation : ReservationServices.getAllReservations()) {
			if (reservation.getVisitor().equals(visitor) && reservation.getEvent().equals(event)) {
				// Ενημερώνουμε το status σε "Deactivated"
				reservation.setStatus("Deactivated");

				// Μειώνουμε τις τρέχουσες κρατήσεις της εκδήλωσης
				event.setCurrentReservations(event.getCurrentReservations() - 1);

				System.out.println("Η κράτηση για την εκδήλωση " + event.getTitle() + " ακυρώθηκε.");
				return ResponseEntity
						.ok("Η κράτηση για την εκδήλωση '" + event.getTitle() + "' ακυρώθηκε (Status: Deactivate).");
			}
		}
		return ResponseEntity.badRequest().body("Αποτυχία: Δεν βρέθηκε κράτηση για τον επισκέπτη.");
	}

	// Ενημερώνει τις κρατήσεις ενός απενεργοποιημένου event
	@PutMapping("/deactivate/{eventId}")
	public ResponseEntity<String> updateReservationsForDeactivatedEvent(@PathVariable Long eventId) {
		reservationServices.updateReservationsForDeactivatedEvent(eventId);
		return ResponseEntity.ok("Όλες οι σχετικές κρατήσεις έχουν ενημερωθεί ως 'Deactivated'.");
	}

	// Επιστρέφει όλες τις κρατήσεις.

	@GetMapping("/all")
	public List<Reservation> getAllReservations() {
		return ReservationServices.getAllReservations();
	}

}