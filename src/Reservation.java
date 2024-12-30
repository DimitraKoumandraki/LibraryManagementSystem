
public class Reservation {
	private Visitor visitor;
	private Event event;
	
	public Reservation(Visitor visitor, Event event) {
		this.visitor=visitor;
		this.event=event;
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
	
	/*public static void main(String[] args) {
	       
		Visitor visitor = new Visitor("John", "Doe", "john.doe@example.com");
		
		Reservation reservation = new Reservation(visitor, null);
		  
		System.out.println("Visitor:" + reservation.getVisitor().getName());
		
	}*/
	
	
	
	
}
