package com.team4.eventproject.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.team4.eventproject.Visitor;

@Service
public class VisitorServices {
	private List<Visitor> visitors;
	
	public VisitorServices(){
		visitors =new ArrayList<>();
		
		visitors.add(new Visitor("Leonardo", "DiCaprio", "leonardo.dicaprio@gmail.com"));
		visitors.add(new Visitor("Scarlett", "Johansson", "scarlett.johansson@gmail.com"));
		visitors.add(new Visitor("Emma", "Watson", "emma.watson@gmail.com"));
		visitors.add(new Visitor("Robert", "Downey Jr.", "robert.downey@gmail.com"));
		visitors.add(new Visitor("Tom", "Hanks", "tom.hanks@gmail.com"));
		visitors.add(new Visitor("Jennifer", "Lawrence", "jennifer.lawrence@gmail.com"));
		visitors.add(new Visitor("Meryl", "Streep", "meryl.streep@gmail.com"));
		visitors.add(new Visitor("Johnny", "Depp", "johnny.depp@gmail.com"));
		visitors.add(new Visitor("Angelina", "Jolie", "angelina.jolie@gmail.com"));
		visitors.add(new Visitor("Brad", "Pitt", "brad.pitt@gmail.com"));

		
		
	}
	
	// Επιστρέφει όλους τους Επισκέπτες 
	
	public List<Visitor>getAllVisitors() {
		return visitors;
		
	}
	//θέλω να επιστρέψω έναν νέο επισκέπτη στην λίστα 
	public void addVisitor(Visitor visitor) {
		visitors.add(visitor);
	}
	
	

}
