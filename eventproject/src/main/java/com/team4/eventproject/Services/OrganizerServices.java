package com.team4.eventproject.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.team4.eventproject.ApprovalRequest;
import com.team4.eventproject.Employee;
import com.team4.eventproject.Event;
import com.team4.eventproject.Organizer;

@Service
public class OrganizerServices {
	private List<Organizer> organizers;

	public OrganizerServices() {
	    organizers = new ArrayList<>();  // Αρχικοποιούμε τη λίστα
	    
	    // Δημιουργία ενός οργανωτή και προσθήκη στη λίστα
	    Organizer organizer1 = new Organizer(12345678, "John", "Doe", "Music event organizer", 1L);
	    organizers.add(organizer1);
	}
	
	public List<Organizer> getAllOrganizers() {
		return organizers;
	}
	
	// Δημιουργία organizer για την ένωση μεταξύ organizer και services
	private Organizer organizer;

	public OrganizerServices(Organizer organizer) {
		this.organizer = organizer;
	}

	// Έγκριση για την δημιουργία της
	public ApprovalRequest requestAddEvent(Event event) {
		System.out.println("Ο οργανωτής " + organizer.getName() + " ζήτησε έγκριση για τη δημιουργία της εκδήλωσης "
				+ event.getTitle());
		return new ApprovalRequest("Add", event, organizer, LocalDateTime.now());
	}

	// Προσθήκη νέας εκδήλωσης
	public void addEvent(Event event) {
	    // Προσθήκη του event στη λίστα του διοργανωτή
	    organizer.getEvents().add(event);

	    // Αλλαγή του status του event σε activated
	    event.setStatus("activated");

	    // Μήνυμα επιβεβαίωσης
	    System.out.println("Η εκδήλωση " + event.getTitle() + " προστέθηκε από τον διοργανωτή " 
	                       + organizer.getName() + " " + organizer.getSurname() + " και το status άλλαξε σε activated.");
	}


	// Αφαίρεση μίας εκδήλωσης να αλλαζει το status
	public boolean removeEvent(Event event) {
	    // Έλεγχος αν το event υπάρχει στη λίστα
	    if (organizer.getEvents().contains(event)) {
	        // Αλλαγή του status του event σε deactivated
	        event.setStatus("deactivated");

	        System.out.println("Η εκδήλωση " + event.getTitle() + " έχει απενεργοποιηθεί επιτυχώς.");
	        return true;
	    } else {
	        System.out.println("Η εκδήλωση " + event.getTitle() + " δεν βρέθηκε.");
	        return false;
	    }
	}

	// Μέθοδος όπου επιστρέφει Organizer
	public Organizer getOrganizer() {
		return organizer;
	}

	// Αναζητά όλους τους διοργανωτές μέσω id
	public Organizer findOrganizerById(Long organizerId) {
		for (Organizer organizer : organizers) {
			if (organizer.getId().equals(organizerId)) {
				return organizer; // Return the Organizer if found
			}
		}
		System.out.println("Ο διοργανωτής με ID " + organizerId + " δεν βρέθηκε.");
		return null; // Return null if not found
	}

	
      public void addOrganizer(Organizer organizer2) {
    	  
      }

}
