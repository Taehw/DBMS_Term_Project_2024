import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private static final String DB_URL = "jdbc:mysql://192.168.56.101:4567/ClubManagement";
    private static final String DB_USER = "tkim"; // MySQL 사용자 이름
    private static final String DB_PASSWORD = "taehwa8508"; // MySQL 비밀번호

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
