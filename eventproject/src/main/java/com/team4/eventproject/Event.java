package com.team4.eventproject;


public class Event {

	private Long id;
	private String title;
    private String theme;
    private String description;
    private String location;
    private Integer maxCapacity;
    private Integer day, month, year;
    private Integer hour, minutes;
    private Integer duration;//minutes
    private Organizer organizer;
    private String status;// active-deactivate
    private Integer currentReservations = 0; // Τρέχουσες κρατήσεις


    public Event(Long id,String title, String theme, String description, String location, Integer maxCapacity, Integer day, Integer month, Integer year, Integer hour, Integer minutes, Integer duration, Organizer organizer, String status) {
        this.id= id;
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
   

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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

	public Integer getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public Integer getDuration() {
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
	public Integer getCurrentReservations() {
		return currentReservations;
	}
	public void setCurrentReservations(Integer currentReservations) {
		this.currentReservations = currentReservations;
	}


	
}
