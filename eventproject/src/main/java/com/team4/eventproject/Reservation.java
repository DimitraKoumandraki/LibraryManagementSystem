package com.team4.eventproject;


import java.util.List;

public class Reservation {
	private Visitor visitor;
	private Event event;
	//private static List<Reservation> reservations = new ArrayList<>();

	public Reservation(Visitor visitor, Event event) {
		this.visitor = visitor;
		this.event = event;
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

	


}



