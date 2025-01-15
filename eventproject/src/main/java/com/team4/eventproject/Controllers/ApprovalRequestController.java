package com.team4.eventproject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.team4.eventproject.ApprovalRequest;
import com.team4.eventproject.Services.ApprovalRequestServices;

import java.util.List;

@RestController
@RequestMapping("/approval-requests")
public class ApprovalRequestController {

	@Autowired
	// Η δήλωση αυτή επιτρέπει στην cotroller να καλεί μεθόδους από την services
	private ApprovalRequestServices approvalRequestServices;

//Επιστρέφει όλα τα αιτήματα έγκρισης.
	@GetMapping("/all")
	public ResponseEntity<List<ApprovalRequest>> getAllRequests() {
		List<ApprovalRequest> requests = approvalRequestServices.getAllRequests();
		return ResponseEntity.ok(requests);
	}

	// Αναζητά ένα αίτημα μέσω του ID του.
	@GetMapping("/approvalRequest/id/{id}")
	public String findById(@PathVariable Long id) {

		ApprovalRequest approval = ApprovalRequestServices.findById(approvalRequestServices.getAllRequests(), id);
		if (approval != null) {
			return approval.toString();
		} else {
			return "Το αίτημα έγκρισης με ID " + id + " δεν βρέθηκε.";
		}
	}
}
