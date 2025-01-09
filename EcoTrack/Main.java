package EcoTrack;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WildlifeTracker tracker = new WildlifeTracker();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Wildlife Sighting Tracker!");
        System.out.println("1. Add a Sighting");
        System.out.println("2. View All Sightings");
        System.out.println("3. Filter Sightings by Animal or Location");
        System.out.println("4. Analyze Sightings");
        System.out.println("5. Exit");

        while (true) {
            System.out.print("\nChoose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Animal Name: ");
                    String animal = scanner.nextLine();
                    System.out.print("Enter Location: ");
                    String location = scanner.nextLine();
                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter Time (HH:MM:SS): ");
                    String time = scanner.nextLine();
                    tracker.addSighting(animal, location, date, time);
                    break;

                case 2:
                    tracker.viewSightings();
                    break;

                case 3:
                    System.out.print("Filter by (animal_name/location): ");
                    String filter = scanner.nextLine();
                    System.out.print("Enter value: ");
                    String value = scanner.nextLine();
                    tracker.filterSightings(filter, value);
                    break;

                case 4:
                    tracker.analyzeSightings();
                    break;

                case 5:
                    System.out.println("Thank you for using the Wildlife Sighting Tracker!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
