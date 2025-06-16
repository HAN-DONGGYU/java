package bookjava.GUI.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Mysql {
	
	private static Connection conn;

	public static void main(String[] args) {
        Statement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bookjava?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8",
                "root", "rootroot");

            System.out.println("SQL 연결 성공");

            stmt = conn.createStatement();

            String createBookTable =
        	    "CREATE TABLE IF NOT EXISTS bookManagement (" +
        	    "id INT AUTO_INCREMENT PRIMARY KEY, " +
        	    "title VARCHAR(255), " +
        	    "author VARCHAR(255), " +
        	    "publisher VARCHAR(255), " +
        	    "publish_date DATE, " +
        	    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
        	    "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
        	    ");";

        	String createMemberTable =
        	    "CREATE TABLE IF NOT EXISTS memberManagement (" +
        	    "id INT AUTO_INCREMENT PRIMARY KEY, " +
        	    "name VARCHAR(255), " +
        	    "phone VARCHAR(255), " +
        	    "email VARCHAR(255), " +
        	    "address VARCHAR(255), " +
        	    "registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
        	    "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
        	    ");";

        	String createLoanTable =
        	    "CREATE TABLE IF NOT EXISTS loanManagement (" +
        	    "id INT AUTO_INCREMENT PRIMARY KEY, " +
        	    "id_book INT NOT NULL, " +
        	    "id_member INT NOT NULL, " +
        	    "loan_date DATE, " +
        	    "due_date DATE, " +
        	    "return_date TIMESTAMP, " +
        	    "status VARCHAR(50), " +
        	    "FOREIGN KEY (id_book) REFERENCES bookManagement(id), " +
        	    "FOREIGN KEY (id_member) REFERENCES memberManagement(id)" +
        	    ");";


            stmt.executeUpdate(createBookTable);
            stmt.executeUpdate(createMemberTable);
            stmt.executeUpdate(createLoanTable);
            System.out.println("테이블 생성 완료");

        } catch (ClassNotFoundException e) {
            System.err.println("드라이버를 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("SQL 오류 발생");
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	
	public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bookjava?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8",
                "root", "rootroot"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
