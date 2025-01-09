package FeedbackForge;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HobbyOrganizer organizer = new HobbyOrganizer();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Hobby Organizer!");
        System.out.println("1. Add a Hobby");
        System.out.println("2. View Hobbies");
        System.out.println("3. Update Hobby Progress");
        System.out.println("4. Filter Hobbies");
        System.out.println("5. Analyze Hobby Progress");
        System.out.println("6. Exit");

        while (true) {
            System.out.print("\nChoose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Hobby Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Category (e.g., Music, Sports): ");
                    String category = scanner.nextLine();
                    System.out.print("Enter Skill Level (e.g., Beginner, Intermediate): ");
                    String skillLevel = scanner.nextLine();
                    organizer.addHobby(name, category, skillLevel);
                    break;

                case 2:
                    organizer.viewHobbies();
                    break;

                case 3:
                    System.out.print("Enter Hobby ID to Update: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter Hours Spent: ");
                    int hours = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Milestone (or leave blank): ");
                    String milestone = scanner.nextLine();
                    organizer.updateProgress(id, hours, milestone.isEmpty() ? null : milestone);
                    break;

                case 4:
                    System.out.print("Filter by (category/skill_level): ");
                    String filter = scanner.nextLine();
                    System.out.print("Enter Value: ");
                    String value = scanner.nextLine();
                    organizer.filterHobbies(filter, value);
                    break;

                case 5:
                    organizer.analyzeHobbies();
                    break;

                case 6:
                    System.out.println("Thank you for using the Hobby Organizer! Goodbye.");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
