
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


         // Δημιουργία Visitors
        List<Visitor> visitors = new ArrayList<>();
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


        // Δημιουργία Employees
        Employee emp1 = new Employee("Γιώργος", "Παπαδόπουλος", "george.papadopoulos@gmail.com");
        Employee emp2 = new Employee("Μαρία", "Κωνσταντίνου", "maria.konstantinou@gmail.com");
        Employee emp3 = new Employee("Νίκος", "Καραγιάννης", "nikos.karagiannis@gmail.com");

        // Δημιουργία Organizers
        Organizer org1 = new Organizer("Αλέξανδρος", "Ιωάννου", "afm1", "Οργανωτής Συναυλιών");
        Organizer org2 = new Organizer("Ελένη", "Χατζηγεωργίου", "afm2", "Οργανωτής Θεάτρων");
        Organizer org3 = new Organizer("Κώστας", "Δημητρίου", "afm3", "Οργανωτής Εργαστηρίων");
        Organizer org4 = new Organizer("Αντωνία", "Σωτηρίου", "afm4", "Οργανωτής Φεστιβάλ");






      Event event1 = new Event("Robotics Workshop", "Technology", "Learn the basics of building and programming robots.", "Thessaloniki International Fair",
                    500,8,3,2025,8,30,160,  org1, "Pending");

     Event event2 = new Event("Wine Tasting - Local Vineyards", "Culinary", "Discover the finest wines from local vineyards.",
                    " Thessaloniki Wine Cellar", 200, 15, 5, 2025, 10, 0, 120, org1, "Approved");

     Event event3 = new Event("Fashion Show - Fall Collection", "Fashion", "A stunning presentation of the latest fall trends.", "Thessaloniki Art Center",
            300, 10, 6, 2025, 19, 30, 180, org2, "Pending");

     Event event4 = new Event("Yoga Retreat - Wellness and Relaxation", "Health", "A weekend of yoga, meditation, and relaxation.",
            "Thessaloniki Concert Hall", 150, 12, 6, 2025, 18, 0, 120, org2, "Approved");

     Event event5 = new Event("Startup Expo - Innovation and Networking", "Business", "Meet the most innovative startups and network with entrepreneurs.",
            " Thessaloniki International Fair", 100, 5, 7, 2025, 9, 0, 180, org3, "Pending");

     Event event6 = new Event("Classical Music Concert - Beethoven's Symphony", "Music", "An orchestra performing Beethoven's greatest symphonies.",
            "Thessaloniki Concert Hall", 50, 8, 7, 2025, 17, 0, 120, org3, "Approved");


     Event event7 = new Event("Marathon - Run for a Cause", "Sports", "Participate in a marathon to support a charity.",
            "Thessaloniki Waterfront", 400, 20, 8, 2025, 10, 0, 240, org4, "Pending");
 
     Event event8 = new Event("Science Fair - Discover the Future", "Art", "Explore interactive exhibits and talks from leading scientists.",
            "Thessaloniki Science Center", 500, 22, 8, 2025, 18, 0, 300, org4, "Approved");
     
     
      // Δημιουργία event με μικρή χωρητικότητα για δοκιμή 
      Event limitedEvent = new Event("Exclusive Art Tour", "Art", "VIP tour of the art gallery.", "Thessaloniki Art Center", 2, 1, 1, 2025, 10, 0, 120, org1, "Approved");

      // Δοκιμή κρατήσεων
      Visitor visitor1 = visitors.get(0);
      Visitor visitor2 = visitors.get(1);
      Visitor visitor3 = visitors.get(2);

      visitor1.makeReservation(limitedEvent); // Επιτυχής κράτηση
      visitor2.makeReservation(limitedEvent); // Επιτυχής κράτηση
      visitor3.makeReservation(limitedEvent); // Αποτυχία: Δεν υπάρχουν διαθέσιμες θέσεις

           List<Event> allEvents = Arrays.asList(event1, event2, event3, event4, event5, event6, event7, event8);

      // Δημιουργία αιτημάτων προσθήκης
      ApprovalRequest addRequest1 = new ApprovalRequest("add", event1, org1, LocalDateTime.now());
      ApprovalRequest addRequest2 = new ApprovalRequest("add", event2, org2, LocalDateTime.now());

      //Έγκριση αιτημάτων προσθήκης
      emp1.handleApprovalRequest(addRequest1, "Approved", "Η εκδήλωση εγκρίθηκε επιτυχώς.");
      emp2.handleApprovalRequest(addRequest2, "Approved", "Η εκδήλωση εγκρίθηκε επιτυχώς.");

      // Προσθήκη της νέας εκδήλωσης για τους δύο πρώτους διοργανωτές
      org1.addEvent(event1);
      org2.addEvent(event2);

      // Δημιουργία αιτημάτων διαγραφής
      ApprovalRequest deleteRequest1 = new ApprovalRequest("delete", event5, org3, LocalDateTime.now());
      ApprovalRequest deleteRequest2 = new ApprovalRequest("delete", event7, org4, LocalDateTime.now());

      //Έγκριση αιτημάτων διαγραφής
      emp3.handleApprovalRequest(deleteRequest1, "Approved", "Η εκδήλωση αφαιρέθηκε επιτυχώς.");
      emp1.handleApprovalRequest(deleteRequest2, "Approved", "Η εκδήλωση αφαιρέθηκε επιτυχώς.");

    
     //Αποθήκευση του txt στο αρχείο
     String stringToWrite = "Java files";
     try {
        BufferedWriter writer = new BufferedWriter(new FileWriter("event-agenda.txt"));
        writer.write(stringToWrite);
        writer.close();
        System.out.println("Το αρχείο 'event-agenda.txt' δημιουργήθηκε επιτυχώς.");
       } catch (IOException e) { 
        System.out.println("Δημιουργήθηκε σφάλμα κατά την εγγραφή στο αρχειο.");
        e.printStackTrace();

       }
    

     //Για να διαγράψω μια εκδήλωση απο τους άλλους δύο διοργανωτές πρέπει πρώτα να προσθέσω σε αυτούς κάποια εκδήλωση
     org3.addEvent(new Event("Photography Workshop", "Art", "Learn the basics of photography",
            "Thessaloniki Art Center", 100, 5, 7, 2025, 9, 0, 180, org3, "Pending"));

      org4.addEvent(new Event("Book Fair", "Literature", "Explore new and classic books",
            "Thessaloniki Concert Hall", 400, 20, 8, 2025, 10, 0, 240, org4, "Pending"));
      // Διαγραφή εκδήλωσης για org3
      if (!org3.getEvents().isEmpty()) {
        Event eventToDelete1 = org3.getEvents().get(0); // Επιλογή της πρώτης εκδήλωσης
        emp1.deleteEventDirectly(eventToDelete1, org3); // Διαγραφή της εκδήλωσης
        System.out.println("Η εκδήλωση " + eventToDelete1.getTitle() + " διαγράφηκε από τον οργανωτή "+ org3.getName() + ".");
      } else {
        System.out.println("Η λίστα εκδηλώσεων για τον οργανωτή" + org4.getName() + " είναι κενή.");
       }

       // Διαγραφή εκδήλωσης για org4
       if (!org4.getEvents().isEmpty()) {
        Event eventToDelete2 = org4.getEvents().get(0); // Επιλογή της πρώτης εκδήλωσης
        emp1.deleteEventDirectly(eventToDelete2, org4); // Διαγραφή της εκδήλωσης
        System.out.println("Η εκδήλωση " + eventToDelete2.getTitle() + " διαγράφηκε από τον οργανωτή "+ org4.getName() + ".");
      } else {
        System.out.println("Η λίστα εκδηλώσεων για τον οργανωτή " + org4.getName() + " είναι κενή.");
    }

        // Ζητούμε από τον χρήστη να εισάγει τα κριτήρια για τα event
        System.out.println("Εισάγετε τα κριτήρια αναζήτησης. Αφήστε το πεδίο κενό για να παραλείψετε κάποιο κριτήριο.");

        System.out.print("Theme (e.g., Art, Music): ");
        String theme = scanner.nextLine();
        if (theme.isBlank()) theme = null;

        System.out.print("Location (e.g., Athens, Thessaloniki): ");
        String location = scanner.nextLine();
        if (location.isBlank()) location = null;

        Integer day = null;
        Integer month = null;
        Integer year = null;

         // Ζήτηση για την ημέρα
          System.out.print("Enter day (1-31): ");
          String dayInput = scanner.nextLine();

           if (!dayInput.isBlank()) {
            day = Integer.parseInt(dayInput);  // Αν υπάρχει τιμή, την αποθηκεύουμε στην day

         // Ζήτηση για τον μήνα αν η ημέρα είναι έγκυρη
            System.out.print("Enter month (1-12): ");
            String monthInput = scanner.nextLine();

            if (!monthInput.isBlank()) {
                month = Integer.parseInt(monthInput);  // Αν υπάρχει τιμή, την αποθηκεύουμε στην month

                // Ζήτηση για το έτος αν ο μήνας είναι έγκυρος
                System.out.print("Enter year (e.g., 2025): ");
                String yearInput = scanner.nextLine();

                if (!yearInput.isBlank()) {
                    year = Integer.parseInt(yearInput);  // Αν υπάρχει τιμή, την αποθηκεύουμε στην year
                }
            }
        }


         // Εκτέλεση αναζήτησης βάσει των κριτηρίων
         List<Event> searchResults = Visitor.searchByCriteria(allEvents, day, month, year, location, theme);

         // Εκτύπωση αποτελεσμάτων
         System.out.println("Αποτελέσματα:");
         if (searchResults.isEmpty()) {
            System.out.println("Δεν βρέθηκαν εκδηλώσεις που να ταιριάζουν με τα κριτήρια σας.");
         } else {
            for (Event event : searchResults) {
                // Εκτύπωση των λεπτομερειών του κάθε event
                System.out.println("Event: " + event.getTitle());
                System.out.println("Theme: " + event.getTheme());
                System.out.println("Location: " + event.getLocation());
                System.out.println("Date: " + event.getDay() + "/" + event.getMonth() + "/" + event.getYear());
                System.out.println("Time: " + event.getHour() + ":" + event.getMinutes()); // Formatting the minutes with leading zero if needed
                System.out.println("--------------------------------------------");

              }
         }

        // Εμφάνιση διαθέσιμων εκδηλώσεων
        System.out.print("Events:");
        for (Event event : allEvents) {
        System.out.println("- " + event.getTitle());
        }

       // Ζητούμε από τον χρήστη να εισάγει τον τίτλο της εκδήλωσης
        System.out.print("Εισάγετε τον τίτλο της εκδήλωσης που θέλετε να κρατήσετε: ");
        String eventTitle = scanner.nextLine();

       // Εύρεση της εκδήλωσης με βάση τον τίτλο
        Event chosenEvent = null;
        for (Event event : allEvents) {
        if (event.getTitle().equalsIgnoreCase(eventTitle)) {
            chosenEvent = event;
            break;
            }
        }
    
    
        // Εμφάνιση διαθέσιμων επισκεπτών
        System.out.println("Διαθέσιμοι επισκέπτες:");
        for (int i = 0; i < visitors.size(); i++) {
            Visitor v = visitors.get(i);
            System.out.println((i + 1) + ". " + v.getName() + " " + v.getSurname());
        }

        // Επιλογή επισκέπτη
        System.out.print("Επιλέξτε τον αριθμό του επισκέπτη: ");
        int visitorIndex = scanner.nextInt();
        scanner.nextLine(); 

        if (visitorIndex < 1 || visitorIndex > visitors.size()) {
            System.out.println("Μη έγκυρη επιλογή επισκέπτη.");
            return;
        }

        Visitor visitor = visitors.get(visitorIndex - 1);
        System.out.println("Επιλέξατε τον επισκέπτη: " + visitor.getName() + " " + visitor.getSurname());
    

       // Δημιουργία κράτησης αν βρεθεί η εκδήλωση
        if (chosenEvent != null) {
        visitor.makeReservation(chosenEvent);
         } else {
        System.out.println("Δεν βρέθηκε εκδήλωση με τον τίτλο: " + eventTitle);
        }

       // Προβολή κρατήσεων
        System.out.println("Η κράτηση σας:");
        visitor.viewReservations();

       // Ακύρωση κράτησης
        System.out.print("Θέλετε να ακυρώσετε κάποια κράτηση; (yes/no): ");
        String cancelChoice = scanner.nextLine();
        if (cancelChoice.equalsIgnoreCase("yes")) {
        System.out.print("Εισάγετε τον τίτλο της εκδήλωσης που θέλετε να ακυρώσετε: ");
        String cancelEventTitle = scanner.nextLine();
        Event cancelEvent = null;
        // Εύρεση της εκδήλωσης που θέλει να ακυρώσει
       
        for (Reservation reservation : visitor.getReservations()) {
            if (reservation.getEvent().getTitle().equalsIgnoreCase(cancelEventTitle)) {
                cancelEvent = reservation.getEvent();
                break;
            }
        }
        
        // Ακύρωση κράτησης αν βρεθεί η εκδήλωση
        if (cancelEvent != null) {
            visitor.cancelReservation(cancelEvent);
        } else {
            System.out.println("Δεν βρέθηκε κράτηση για την εκδήλωση: " + cancelEventTitle);
        
         }
        }
       
    }
    

}


