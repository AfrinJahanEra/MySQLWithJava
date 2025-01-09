// import java.sql.*;
// import java.util.Scanner;

// public class PersonalFinanceManager {
//     private static final String DB_URL = "jdbc:mysql://localhost:3306/finance_manager";
//     private static final String DB_USER = "root";
//     private static final String DB_PASSWORD = "123";

//     public static void main(String[] args) {
//         try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
//             Scanner scanner = new Scanner(System.in);
//             System.out.println("Welcome to the Personal Finance Manager!");

//             while (true) {
//                 System.out.println("\nMenu:");
//                 System.out.println("1. Add User");
//                 System.out.println("2. Add Expense");
//                 System.out.println("3. Generate Report");
//                 System.out.println("4. Set Savings Goal");
//                 System.out.println("5. Visualize Income vs. Expenses");
//                 System.out.println("6. Exit");
//                 System.out.print("Choose an option: ");

//                 int choice = scanner.nextInt();
//                 scanner.nextLine(); // Consume newline

//                 switch (choice) {
//                     case 1 -> addUser(conn, scanner);
//                     case 2 -> addExpense(conn, scanner);
//                     case 3 -> generateReport(conn, scanner);
//                     case 4 -> setSavingsGoal(conn, scanner);
//                     case 5 -> visualizeIncomeVsExpenses(conn, scanner);
//                     case 6 -> {
//                         System.out.println("Thank you for using the Personal Finance Manager!");
//                         return;
//                     }
//                     default -> System.out.println("Invalid choice. Please try again.");
//                 }
//             }
//         } catch (SQLException e) {
//             System.err.println("Database connection failed: " + e.getMessage());
//         }
//     }

//     private static void addUser(Connection conn, Scanner scanner) {
//         System.out.print("Enter your name: ");
//         String name = scanner.nextLine();
//         System.out.print("Enter your monthly income: ");
//         double income = scanner.nextDouble();

//         String query = "INSERT INTO users (name, income) VALUES (?, ?)";
//         try (PreparedStatement stmt = conn.prepareStatement(query)) {
//             stmt.setString(1, name);
//             stmt.setDouble(2, income);
//             stmt.executeUpdate();
//             System.out.println("User added successfully.");
//         } catch (SQLException e) {
//             System.err.println("Failed to add user: " + e.getMessage());
//         }
//     }

//     private static void addExpense(Connection conn, Scanner scanner) {
//         System.out.print("Enter your user ID: ");
//         int userId = scanner.nextInt();
//         scanner.nextLine(); // Consume newline
//         System.out.print("Enter expense category (e.g., Food, Transport): ");
//         String category = scanner.nextLine();
//         System.out.print("Enter amount: ");
//         double amount = scanner.nextDouble();
//         scanner.nextLine(); // Consume newline
//         System.out.print("Enter expense date (YYYY-MM-DD): ");
//         String date = scanner.nextLine();

//         String query = "INSERT INTO expenses (user_id, category, amount, expense_date) VALUES (?, ?, ?, ?)";
//         try (PreparedStatement stmt = conn.prepareStatement(query)) {
//             stmt.setInt(1, userId);
//             stmt.setString(2, category);
//             stmt.setDouble(3, amount);
//             stmt.setDate(4, Date.valueOf(date));
//             stmt.executeUpdate();
//             System.out.println("Expense added successfully.");
//         } catch (SQLException e) {
//             System.err.println("Failed to add expense: " + e.getMessage());
//         }
//     }

//     private static void generateReport(Connection conn, Scanner scanner) {
//         System.out.print("Enter your user ID: ");
//         int userId = scanner.nextInt();
//         scanner.nextLine(); // Consume newline
//         System.out.print("Enter report type (monthly/yearly): ");
//         String reportType = scanner.nextLine();

//         String query = reportType.equalsIgnoreCase("monthly") 
//             ? "SELECT category, SUM(amount) AS total FROM expenses WHERE user_id = ? AND MONTH(expense_date) = MONTH(CURRENT_DATE) GROUP BY category" 
//             : "SELECT category, SUM(amount) AS total FROM expenses WHERE user_id = ? AND YEAR(expense_date) = YEAR(CURRENT_DATE) GROUP BY category";

//         try (PreparedStatement stmt = conn.prepareStatement(query)) {
//             stmt.setInt(1, userId);
//             ResultSet rs = stmt.executeQuery();
//             System.out.println("Expense Report:");
//             while (rs.next()) {
//                 System.out.println(rs.getString("category") + ": " + rs.getDouble("total"));
//             }
//         } catch (SQLException e) {
//             System.err.println("Failed to generate report: " + e.getMessage());
//         }
//     }

//     private static void setSavingsGoal(Connection conn, Scanner scanner) {
//         System.out.print("Enter your user ID: ");
//         int userId = scanner.nextInt();
//         System.out.print("Enter your savings goal: ");
//         double goal = scanner.nextDouble();

//         String query = "INSERT INTO savings (user_id, goal) VALUES (?, ?) ON DUPLICATE KEY UPDATE goal = ?";
//         try (PreparedStatement stmt = conn.prepareStatement(query)) {
//             stmt.setInt(1, userId);
//             stmt.setDouble(2, goal);
//             stmt.setDouble(3, goal);
//             stmt.executeUpdate();
//             System.out.println("Savings goal set successfully.");
//         } catch (SQLException e) {
//             System.err.println("Failed to set savings goal: " + e.getMessage());
//         }
//     }

//     private static void visualizeIncomeVsExpenses(Connection conn, Scanner scanner) {
//         System.out.print("Enter your user ID: ");
//         int userId = scanner.nextInt();

//         String incomeQuery = "SELECT income FROM users WHERE id = ?";
//         String expenseQuery = "SELECT SUM(amount) AS total_expenses FROM expenses WHERE user_id = ?";

//         try (PreparedStatement incomeStmt = conn.prepareStatement(incomeQuery);
//              PreparedStatement expenseStmt = conn.prepareStatement(expenseQuery)) {

//             incomeStmt.setInt(1, userId);
//             expenseStmt.setInt(1, userId);

//             ResultSet incomeRs = incomeStmt.executeQuery();
//             ResultSet expenseRs = expenseStmt.executeQuery();

//             if (incomeRs.next() && expenseRs.next()) {
//                 double income = incomeRs.getDouble("income");
//                 double totalExpenses = expenseRs.getDouble("total_expenses");

//                 System.out.println("Income: " + income);
//                 System.out.println("Expenses: " + totalExpenses);

//                 if (income < totalExpenses) {
//                     System.out.println("Alert: You are overspending!");
//                 }
//             }
//         } catch (SQLException e) {
//             System.err.println("Failed to visualize income vs. expenses: " + e.getMessage());
//         }
//     }
// }
 
