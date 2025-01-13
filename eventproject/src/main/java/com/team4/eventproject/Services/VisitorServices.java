package com.team4.eventproject.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.team4.eventproject.Visitor;

@Service
public class VisitorServices {
	private List<Visitor> visitors;

	public VisitorServices() {
		visitors = new ArrayList<>();

		visitors.add(new Visitor("Leonardo", "DiCaprio", "leonardo.dicaprio@gmail.com", 1L));
		visitors.add(new Visitor("Scarlett", "Johansson", "scarlett.johansson@gmail.com", 2L));
		visitors.add(new Visitor("Emma", "Watson", "emma.watson@gmail.com", 3L));
		visitors.add(new Visitor("Robert", "Downey Jr.", "robert.downey@gmail.com", 4L));
		visitors.add(new Visitor("Tom", "Hanks", "tom.hanks@gmail.com", 5L));
		visitors.add(new Visitor("Jennifer", "Lawrence", "jennifer.lawrence@gmail.com", 6L));
		visitors.add(new Visitor("Meryl", "Streep", "meryl.streep@gmail.com", 7L));
		visitors.add(new Visitor("Johnny", "Depp", "johnny.depp@gmail.com", 8L));
		visitors.add(new Visitor("Angelina", "Jolie", "angelina.jolie@gmail.com", 9L));
		visitors.add(new Visitor("Brad", "Pitt", "brad.pitt@gmail.com", 10L));

	}

	// Επιστρέφει όλους τους Επισκέπτες

	public List<Visitor> getAllVisitors() {
		return visitors;

	}

	// θέλω να επιστρέψω έναν νέο επισκέπτη στην λίστα
	public void addVisitor(Visitor visitor) {
		visitors.add(visitor);
	}

	// Αναζήτηση επισκέπτη με βάση το ID
	public Visitor findVisitorById(Long id) {
		for (Visitor visitor : visitors) {
			if (visitor.getId().equals(id)) {
				return visitor;
			}
		}
		return null; // Επιστρέφει null αν δεν βρεθεί επισκέπτης
	}
}
