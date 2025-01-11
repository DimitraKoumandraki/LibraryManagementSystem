package com.team4.eventproject.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.team4.eventproject.Event;
import com.team4.eventproject.Services.OrganizerServices;

@RestController
@RequestMapping("/organizer")
public class OrganizerController {

    @Autowired
    private OrganizerServices organizerServices;

    // Προσθήκη νέας εκδήλωσης
    @PostMapping("/addEvent")
    public String addEvent(@RequestBody Event event) {
        organizerServices.addEvent(event);
        return "Η εκδήλωση " + event.getTitle() + " προστέθηκε επιτυχώς!";
    }

    // Αφαίρεση μίας εκδήλωσης
    @DeleteMapping("/removeEvent")
    public String removeEvent(@RequestBody Event event) {
        boolean removed = organizerServices.removeEvent(event);
        if (removed) {
            return "Η εκδήλωση " + event.getTitle() + " αφαιρέθηκε επιτυχώς!";
        } else {
            return "Η εκδήλωση " + event.getTitle() + " δεν βρέθηκε.";
        }
    }

    // Αίτημα έγκρισης για προσθήκη εκδήλωσης
    @PostMapping("/requestAddEvent")
    public String requestAddEvent(@RequestBody Event event) {
        organizerServices.requestAddEvent(event);
        return "Υποβλήθηκε αίτημα έγκρισης για την εκδήλωση " + event.getTitle() + ".";
    }

    
    // Προβολή όλων των εκδηλώσεων του διοργανωτή
    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return organizerServices.getOrganizer().getEvents();
    } 
}
