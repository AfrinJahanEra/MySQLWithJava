package BookBuddy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        Book book = new Book();

        while (true) {
            System.out.println("1. Register User");
            System.out.println("2. Login");
            System.out.println("3. Add Book");
            System.out.println("4. Get Recommendations");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    user.registerUser(username, email, password);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    if (user.login(loginUsername, loginPassword)) {
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Invalid login.");
                    }
                    break;
                case 3:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter published year: ");
                    int year = scanner.nextInt();
                    book.addBook(title, author, category, year);
                    break;
                case 4:
                    System.out.print("Enter user ID for recommendations: ");
                    int userId = scanner.nextInt();
                    user.getRecommendations(userId);
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

