package com.team4.eventproject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Organizer {

	/**
	 * Η κλάση Organizer αναπαριστά έναν διοργανωτή εκδηλώσεων που θα μπορεί να
	 * κάνει προσθήκη εκδηλώσεων,αφαίρεση εκδηλώσεων,δημιουργία αιτημάτων έγκρισης
	 * για νέες εκδηλώσεις.
	 */

	private Integer afm;
	private String name;
	private String surname;
	private String description;
	private List<Event> events;
	private Long id;

	// Default constructor
	public Organizer() {

		this.events = new ArrayList<>();

	}

	public Organizer(Integer afm, String name, String surname, String description, Long id) {
		this.afm = afm;
		this.name = name;
		this.surname = surname;
		this.description = description;
		this.events = new ArrayList<>(); // Αρχικοποίηση της λίστας event
		this.id = id;
	}

	public Integer getAfm() {
		return afm;
	}

	public void setAfm(Integer afm) {
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void addEvent(Event event) {
		events.add(event);
	}

	public boolean removeEvent(Event event) {
		return events.remove(event);
	}
	  @Override
	    public String toString() {
	        return "Organizer{" +
	                "afm=" + afm +
	                ", name='" + name + '\'' +
	                ", surname='" + surname + '\'' +
	                ", description='" + description + '\'' +
	                ", events=" + events +
	                ", id=" + id +
	                '}';
	    }

}
