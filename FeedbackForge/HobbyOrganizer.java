package FeedbackForge;

import java.sql.*;
import java.util.Scanner;

public class HobbyOrganizer {

    public void addHobby(String name, String category, String skillLevel) {
        String query = "INSERT INTO hobbies (hobby_name, category, skill_level) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, category);
            stmt.setString(3, skillLevel);
            stmt.executeUpdate();
            System.out.println("Hobby added successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to add hobby: " + e.getMessage());
        }
    }

    public void viewHobbies() {
        String query = "SELECT * FROM hobbies";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Hobby List:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("hobby_name") +
                        ", Category: " + rs.getString("category") +
                        ", Skill Level: " + rs.getString("skill_level") +
                        ", Hours Spent: " + rs.getInt("hours_spent") +
                        ", Milestones: " + rs.getString("milestones"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch hobbies: " + e.getMessage());
        }
    }

    public void updateProgress(int id, int hours, String milestone) {
        String query = "UPDATE hobbies SET hours_spent = hours_spent + ?, milestones = CONCAT(IFNULL(milestones, ''), ?) WHERE id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, hours);
            stmt.setString(2, milestone != null ? "\n" + milestone : "");
            stmt.setInt(3, id);
            stmt.executeUpdate();
            System.out.println("Progress updated successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to update progress: " + e.getMessage());
        }
    }

    public void filterHobbies(String filter, String value) {
        String query = "SELECT * FROM hobbies WHERE " + filter + " = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, value);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Filtered Hobbies:");
            while (rs.next()) {
                System.out.println("Name: " + rs.getString("hobby_name") +
                        ", Category: " + rs.getString("category") +
                        ", Skill Level: " + rs.getString("skill_level"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to filter hobbies: " + e.getMessage());
        }
    }

    public void analyzeHobbies() {
        String query = "SELECT hobby_name, hours_spent FROM hobbies";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Hobby Progress Analysis:");
            while (rs.next()) {
                System.out.println("Hobby: " + rs.getString("hobby_name") +
                        ", Hours Spent: " + rs.getInt("hours_spent"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to analyze hobbies: " + e.getMessage());
        }
    }
}
