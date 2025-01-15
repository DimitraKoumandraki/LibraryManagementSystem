package com.team4.eventproject.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.team4.eventproject.Event;
import com.team4.eventproject.Reservation;
import com.team4.eventproject.Visitor;
import java.util.stream.Collectors;

import com.team4.eventproject.Organizer;  // Προσθέτουμε την κλάση Organizer

@Service
public class ReservationServices {

    private static List<Reservation> reservations = new ArrayList<>();
    
 // Δεδομένα κρατήσεων για δοκιμή
    private static Organizer organizer1;  
    public ReservationServices() {
        reservations = new ArrayList<>();
        
        organizer1 = new Organizer();  
       
        reservations.add(new Reservation(
            new Visitor("Dimitra", "koum", "jDimitrakoum@example.com", 3L), 
            new Event(3L, "Rock Night", "Rock", "A thrilling rock concert", 
                      "Stadium A", 800, 2, 7, 2025, 21, 0, 150, organizer1, "Active"),1L)
        );
        
   
    }


	// Επιστρέφει τις κρατήσεις που έχει κάνει ένας επισκέπτης.
	public List<Reservation> getReservationsByVisitor(String visitorEmail) {
		return reservations.stream()
				.filter(reservation -> reservation.getVisitor().getEmail().equalsIgnoreCase(visitorEmail))
				.collect(Collectors.toList());
	}

	// Δημιουργία κράτησης για τον επισκέπτη,δίνει μια λεπτομερή αναφορά για την
	// διαδικασία της προσθήκης της κράτησης

	public boolean createReservation(Visitor visitor, Event event) {
		// Ελέγχουμε αν υπάρχουν διαθέσιμες θέσεις
		if (!hasAvailableSeats(event)) {
			return false; // Αν δεν υπάρχουν διαθέσιμες θέσεις, επιστρέφουμε false
		}

		// Ελέγχουμε αν ο επισκέπτης έχει ήδη κάνει κράτηση για την εκδήλωση
		for (Reservation reservation : reservations) {
			if (reservation.getVisitor().equals(visitor) && reservation.getEvent().equals(event)) {
				return false; // Ο επισκέπτης έχει ήδη κράτηση για αυτή την εκδήλωση
			}
		}

		// Αν δεν υπήρξε κράτηση, προσθέτουμε μια νέα κράτηση
		event.setCurrentReservations(event.getCurrentReservations() + 1);
		Reservation newReservation = new Reservation(visitor, event, null);
		reservations.add(newReservation);

		return true; // Η κράτηση ολοκληρώθηκε επιτυχώς
	}

	// Ακύρωση κράτησης
	public boolean cancelReservation(Visitor visitor, Event event) {
		for (Reservation reservation : reservations) {
			if (reservation.getVisitor().equals(visitor) && reservation.getEvent().equals(event)) {
				// Ενημερώνουμε το status σε "Deactivated"
				reservation.setStatus("Deactivated");

				// Μειώνουμε τις τρέχουσες κρατήσεις της εκδήλωσης
				event.setCurrentReservations(event.getCurrentReservations() - 1);

				System.out.println("Η κράτηση για την εκδήλωση " + event.getTitle() + " ακυρώθηκε.");
				return true;
			}
		}
		System.out.println("Αποτυχία: Δεν υπάρχει κράτηση για τον επισκέπτη " + visitor.getName() + " στην εκδήλωση: "
				+ event.getTitle());
		return false;
	}
	
	
	private void updateReservationsForDeactivatedEvent(Event event) {
		// Ενημέρωση των κρατήσεων για το απενεργοποιημένο event
		for (Reservation reservation : ReservationServices.getAllReservations()) {
			if (reservation.getEvent().equals(event)) {
				reservation.setStatus("Deactivated");
				System.out.println("Η κράτηση του επισκέπτη " + reservation.getVisitor().getEmail()
						+ " για την εκδήλωση " + event.getTitle() + " έχει ακυρωθεί.");
			}
		}
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

	// Αυτή η μέθοδος ελέγχει αν υπάρχουν διαθέσιμες κρατήσεις
	public static boolean hasAvailableSeats(Event event) {
		return event.getCurrentReservations() < event.getMaxCapacity();

	}

	// Μέθοδος που χρησιμοποιείται όταν θέλουμε να αυξήσουμε τον αριθμό κρατήσεων
	// μιας εκδήλωσης
	public boolean makeReservation(Event event) {
		if (hasAvailableSeats(event)) {
			event.setCurrentReservations(event.getCurrentReservations() + 1);
			return true;
		}
		return false; // Επιστροφή false αν δεν υπάρχουν διαθέσιμες θέσεις
	}

	// Αναζήτηση κράτησης μέσω ID
	// Ελέγχει αν το ID της κράτησης (reservation.getId()) ταιριάζει με το
	// reservationId που δίνεται ως είσοδος.
	// Επιστρέφει το αντικείμενο Reservation αν βρεθεί.
	public Reservation findReservationById(Long reservationId) {
	    if (reservations == null || reservations.isEmpty()) {
	        System.out.println("Η λίστα κρατήσεων είναι κενή ή δεν έχει αρχικοποιηθεί.");
	        return null;
	    }

	    for (Reservation reservation : reservations) {
	        if (reservation.getId().equals(reservationId)) {
	            return reservation; // Επιστροφή της κράτησης αν βρεθεί
	        }
	    }
	    System.out.println("Η κράτηση με ID " + reservationId + " δεν βρέθηκε.");
	    return null; // Επιστροφή null αν δεν βρεθεί κράτηση
	}

}

