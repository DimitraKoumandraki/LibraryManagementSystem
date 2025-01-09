package com.team4.eventprojectServices;

import java.util.List;

import com.team4.eventproject.Event;
import com.team4.eventproject.Reservation;
import com.team4.eventproject.Visitor;
import java.util.ArrayList;

public class ReservationServices {
	private static List<Reservation> reservations = new ArrayList<>();
	// Δημιουργία κράτησης
	public static boolean createReservation(Visitor visitor, Event event) {
		if (!event.hasAvailableSeats()) {
			System.out.println("Αποτυχία: Δεν υπάρχουν διαθέσιμες θέσεις για την εκδήλωση: " + event.getTitle());
			return false;
		}
		for (Reservation reservation : reservations) {
			if (reservation.getVisitor().equals(visitor) && reservation.getEvent().equals(event)) {
				System.out.println(
						"Αποτυχία: Ο επισκέπτης " + visitor.getName() + " έχει ήδη κράτηση για αυτή την εκδήλωση.");
				return false;
			}
		}

		event.makeReservation(); // Αυξάνει τις τρέχουσες κρατήσεις της εκδήλωσης
		Reservation newReservation = new Reservation(visitor, event);
		reservations.add(newReservation);
		System.out.println(
				"Επιτυχία: Ο επισκέπτης " + visitor.getName() + " έκανε κράτηση για την εκδήλωση: " + event.getTitle());
		return true;
	}

	// Ακύρωση κράτησης
	public static boolean cancelReservation(Visitor visitor, Event event) {
		for (Reservation reservation : reservations) {
			if (reservation.getVisitor().equals(visitor) && reservation.getEvent().equals(event)) {
				reservations.remove(reservation);
				event.cancelReservation(); // Μειώνει τις τρέχουσες κρατήσεις της εκδήλωσης
				System.out.println("Η κράτηση για την εκδήλωση " + event.getTitle() + " ακυρώθηκε.");
				return true;
			}
		}
		System.out.println("Αποτυχία: Δεν υπάρχει κράτηση για τον επισκέπτη " + visitor.getName() + " στην εκδήλωση: "
				+ event.getTitle());
		return false;
	}

	// Προβολή όλων των κρατήσεων
	public static void viewAllReservations() {
		if (reservations.isEmpty()) {
			System.out.println("Δεν υπάρχουν κρατήσεις.");
		} else {
			System.out.println("Λίστα Κρατήσεων:");
			for (Reservation reservation : reservations) {
				System.out.println(
						"- " + reservation.getVisitor().getName() + " -> " + reservation.getEvent().getTitle());
			}
		}
	}

	
	// Επιστρέφει όλες τις κρατήσεις
		public static List<Reservation> getAllReservations() {
			return reservations;
		}
	
	
}
