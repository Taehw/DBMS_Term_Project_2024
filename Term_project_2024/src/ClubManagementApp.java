import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ClubManagementApp {
    public static void main(String[] args) {
        Connection conn = null;

        try {
            // 데이터베이스 연결
            conn = DBconnection.getConnection();
            System.out.println("=======================================");
            System.out.println("   Welcome to the Club Management App   ");
            System.out.println("=======================================");
            ClubDAO clubDAO = new ClubDAO(conn);
            MemberDAO memberDAO = new MemberDAO(conn);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                displayMenu(); // 메뉴 출력
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
                        System.out.println("\nExiting... Thank you for using the app!");
                        return;
                    }
                    default -> System.out.println("\nInvalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while connecting to the database.");
            e.printStackTrace();
        } finally {
            // 데이터베이스 연결 종료
            DBconnection.closeConnection(conn);
        }
    }

    // 메뉴 출력 메서드
    private static void displayMenu() {
        System.out.println("\n=======================================");
        System.out.println("              Main Menu                ");
        System.out.println("=======================================");
        System.out.println("1. View Clubs");
        System.out.println("2. Add Club");
        System.out.println("3. Update Club");
        System.out.println("4. Delete Club");
        System.out.println("5. View Members");
        System.out.println("6. Add Member");
        System.out.println("7. Update Member");
        System.out.println("8. Delete Member");
        System.out.println("99. Quit");
        System.out.println("=======================================");
    }
}
