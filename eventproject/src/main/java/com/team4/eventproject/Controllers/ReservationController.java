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
import com.team4.eventproject.Services.ReservationServices;

@RestController
@RequestMapping("reservations")
public class ReservationController {

	@Autowired
	private ReservationServices reservationServices;

	/**
	 * Επιστρέφει τις κρατήσεις που έχει κάνει ένας επισκέπτης.
	 *
	 * @param visitorEmail Το email του επισκέπτη.
	 * @return Λίστα κρατήσεων.
	 */

	@GetMapping("/by-visitor")
	public List<Reservation> getReservationsByVisitor(@RequestParam String visitorEmail) {
		return reservationServices.getReservationsByVisitor(visitorEmail);
	}

	/**
	 * Δημιουργεί μία νέα κράτηση.
	 *
	 * @param visitor Ο επισκέπτης που κάνει την κράτηση.
	 * @param event   Το αντικείμενο της εκδήλωσης.
	 * @return Μήνυμα επιτυχίας ή αποτυχίας.
	 */
	@PostMapping("/create")
	public ResponseEntity<String> createReservation(@RequestBody Visitor visitor, @RequestBody Event event) {
		boolean success = ReservationServices.createReservation(visitor, event);
		if (success) {
			return ResponseEntity.ok("Η κράτηση για την εκδήλωση '" + event.getTitle() + "' ολοκληρώθηκε επιτυχώς.");
		}
		return ResponseEntity.badRequest().body("Αποτυχία: Δεν ήταν δυνατή η δημιουργία της κράτησης.");
	}

	/**
	 * Ακυρώνει μία κράτηση.
	 *
	 * @param visitor Ο επισκέπτης που ακυρώνει την κράτηση.
	 * @param event   Το αντικείμενο της εκδήλωσης.
	 * @return Μήνυμα επιτυχίας ή αποτυχίας.
	 */
	@DeleteMapping("/cancel")
	public ResponseEntity<String> cancelReservation(@RequestBody Visitor visitor, @RequestBody Event event) {
		boolean success = ReservationServices.cancelReservation(visitor, event);
		if (success) {
			return ResponseEntity.ok("Η κράτηση για την εκδήλωση '" + event.getTitle() + "' ακυρώθηκε επιτυχώς.");
		}
		return ResponseEntity.badRequest().body("Αποτυχία: Δεν βρέθηκε κράτηση για τον επισκέπτη.");
	}

	/**
	 * Επιστρέφει όλες τις κρατήσεις.
	 *
	 * @return Λίστα με όλες τις κρατήσεις.
	 */
	@GetMapping
	public List<Reservation> getAllReservations() {
		return ReservationServices.getAllReservations();
	}

	/**
	 * Επιστρέφει αν υπάρχουν διαθέσιμες θέσεις για μία εκδήλωση.
	 *
	 * @param event Το αντικείμενο της εκδήλωσης.
	 * @return Μήνυμα για τη διαθεσιμότητα των θέσεων.
	 */
	@GetMapping("/availability")
	public ResponseEntity<String> checkAvailability(@RequestBody Event event) {
		boolean hasSeats = ReservationServices.hasAvailableSeats(event);
		if (hasSeats) {
			return ResponseEntity.ok("Υπάρχουν διαθέσιμες θέσεις για την εκδήλωση '" + event.getTitle() + "'.");
		}
		return ResponseEntity.badRequest()
				.body("Δεν υπάρχουν διαθέσιμες θέσεις για την εκδήλωση '" + event.getTitle() + "'.");
	}
}