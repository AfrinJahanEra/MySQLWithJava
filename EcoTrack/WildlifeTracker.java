package EcoTrack;

import java.sql.*;
import java.util.Scanner;

public class WildlifeTracker {

    public void addSighting(String animalName, String location, String date, String time) {
        String query = "INSERT INTO sightings (animal_name, location, sighting_date, sighting_time) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, animalName);
            stmt.setString(2, location);
            stmt.setDate(3, Date.valueOf(date));
            stmt.setTime(4, Time.valueOf(time));
            stmt.executeUpdate();
            System.out.println("Sighting added successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to add sighting: " + e.getMessage());
        }
    }

    public void viewSightings() {
        String query = "SELECT * FROM sightings";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Wildlife Sightings:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Animal: " + rs.getString("animal_name") +
                        ", Location: " + rs.getString("location") +
                        ", Date: " + rs.getDate("sighting_date") +
                        ", Time: " + rs.getTime("sighting_time"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch sightings: " + e.getMessage());
        }
    }

    public void filterSightings(String filter, String value) {
        String query = "SELECT * FROM sightings WHERE " + filter + " = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, value);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Filtered Sightings:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Animal: " + rs.getString("animal_name") +
                        ", Location: " + rs.getString("location") +
                        ", Date: " + rs.getDate("sighting_date") +
                        ", Time: " + rs.getTime("sighting_time"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to filter sightings: " + e.getMessage());
        }
    }

    public void analyzeSightings() {
        String query = "SELECT animal_name, COUNT(*) AS sightings_count FROM sightings GROUP BY animal_name";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Sightings Analysis:");
            while (rs.next()) {
                System.out.println("Animal: " + rs.getString("animal_name") + ", Sightings: " + rs.getInt("sightings_count"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to analyze sightings: " + e.getMessage());
        }
    }
}
