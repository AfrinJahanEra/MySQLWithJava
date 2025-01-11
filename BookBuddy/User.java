package BookBuddy;

import java.sql.*;

public class User {

    // Register a new user
    public void registerUser(String username, String email, String password) {
        String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.executeUpdate();
            System.out.println("User registered successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to register user: " + e.getMessage());
        }
    }

    // Log in a user (check if the username and password match)
    public boolean login(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // If result exists, login successful
        } catch (SQLException e) {
            System.out.println("Failed to log in: " + e.getMessage());
            return false;
        }
    }

    // View book recommendations based on ratings
    public void getRecommendations(int userId) {
        String query = "SELECT b.title, AVG(r.rating) AS avg_rating " +
                       "FROM books b " +
                       "JOIN ratings r ON b.book_id = r.book_id " +
                       "WHERE r.user_id != ? " +  // Filter out books rated by the user
                       "GROUP BY b.book_id " +
                       "ORDER BY avg_rating DESC LIMIT 10";  // Top 10 recommended books
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Recommended Books:");
            while (rs.next()) {
                System.out.println(rs.getString("title") + " - Average Rating: " + rs.getDouble("avg_rating"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to get recommendations: " + e.getMessage());
        }
    }
}
