import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ClubManagementApp {
    private static final String DB_URL = "jdbc:mysql://192.168.56.101:4567/ClubManagement";
    private static final String DB_USER = "tkim"; // MySQL 사용자 이름
    private static final String DB_PASSWORD = "taehwa8508"; // MySQL 비밀번호

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Database connected successfully!");
            ClubDAO clubDAO = new ClubDAO(conn);
            MemberDAO memberDAO = new MemberDAO(conn);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\n1. View Clubs");
                System.out.println("2. Add Club");
                System.out.println("3. Update Club");
                System.out.println("4. Delete Club");
                System.out.println("5. View Members");
                System.out.println("6. Add Member");
                System.out.println("7. Update Member");
                System.out.println("8. Delete Member");
                System.out.println("99. Quit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> clubDAO.viewClubs();
                    case 2 -> clubDAO.addClub(scanner);
                    case 3 -> clubDAO.updateClub(scanner);
                    case 4 -> clubDAO.deleteClub(scanner);
                    case 5 -> memberDAO.viewMembers();
                    case 6 -> memberDAO.addMember(scanner);
                    case 7 -> memberDAO.updateMember(scanner);
                    case 8 -> memberDAO.deleteMember(scanner);
                    case 99 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
