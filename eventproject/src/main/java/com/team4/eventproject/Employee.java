package com.team4.eventproject;

/*Ένας υπάλληλος μπορεί ενημερώνεται για αιτήσεις προσθήκης ή διαγραφής εκδηλώσεων και
έπειτα να τις εγκρίνει ή να τις απορρίπτει. Επίσης, θα μπορεί να διαγράψει μία τρέχουσα
εκδήλωση.*/
import java.util.List;
import java.util.ArrayList;


public class Employee {

	private String name;
	private String surname;
	private String email;

	

	public Employee(String name, String surname, String email) {
		
		this.name = name;
		this.surname = surname;
		this.email = email;
		
	}

	// Getters και Setters

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
	
   }  