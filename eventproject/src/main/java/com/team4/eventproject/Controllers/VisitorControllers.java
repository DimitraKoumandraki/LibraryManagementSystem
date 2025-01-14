package com.team4.eventproject.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.team4.eventproject.Visitor;
import com.team4.eventproject.Services.VisitorServices;

@RestController
@RequestMapping("/visitors")
public class VisitorControllers {
	@Autowired
	private VisitorServices visitorServices;

	// Επιστρέφει όλους τους Επισκέπτες

	@GetMapping("/all")
	public List<Visitor> getAllVisitors() {
		return visitorServices.getAllVisitors();
	}

	// Αναζήτηση επισκέπτη μέσω ID.

	@GetMapping("/id")
	public ResponseEntity<?> findVisitorById(@PathVariable Long id) {
		Visitor visitor = visitorServices.findVisitorById(id);
		if (visitor != null) {
			return ResponseEntity.ok(visitor);
		} else {
			return ResponseEntity.badRequest().body("Ο επισκέπτης με ID " + id + " δεν βρέθηκε.");
		}
	}

	// Προσθέτει έναν νέο επισκέπτη

	@PostMapping("/add")
	public String addvisitor(@RequestBody Visitor visitor) {
		visitorServices.addVisitor(visitor);
		return "Ο Επισκέπτης προστέθηκε";
	}

}
