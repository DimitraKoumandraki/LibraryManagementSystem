package com.team4.eventproject;

public class Reservation {
	private Visitor visitor;
	private Event event;
	private Long id;
	private String Status; // active/deactivated

	
	

	//constructor για να ορισουμε και το id της κρατησης
    public Reservation(Visitor visitor, Event event, Long id) {
        this.visitor = visitor;
        this.event = event;
        this.id = id;
        this.Status = "Active";
    }
    

	public void setStatus(String status) {
		this.Status = status;
	}
	public String getStatus() {
		return Status;
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
	@Override
    public String toString() {
        return "Reservation{" +
               "id=" + id +
               ", visitor=" + visitor +
               ", event=" + event +
               ", status='" + Status + '\'' +
               '}';
    }

}
