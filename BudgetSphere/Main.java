import java.util.Scanner;

public class Main {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/finance_manager";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FinanceManager financeManager = new FinanceManager(DB_URL, DB_USER, DB_PASSWORD);

        while (true) {
            System.out.println("\n--- Personal Finance Manager ---");
            System.out.println("1. Add User");
            System.out.println("2. Add Expense");
            System.out.println("3. Generate Report");
            System.out.println("4. Set Savings Goal");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your monthly income: ");
                    double income = scanner.nextDouble();
                    financeManager.addUser(name, income);
                    break;

                case 2:
                    System.out.print("Enter your user ID: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    while (true) {
                        System.out.print("Enter expense category (e.g., Food, Transport): ");
                        String category = scanner.nextLine();
                        System.out.print("Enter amount: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        if (!financeManager.addExpense(userId, category, amount)) {
                            break; // Stop if expense exceeds income
                        }
                        System.out.print("Add another expense? (yes/no): ");
                        String continueAdding = scanner.nextLine();
                        if (!continueAdding.equalsIgnoreCase("yes")) {
                            break;
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter your user ID: ");
                    userId = scanner.nextInt();
                    scanner.nextLine();
                    financeManager.generateReport(userId);
                    break;

                case 4:
                    System.out.print("Enter your user ID: ");
                    userId = scanner.nextInt();
                    System.out.print("Enter your savings goal: ");
                    double goal = scanner.nextDouble();
                    financeManager.setSavingsGoal(userId, goal);
                    break;

                case 5:
                    System.out.println("Thank you for using the Personal Finance Manager!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
