import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MemberDAO {
    private Connection conn;

    public MemberDAO(Connection conn) {
        this.conn = conn;
    }

    public void viewMembers() {
        String query = "SELECT * FROM Member";
        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            System.out.println("\nMembers:");
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s, ClubID: %d%n",
                        rs.getInt("MemberID"),
                        rs.getString("Name"),
                        rs.getInt("ClubID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMember(Scanner scanner) {
        System.out.print("Enter Member Name: ");
        scanner.nextLine(); // Consume newline
        String memberName = scanner.nextLine();
        System.out.print("Enter Club ID: ");
        int clubId = scanner.nextInt();

        String query = "INSERT INTO Member (Name, ClubID) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, memberName);
            pstmt.setInt(2, clubId);
            pstmt.executeUpdate();
            System.out.println("Member added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMember(Scanner scanner) {
        System.out.print("Enter Member ID to update: ");
        int memberId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter New Member Name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter New Club ID: ");
        int newClubId = scanner.nextInt();

        String query = "UPDATE Member SET Name = ?, ClubID = ? WHERE MemberID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, newClubId);
            pstmt.setInt(3, memberId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Member updated successfully!");
            } else {
                System.out.println("Member not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMember(Scanner scanner) {
        System.out.print("Enter Member ID to delete: ");
        int memberId = scanner.nextInt();

        String query = "DELETE FROM Member WHERE MemberID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, memberId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Member deleted successfully!");
            } else {
                System.out.println("Member not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
