import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ClubDAO {
    private Connection conn;

    public ClubDAO(Connection conn) {
        this.conn = conn;
    }

    public void viewClubs() {
        String query = "SELECT * FROM Club";
        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            System.out.println("\nClubs:");
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s, Type: %s%n",
                        rs.getInt("ClubID"),
                        rs.getString("ClubName"),
                        rs.getString("Type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addClub(Scanner scanner) {
        System.out.print("Enter Club Name: ");
        scanner.nextLine(); // Consume newline
        String clubName = scanner.nextLine();
        System.out.print("Enter Club Type: ");
        String type = scanner.nextLine();

        String query = "INSERT INTO Club (ClubName, Type) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, clubName);
            pstmt.setString(2, type);
            pstmt.executeUpdate();
            System.out.println("Club added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateClub(Scanner scanner) {
        System.out.print("Enter Club ID to update: ");
        int clubId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter New Club Name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter New Club Type: ");
        String newType = scanner.nextLine();

        String query = "UPDATE Club SET ClubName = ?, Type = ? WHERE ClubID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, newName);
            pstmt.setString(2, newType);
            pstmt.setInt(3, clubId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Club updated successfully!");
            } else {
                System.out.println("Club not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClub(Scanner scanner) {
        System.out.print("Enter Club ID to delete: ");
        int clubId = scanner.nextInt();

        String query = "DELETE FROM Club WHERE ClubID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, clubId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Club deleted successfully!");
            } else {
                System.out.println("Club not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
