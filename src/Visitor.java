import java.util.ArrayList;
import java.util.List;

public class Visitor {

        private String name;
        private String surname;
        private String email;
        public List<Reservation> reservations;  // Λίστα με τις κρατήσεις

        public Visitor(String name, String surname, String email) {
            this.name = name;
            this.surname = surname;
            this.email = email;
          this.reservations = new ArrayList<>();//Αρχικοποίηση κρατήσεων
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



    // Αυτή η μέθοδος επιστρέφει την λίστα κρατήσεων
    public List<Reservation> getReservations() {
        return reservations;
    }

    
    // Με αυτή την μέθοδο γίνεται η κράτηση
    public void makeReservation(Event event) {
        for (Reservation reservation : reservations) {
            if (reservation.getEvent().equals(event)) {
                System.out.println("Έχετε ήδη κάνει κράτηση για αυτήν την εκδήλωση.");
                return;
            }
        }
        reservations.add(new Reservation(this, event));
        System.out.println("Ο επισκέπτης " + name + " έκανε κράτηση για την εκδήλωση: " + event.getTitle());
    }
    
    // Με αυτή την μέθοδο γίνεται ακύρωση των κρατήσεων 
    public void cancelReservation(Event event) {
        Reservation toRemove = null;
        for (Reservation reservation : reservations) {
            if (reservation.getEvent().equals(event)) {
                toRemove = reservation;
                break;
            }
        }

        if (toRemove != null) {
            reservations.remove(toRemove);
            System.out.println("Η κράτηση για την εκδήλωση " + event.getTitle() + " ακυρώθηκε.");
        } else {
            System.out.println("Δεν έχετε κάνει κράτηση για αυτήν την εκδήλωση.");
        }
    }

    
    
    //Με αυτή την μέθοδο γίνεται προβολή των κρατήσεων 
    public void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("Δεν έχετε κάνει καμία κράτηση.");
        } else {
            System.out.println("Οι κρατήσεις σας:");
            for (Reservation reservation : reservations) {
                System.out.println("- " + reservation.getEvent().getTitle());
            }
        }
    }
}
    
       
