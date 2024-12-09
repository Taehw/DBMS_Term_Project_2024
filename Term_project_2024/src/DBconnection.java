import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    public static Connection connect() {
        // JDBC URL, 사용자명, 비밀번호 설정
        String url = "jdbc:mysql://192.168.56.101:4567/madang"; // CentOS IP 주소
        String user = "tkim"; // MySQL 사용자
        String password = "taehwa8508"; // MySQL 비밀번호

        try {
            // JDBC 드라이버 로드 및 Connection 객체 생성
            Class.forName("com.mysql.cj.jdbc.Driver"); // 드라이버 로드
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful!");
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            return null;
        }
    }
}
