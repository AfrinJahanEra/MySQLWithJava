package BookBuddy;

import java.sql.*;

public class Book {

    // Add a new book
    public void addBook(String title, String author, String category, int publishedYear) {
        String query = "INSERT INTO books (title, author, category, published_year) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, category);
            stmt.setInt(4, publishedYear);
            stmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to add book: " + e.getMessage());
        }
    }

    // Track user interactions with books (e.g., read, rated)
    public void trackInteraction(int userId, int bookId, String interactionType) {
        String query = "INSERT INTO user_interactions (user_id, book_id, interaction_type) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, bookId);
            stmt.setString(3, interactionType);
            stmt.executeUpdate();
            System.out.println("Interaction recorded successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to track interaction: " + e.getMessage());
        }
    }
}
