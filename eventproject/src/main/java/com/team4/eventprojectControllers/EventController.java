package com.team4.eventprojectControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team4.eventproject.Event;
import com.team4.eventprojectServices.EventServices;

@RestController
@RequestMapping("events")
public class EventController {
	
	@Autowired
    private EventServices eventServices;

    // Αναζήτηση των event βάσει των κριτηρίων
    @GetMapping("/search")
    public List<Event> searchEvents(
            @RequestParam Integer day,
            @RequestParam Integer month,
            @RequestParam Integer year,
            @RequestParam String location,
            @RequestParam String theme) {
        
        List<Event> events = fetchAllEvents(); 
        
        return eventServices.searchByCriteria(events, day, month, year, location, theme);
    }

	private List<Event> fetchAllEvents() {
		return null;
	}
	

}
