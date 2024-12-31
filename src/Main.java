
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


    // Δημιουργία Εvent ?

    Event event1 = new Event("Concert - Coldplay", "Music", "Description for Coldplay", "Thessaloniki Concert Hall",
            500,8,3,2025,8,30,160,  org1, "Pending");

}
}


