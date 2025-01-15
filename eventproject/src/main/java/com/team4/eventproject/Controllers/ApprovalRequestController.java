package com.team4.eventproject.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.team4.eventproject.ApprovalRequest;
import com.team4.eventproject.Services.ApprovalRequestServices;

@RestController
public class ApprovalRequestController {

    private List<ApprovalRequest> approvalRequests;

    public ApprovalRequestController() {
        // Προσθήκη 2 αιτημάτων έγκρισης για τον έλεγχο της λειτουργίας του κώδικα
        this.approvalRequests = List.of(
            new ApprovalRequest("add", null, null, null, 1L),
            new ApprovalRequest("delete", null, null, null, 2L)
        );
    }

    @GetMapping("/approvalRequest/id/{id}")
    public String findById(@PathVariable Long id) {

    	ApprovalRequest approval = ApprovalRequestServices.findById(approvalRequests, id);
        if (approval != null) {
            return approval.toString();
        } else {
            return "Το αίτημα έγκρισης με ID " + id + " δεν βρέθηκε.";
        }
    }
}

