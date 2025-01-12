package com.team4.eventproject;

public class Reservation {
	private Visitor visitor;
	private Event event;
	private Long id; // Μοναδικό ID κράτησης

	
	//constructor για την δηιουργια κρατησης με εναν visitor και ενα event
	public Reservation(Visitor visitor, Event event) {
		this.visitor = visitor;
		this.event = event;
	
	}
	//constructor για να ορισουμε και το id της κρατησης
    public Reservation(Visitor visitor, Event event, Long id) {
        this.visitor = visitor;
        this.event = event;
        this.id = id;
    }

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
