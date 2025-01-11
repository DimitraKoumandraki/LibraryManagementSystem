package com.team4.eventproject.Services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.team4.eventproject.ApprovalRequest;
import com.team4.eventproject.Event;
import com.team4.eventproject.Organizer;

@Service
public class OrganizerServices {
	
	//Δημιουργία organizer για την ενώση μεταξύ organizer και services
    private Organizer organizer;

    public OrganizerServices(Organizer organizer) {
        this.organizer = organizer;
    }

    // Έγκριση για την δημιουργία της
    public ApprovalRequest requestAddEvent(Event event) {
        System.out.println("Ο οργανωτής " + organizer.getName() + " ζήτησε έγκριση για τη δημιουργία της εκδήλωσης " + event.getTitle());
        return new ApprovalRequest("Add", event, organizer, LocalDateTime.now());
    }

    // Προσθήκη νέας εκδήλωσης
    public void addEvent(Event event) {
        organizer.getEvents().add(event);
        System.out.println("Η εκδήλωση \"" + event.getTitle() + "\" προστέθηκε από τον διοργανωτή "
                + organizer.getName() + " " + organizer.getSurname() + ".");
    }

    // Αφαίρεση μίας εκδήλωσης
    public boolean removeEvent(Event event) {
        boolean removed = organizer.getEvents().remove(event);
        if (removed) {
            System.out.println("Η εκδήλωση \"" + event.getTitle() + "\" αφαιρέθηκε επιτυχώς.");
        } else {
            System.out.println("Η εκδήλωση \"" + event.getTitle() + "\" δεν βρέθηκε.");
        }
        return removed;
    }
    
    //Μέθοδος όπου επιστρέφει null για την ευρυθμη λειτουργία του OrganizerController
	public Object getOrganizer() {
		return null;
	}

    
}
