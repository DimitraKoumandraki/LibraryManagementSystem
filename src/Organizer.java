import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;


public class Organizer {
	
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

	
	public ApprovalRequest requestAddEvent(Event event) {
	    System.out.println("Ο οργανωτής " + name + " ζήτησε έγκριση για τη δημιουργία της εκδήλωσης " + event.getTitle());
	    return new ApprovalRequest("Add", event, this, LocalDateTime.now());
	}
	
	
	public void addEvent(Event event) {
        this.events.add(event);
        System.out.println("Η εκδήλωση \"" + event.getTitle() + "\" προστέθηκε από τον διοργανωτή " 
                + this.name + " " + this.surname + ".");
    }

	
	 // Προσπαθεί να αφαιρέσει την εκδήλωση από την λίστα εκδηλώσεων
    public boolean removeEvent(Event event) {
        
    	  boolean removed = this.events.remove(event);
    	    if (removed) {
    	        // Αν η εκδήλωση αφαιρέθηκε επιτυχώς
    	        System.out.println("Η εκδήλωση " + event.getTitle() + " αφαιρέθηκε επιτυχώς.");
    	    } else {
    	        // Αν η εκδήλωση δεν βρέθηκε στη λίστα
    	        System.out.println("Η εκδήλωση " + event.getTitle() + " δεν βρέθηκε.");
    	    }
    	    return removed; // Επιστρέφει true αν αφαιρέθηκε, αλλιώς false
         
      
    }
    
   
}
