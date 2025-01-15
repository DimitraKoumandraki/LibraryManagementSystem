package com.team4.eventproject.Services;

import java.util.List;

import com.team4.eventproject.ApprovalRequest;

public class ApprovalRequestServices {

    // Αναζητά ένα Approval Request μέσω id από τη λίστα που δίνεται ως όρισμα
    public static ApprovalRequest findById(List<ApprovalRequest> requests, Long id) {
        for (ApprovalRequest request : requests) {
            if (request.getId().equals(id)) {
                return request; 
            }
        }
        System.out.println("Το αίτημα με ID " + id + " δεν βρέθηκε.");
        return null;
    }
}

