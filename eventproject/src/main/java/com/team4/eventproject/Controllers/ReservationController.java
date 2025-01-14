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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reservations")
public class ReservationController {

	@Autowired
	private ReservationServices reservationServices;

	// Επιστρέφει τις κρατήσεις που έχει κάνει ένας επισκέπτης.

	@GetMapping("/by-visitor")
	public List<Reservation> getReservationsByVisitor(@RequestParam String visitorEmail) {
		return reservationServices.getReservationsByVisitor(visitorEmail);
	}

	// Αναζήτηση κράτησης μέσω ID.

	@GetMapping("/id")
	public ResponseEntity<?> findReservationById(@PathVariable Long id) {
		Reservation reservation = reservationServices.findReservationById(id);
		if (reservation != null) {
			return ResponseEntity.ok(reservation);
		} else {
			return ResponseEntity.badRequest().body("Η κράτηση με ID " + id + " δεν βρέθηκε.");
		}
	}
	// Δημιουργεί μία νέα κράτηση.

	@PostMapping("/create")
	public ResponseEntity<String> createReservation(@RequestBody Visitor visitor, @RequestBody Event event) {
		boolean success = ReservationServices.createReservation(visitor, event);
		if (success) {
			return ResponseEntity.ok("Η κράτηση για την εκδήλωση '" + event.getTitle() + "' ολοκληρώθηκε επιτυχώς.");
		}
		return ResponseEntity.badRequest().body("Αποτυχία: Δεν ήταν δυνατή η δημιουργία της κράτησης.");
	}

	// Ακυρώνει μία κράτηση.

	@DeleteMapping("/cancel")
	public ResponseEntity<String> cancelReservation(@RequestBody Visitor visitor, @RequestBody Event event) {
		boolean success = ReservationServices.cancelReservation(visitor, event);
		if (success) {
			return ResponseEntity.ok("Η κράτηση για την εκδήλωση '" + event.getTitle() + "' ακυρώθηκε επιτυχώς.");
		}
		return ResponseEntity.badRequest().body("Αποτυχία: Δεν βρέθηκε κράτηση για τον επισκέπτη.");
	}

	// Επιστρέφει όλες τις κρατήσεις.

	@GetMapping("/all")
	public List<Reservation> getAllReservations() {
		return ReservationServices.getAllReservations();
	}

}