import java.sql.*;

public class FinanceManager {
    private Connection connection;

    public FinanceManager(String dbUrl, String dbUser, String dbPassword) {
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            System.out.println("Connected to the database successfully.");
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }

    public void addUser(String name, double income) {
        String query = "INSERT INTO users (name, income) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setDouble(2, income);
            stmt.executeUpdate();
            System.out.println("User added successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to add user: " + e.getMessage());
        }
    }

    public boolean addExpense(int userId, String category, double amount) {
        try {
            double income = getIncome(userId);
            double totalExpenses = getTotalExpenses(userId);
    
            if (amount + totalExpenses > income) {
                System.out.println("Expense exceeds your income limit! Cannot add this expense.");
                return false;
            }
    
            // Check if a similar category already exists
            String checkQuery = "SELECT id, amount FROM expenses WHERE user_id = ? AND category = ?";
            try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, userId);
                checkStmt.setString(2, category);
    
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    // Update existing category
                    int expenseId = rs.getInt("id");
                    double existingAmount = rs.getDouble("amount");
    
                    String updateQuery = "UPDATE expenses SET amount = ? WHERE id = ?";
                    try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                        updateStmt.setDouble(1, existingAmount + amount);
                        updateStmt.setInt(2, expenseId);
                        updateStmt.executeUpdate();
                        System.out.println("Updated expense in category '" + category + "' with additional amount.");
                    }
                } else {
                    // Insert new expense
                    String insertQuery = "INSERT INTO expenses (user_id, category, amount, expense_date) VALUES (?, ?, ?, CURDATE())";
                    try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                        insertStmt.setInt(1, userId);
                        insertStmt.setString(2, category);
                        insertStmt.setDouble(3, amount);
                        insertStmt.executeUpdate();
                        System.out.println("Expense added successfully in a new category '" + category + "'.");
                    }
                }
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Failed to add expense: " + e.getMessage());
            return false;
        }
    }
    

    public void generateReport(int userId) {
        try {
            double income = getIncome(userId);
            double totalExpenses = getTotalExpenses(userId);
            double savingsGoal = getSavingsGoal(userId);
            double remainingBalance = income - totalExpenses;

            System.out.println("\n--- Expense Report ---");
            System.out.println("Income: " + income);
            System.out.println("Total Expenses: " + totalExpenses);
            System.out.println("Remaining Balance: " + remainingBalance);
            if (savingsGoal > 0) {
                System.out.println("Savings Goal: " + savingsGoal);
                if (remainingBalance >= savingsGoal) {
                    System.out.println("Congratulations! You are on track to meet your savings goal.");
                } else {
                    System.out.println("You need to save " + (savingsGoal - remainingBalance) + " more to meet your goal.");
                }
            }

            String query = "SELECT category, amount, expense_date FROM expenses WHERE user_id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();
                System.out.println("\nDetailed Expenses:");
                while (rs.next()) {
                    System.out.println(rs.getString("category") + ": " + rs.getDouble("amount") + " on " + rs.getDate("expense_date"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to generate report: " + e.getMessage());
        }
    }

    public void setSavingsGoal(int userId, double goal) {
        String query = "INSERT INTO savings (user_id, goal) VALUES (?, ?) ON DUPLICATE KEY UPDATE goal = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setDouble(2, goal);
            stmt.setDouble(3, goal);
            stmt.executeUpdate();
            System.out.println("Savings goal set successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to set savings goal: " + e.getMessage());
        }
    }

    private double getIncome(int userId) throws SQLException {
        String query = "SELECT income FROM users WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("income");
            } else {
                throw new SQLException("User not found.");
            }
        }
    }

    private double getTotalExpenses(int userId) throws SQLException {
        String query = "SELECT SUM(amount) AS total_expenses FROM expenses WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total_expenses");
            }
            return 0.0;
        }
    }

    private double getSavingsGoal(int userId) throws SQLException {
        String query = "SELECT goal FROM savings WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("goal");
            }
            return 0.0;
        }
    }
}
