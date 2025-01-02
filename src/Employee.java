/*Ένας υπάλληλος μπορεί ενημερώνεται για αιτήσεις προσθήκης ή διαγραφής εκδηλώσεων και
έπειτα να τις εγκρίνει ή να τις απορρίπτει. Επίσης, θα μπορεί να διαγράψει μία τρέχουσα
εκδήλωση.*/


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
	
    //Μέθοδος που επεξεργάζεται αιτήματα δημιουργίας ή διαγραφής εκδηλώσεων.
	
	
    public void handleApprovalRequest(ApprovalRequest request, String status, String comments)
       {
    	 // Έλεγχος αν το status είναι έγκυρο
        if (!status.equals("Approved") && !status.equals("Rejected")) {
            throw new IllegalArgumentException("Invalid status. Use 'Approved' or 'Rejected'.");
        }
        // Ενημέρωση του αιτήματος με το status
        request.closeRequest( status, this ,comments);
        
        /* Αν το status είναι "Approved":
	     * - Εάν το αίτημα είναι για προσθήκη εκδήλωσης ("Add"), η εκδήλωση προστίθεται
	     *   στη λίστα του διοργανωτή.
	     * - Εάν το αίτημα είναι για διαγραφή εκδήλωσης ("Delete"), η εκδήλωση διαγράφεται
	     *   από τη λίστα του διοργανωτή.
	     **/

        // Εάν το αίτημα εγκρίθηκε (status = "Approved")
        if (status.equals("Approved")) {
            if (request.getType().equals("Add")) {
        // Προσθήκη της εκδήλωσης στη λίστα του διοργανωτή
                request.getSubmittedBy().addEvent(request.getEvent());
            } else if (request.getType().equals("Delete")) {
        // Διαγραφή της εκδήλωσης από τη λίστα του διοργανωτή
                request.getSubmittedBy().removeEvent(request.getEvent());
            }
        }
    }
       /* Αν το status είναι "Rejected":
        * - Η εκδήλωση παραμένει ως έχει, αλλά ενημερώνεται το αίτημα με την απόρριψη.
        */
    public boolean deleteEvent(Event event, Organizer organizer) {
        return organizer.removeEvent(event);
    }

    // Εμφάνιση στοιχείων υπαλλήλου
    public String getDetails() {
    	 return "Employee: " + name + " " + surname + " | Email: " + email;
    	 
         }
   }  
    
