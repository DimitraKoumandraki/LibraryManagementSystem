
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


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


    // Δημιουργία 2 Εvent για κάθε Organizer
    // Δημιουργία νέας εκδήλωσης για τους πρώτους 2 διοργανωτές
    Event event1 = new Event("Concert - Coldplay", "Music", "Description for Coldplay", "Thessaloniki Concert Hall",
            500,8,3,2025,8,30,160,  org1, "Pending");
    
    Event event2 = new Event("Art Exhibition", "Art", "Modern Art Showcase",
            "Thessaloniki Art Center", 200, 15, 5, 2025, 10, 0, 120, org1, "Approved");
   

    Event event3 = new Event("Jazz Festival", "Music", "Smooth Jazz Night", "Thessaloniki Concert Hall",
            300, 10, 6, 2025, 19, 30, 180, org2, "Pending");
    
    Event event4 = new Event("Theater Play", "Theater", "A classical play by local actors",
            "Thessaloniki Concert Hall", 150, 12, 6, 2025, 18, 0, 120, org2, "Approved");
  
    Event event5 = new Event("Photography Workshop", "Art", "Learn the basics of photography",
            "Thessaloniki Art Center", 100, 5, 7, 2025, 9, 0, 180, org3, "Pending");
    
    Event event6 = new Event("Cooking Class", "Culinary", "Learn to cook gourmet meals",
            "Makedonia Palace", 50, 8, 7, 2025, 17, 0, 120, org3, "Approved");
 
    
    Event event7 = new Event("Book Fair", "Literature", "Explore new and classic books",
            "Thessaloniki Concert Hall", 400, 20, 8, 2025, 10, 0, 240, org4, "Pending");
    
    Event event8 = new Event("Music Festival", "Music", "A festival for all music lovers",
            "Thessaloniki Concert Hall", 500, 22, 8, 2025, 18, 0, 300, org4, "Approved");

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
    

   
    
  
    

    
    
    
    

    

  

 

    


    
    
    
    

}
}


