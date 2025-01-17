package com.team4.eventproject;

/*Ένας υπάλληλος μπορεί ενημερώνεται για αιτήσεις προσθήκης ή διαγραφής εκδηλώσεων και
έπειτα να τις εγκρίνει ή να τις απορρίπτει. Επίσης, θα μπορεί να διαγράψει μία τρέχουσα
εκδήλωση.*/
public class Employee {

	private String name;
	private String surname;
	private String email;
	private Long id;

	public Employee(String name, String surname, String email, Long id) {

		this.name = name;
		this.surname = surname;
		this.email = email;
		this.id = id;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Employee{" + "id=" + id + ", name='" + name + '\'' + ", surname='" + surname + '\'' + ", email='"
				+ email + '\'' + '}';
	}

}