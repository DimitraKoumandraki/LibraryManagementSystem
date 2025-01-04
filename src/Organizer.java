import java.util.List;
import java.util.ArrayList;


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

	public void addEvent(Event event) {
        this.events.add(event);
    }

    //Επιστρέφει true αν το αντικείμενο αφαιρεθεί επιτυχώς και false αν το αντικείμενο δεν βρεθεί στη λίστα.
    public boolean removeEvent(Event event) {
        return events.remove(event);
    }
}
