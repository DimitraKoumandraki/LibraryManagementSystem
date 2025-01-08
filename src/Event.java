import java.util.List;
import java.util.ArrayList;

public class Event {

	private String title;
    private String theme;
    private String description;
    private String location;
    private int maxCapacity;
    private int day, month, year;
    private int hour, minutes;
    private int duration;//minutes
    private Organizer organizer;
    private String status;
    private int currentReservations = 0; // Τρέχουσες κρατήσεις


    public Event(String title, String theme, String description, String location, int maxCapacity, int day, int month, int year, int hour, int minutes, int duration, Organizer organizer, String status) {
        this.title = title;
        this.theme = theme;
        this.description = description;
        this.location = location;
        this.maxCapacity = maxCapacity;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minutes = minutes;
        this.duration = duration;
        this.organizer = organizer;
        this.status = status;

    
    }
   // Αυτή η μέθοδος ελέγχει αν υπάρχουν διαθέσιμες κρατήσεις
    public boolean hasAvailableSeats() {
        return currentReservations < maxCapacity;
    }

    // Μέθοδος για προσθήκη κράτησης
    public boolean makeReservation() {
        if (hasAvailableSeats()) {
            currentReservations++;
            return true;
        }
        return false; // Επιστροφή false αν δεν υπάρχουν διαθέσιμες θέσεις
    }
    
    //Μέθοδος για ακύρωση κράτησης
    public boolean cancelReservation() {
        if (currentReservations > 0) {
            currentReservations--;
            return true;
        }
        return false;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Organizer getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

