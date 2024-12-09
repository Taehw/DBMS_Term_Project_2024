import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 데이터베이스 연결
        Connection connection = DBconnection.connect();
        if (connection == null) {
            System.out.println("Database connection failed. Exiting.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSelect an operation:");
            System.out.println("1. Insert Data");
            System.out.println("2. Delete Data");
            System.out.println("3. Search Data");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            switch (choice) {
                case 1:
                    insertData(connection, scanner);
                    break;
                case 2:
                    deleteData(connection, scanner);
                    break;
                case 3:
                    searchData(connection, scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    try {
                        connection.close(); // Connection 객체 닫기
                    } catch (SQLException e) {
                        System.out.println("Error closing connection: " + e.getMessage());
                    }
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void insertData(Connection connection, Scanner scanner) {
        System.out.print("Enter SQL INSERT statement: ");
        String query = scanner.nextLine();

        try (Statement stmt = connection.createStatement()) {
            // INSERT 실행
            int rowsAffected = stmt.executeUpdate(query);
            System.out.println(rowsAffected + " row(s) inserted.");
        } catch (SQLException e) {
            System.out.println("Error executing INSERT: " + e.getMessage());
        }
    }

    private static void deleteData(Connection connection, Scanner scanner) {
        System.out.print("Enter SQL DELETE statement: ");
        String query = scanner.nextLine();

        try (Statement stmt = connection.createStatement()) {
            // DELETE 실행
            int rowsAffected = stmt.executeUpdate(query);
            System.out.println(rowsAffected + " row(s) deleted.");
        } catch (SQLException e) {
            System.out.println("Error executing DELETE: " + e.getMessage());
        }
    }

    private static void searchData(Connection connection, Scanner scanner) {
        System.out.print("Enter SQL SELECT statement: ");
        String query = scanner.nextLine();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            // SELECT 실행 및 결과 출력
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getMetaData().getColumnName(i) + ": " + rs.getString(i) + " ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Error executing SELECT: " + e.getMessage());
        }
    }
}
