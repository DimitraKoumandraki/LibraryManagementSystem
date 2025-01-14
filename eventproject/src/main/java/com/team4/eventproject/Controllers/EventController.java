package com.team4.eventproject.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private ReservationServices reservationServices;
    
 // Αναζήτηση των event βάσει των κριτηρίων
    @GetMapping("/search")
    public List<Event> searchEvents(@RequestParam(required = true) Long id,
                                     @RequestParam(required = false) Integer day,
                                     @RequestParam(required = false) Integer month,
                                     @RequestParam(required = false) Integer year,
                                     @RequestParam(required = false) String location,
                                     @RequestParam(required = false) String theme) {

        List<Event> allEvents = eventServices.getAllEvents();
        List<Event> EventsAfterFilter = new ArrayList<>();

        for (Event event : allEvents) {
            boolean matches = true;

            // Έλεγχος για το ID
            // Id είναι το μόνο απαραίτητο για την αναζήτηση, τα υπόλοιπα πεδία μπορούν να παραληφθούν
            if (!event.getId().equals(id)) {
                matches = false;
            }
            
            // Έλεγχος για την ημέρα του event
            if (day != null && !event.getDay().equals(day)) {
                matches = false;
            }

            // Έλεγχος για τον μήνα
            if (month != null && !event.getMonth().equals(month)) {
                matches = false;
            }

            // Έλεγχος για το έτος
            if (year != null && !event.getYear().equals(year)) {
                matches = false;
            }

            // Έλεγχος για την τοποθεσία
            if (location != null && !event.getLocation().equalsIgnoreCase(location)) {
                matches = false;
            }

            // Έλεγχος για το θέμα
            if (theme != null && !event.getTheme().equalsIgnoreCase(theme)) {
                matches = false;
            }

            // Αν όλα τα κριτήρια ταιριάζουν, προσθέτουμε το event στη λίστα
            if (matches) {
            	EventsAfterFilter.add(event);
            }
        }

        return EventsAfterFilter;
    }


    // Επιστρέφει τις εκδηλώσεις που έχουν εγκριθεί
    @GetMapping("/approved")
    public List<Event> getAllApprovedEvents() {
        return eventServices.getAllApprovedEvents();
    }

    // Επιστρέφει όλες τις εκδηλώσεις
    @GetMapping("/all")
    public List<Event> getAllEvents() {
        return eventServices.getAllEvents();
    }

    // Επιστρέφει μία εκδήλωση βάσει ID
    @GetMapping("/id")
    public ResponseEntity<?> getEventById(@PathVariable Long id) {
        Event event = eventServices.findEventById(id);
        if (event != null) {
            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.status(404).body("Η εκδήλωση δεν βρέθηκε.");
        }
    }

    // Επιστρέφει αν υπάρχουν διαθέσιμες θέσεις για μία εκδήλωση
    @GetMapping("/id/availability")
    public ResponseEntity<String> checkAvailability(@PathVariable Long id) {
        // Αναζητούμε την εκδήλωση με το συγκεκριμένο ID
        Event event = eventServices.findEventById(id);
        if (event == null) {
            return ResponseEntity.badRequest().body("Η εκδήλωση με το ID " + id + " δεν βρέθηκε.");
        }

        // Έλεγχος αν υπάρχουν διαθέσιμες θέσεις
        boolean hasSeats = reservationServices.hasAvailableSeats(event);

        if (hasSeats) {
            return ResponseEntity.ok("Υπάρχουν διαθέσιμες θέσεις για την εκδήλωση '" + event.getTitle() + "'.");
        }

        return ResponseEntity.badRequest().body("Δεν υπάρχουν διαθέσιμες θέσεις για την εκδήλωση '" + event.getTitle() + "'.");
    }
}

