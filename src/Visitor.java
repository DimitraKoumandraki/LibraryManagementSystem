import java.util.ArrayList;
import java.util.List;

public class Visitor {

        private String name;
        private String surname;
        private String email;

        public Visitor(String name, String surname, String email) {
            this.name = name;
            this.surname = surname;
            this.email = email;
        }

        // Getters and Setters
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


//Πραγματοποιεί αναζήτηση στη δεδομένη λίστα με βάση: θέμα, τοποθεσία ή τίτλος.
//Εάν ένα κριτήριο είναι άκυρο, θα αγνοηθεί στην αναζήτηση.
//Επιστρέφει μια λίστα που ταιριάζει με τα κριτήρια.

    public static List<Event> searchByCriteria(List<Event> events, Integer day, Integer month, Integer year, String location, String theme) {
        List<Event> result = new ArrayList<>();
        for (Event event : events) {
            boolean matchesDate = true;

            // Αν το day, month, ή year είναι null, δεν γίνεται έλεγχος γι' αυτά
            if (day != null && event.getDay() != day) {
                matchesDate = false;
            }
            if (month != null && event.getMonth() != month) {
                matchesDate = false;
            }
            if (year != null && event.getYear() != year) {
                matchesDate = false;
            }

            // Αν η ημερομηνία ταιριάζει ή είναι null, προχωράμε με τα υπόλοιπα κριτήρια
            if (matchesDate &&
                    (location == null || event.getLocation().equalsIgnoreCase(location)) &&
                    (theme == null || event.getTheme().equalsIgnoreCase(theme))) {
                result.add(event);
            }
        }
        return result;
    }

}
