package com.team4.eventproject;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;


public class Organizer {
	
	/**
	  Η κλάση Organizer αναπαριστά έναν διοργανωτή εκδηλώσεων που θα μπορεί να κάνει
	  προσθήκη εκδηλώσεων,αφαίρεση εκδηλώσεων,δημιουργία αιτημάτων έγκρισης για νέες εκδηλώσεις.
	 */
	
	private String afm;
    private String name;
    private String surname;
    private String description;
    private List<Event> events;

    
    public Organizer(String afm, String name, String surname, String description) {
        this.afm = afm;
        this.name = name;
        this.surname = surname;
        this.description = description;
        this.events = new ArrayList<>();
    }
    
    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	

    public void addEvent(Event event) {
        events.add(event);
    }

    public boolean removeEvent(Event event) {
        return events.remove(event);
    }
       
}
