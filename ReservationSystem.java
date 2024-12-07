import java.util.*;

public class ReservationSystem {

    static Map<Integer, String> reservations = new HashMap<>();
    static Map<String, String> users = new HashMap<>();
    static int pnrCounter = 1000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        users.put("admin", "admin123"); // Predefined users
        users.put("user", "user123");

        // Login
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        if (!login(username, password)) {
            System.out.println("Invalid Login. Exiting...");
            return;
        }

        System.out.println("Login successful!");
        while (true) {
            System.out.println("\n1. Make Reservation\n2. Cancel Reservation\n3. View Reservations\n4. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    makeReservation(sc);
                    break;
                case 2:
                    cancelReservation(sc);
                    break;
                case 3:
                    viewReservations();
                    break;
                case 4:
                    System.out.println("Thank you for using the system!");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    static boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    static void makeReservation(Scanner sc) {
        System.out.print("Enter your Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Train Number: ");
        String trainNo = sc.nextLine();
        System.out.print("Enter Class Type (AC/Sleeper): ");
        String classType = sc.nextLine();
        System.out.print("Enter From Location: ");
        String from = sc.nextLine();
        System.out.print("Enter To Location: ");
        String to = sc.nextLine();

        int pnr = pnrCounter++;
        String reservationDetails = "PNR: " + pnr + ", Name: " + name + ", Train: " + trainNo +
                                    ", Class: " + classType + ", From: " + from + ", To: " + to;
        reservations.put(pnr, reservationDetails);
        System.out.println("Reservation Successful! Your PNR is " + pnr);
    }

    static void cancelReservation(Scanner sc) {
        System.out.print("Enter PNR to Cancel: ");
        int pnr = Integer.parseInt(sc.nextLine());
        if (reservations.containsKey(pnr)) {
            System.out.println("Cancelled Reservation: " + reservations.remove(pnr));
        } else {
            System.out.println("PNR not found.");
        }
    }

    static void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations available.");
        } else {
            System.out.println("All Reservations:");
            reservations.values().forEach(System.out::println);
        }
    }
}
