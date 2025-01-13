package com.team4.eventproject;

import org.springframework.boot.SpringApplication;

/** Σύστημα Διαχείρισης Εκδηλώσεων
 * Υλοποιεί τις βασικές λειτουργίες:
 * - Διαχείριση υπαλλήλων (Employees) για την έγκριση/απόρριψη αιτημάτων.
 * - Διαχείριση διοργανωτών (Organizers) για την προσθήκη/διαγραφή εκδηλώσεων.
 * - Διαχείριση εκδηλώσεων (Events) με δυνατότητα κράτησης και ακύρωσης.
 * - Διαχείριση επισκεπτών (Visitors) για κρατήσεις θέσεων σε εκδηλώσεις.
 * - Έλεγχος διαθεσιμότητας και χειρισμός αιτημάτων διαγραφής/προσθήκης εκδηλώσεων.
 */
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventprojectApplication.class, args);
	}

}
