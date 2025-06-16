package bookjava.GUI.BookJava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import bookjava.GUI.DB.Mysql;

public class guiManagement {
	
	public static boolean bookRegister(String title, String author, String publisher) // 도서 반납 메소드
	{
		String sql = "INSERT INTO bookmanagement (title, author, publisher) "
				+ "VALUES ( ?, ?, ?)";

	    try (Connection conn = Mysql.getConnection();
	        PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, title);
	        pstmt.setString(2, author);
	        pstmt.setString(3, publisher);
	        
	        int affectedRows = pstmt.executeUpdate();
			return affectedRows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }

	}

	public static boolean loanReturn(String loanID) // 도서 반납 메소드
	{
		String sql = "UPDATE loanmanagement SET status = '반납', return_date = NOW() WHERE id = ?";

	    try (Connection conn = Mysql.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, loanID);
	        
	        int affectedRows = pstmt.executeUpdate();
			return affectedRows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }

	}

	public static boolean loanLoan(String bookID, String memberID) // 도서 대출 메소드
	{
		String sql = "INSERT INTO loanmanagement (id_book, id_member, loan_date, due_date, status) "
				+ "VALUES (?, ?, CURRENT_DATE, DATE_ADD(CURRENT_DATE, INTERVAL 14 DAY), '미납')";

		try (Connection conn = Mysql.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, bookID);
			pstmt.setString(2, memberID);

			int affectedRows = pstmt.executeUpdate();
			return affectedRows > 0;

		} catch (SQLException e) {
			System.err.println("대출 정보 추가 중 오류 발생");
			e.printStackTrace();
			return false;
		}

	}

	public static boolean memberRegister(List<String> memberInfo) // 회원 등록 메소드
	{
		String sql = "INSERT INTO membermanagement (name, phone, email, address) VALUES (?, ?, ?, ?)";

	    try (Connection conn = Mysql.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, memberInfo.get(0));
	        pstmt.setString(2, memberInfo.get(1));
	        pstmt.setString(3, memberInfo.get(2));
	        pstmt.setString(4, memberInfo.get(3));
	        
	        int affectedRows = pstmt.executeUpdate();
			return affectedRows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public static boolean memberEdit(String memberID, List<String> memberInfo) // 회원 정보 수정 메소드
	{
		String sql = "UPDATE membermanagement SET name = ?, phone = ?, email = ?, address = ? WHERE id = ?";

	    try (Connection conn = Mysql.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, memberInfo.get(0));
	        pstmt.setString(2, memberInfo.get(1));
	        pstmt.setString(3, memberInfo.get(2));
	        pstmt.setString(4, memberInfo.get(3));
	        pstmt.setString(5, memberID);

	        int affectedRows = pstmt.executeUpdate();
			return affectedRows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public static boolean memberDelete(String memberID) // 회원 정보 삭제 메소드
	{
		String sql = "DELETE FROM membermanagement WHERE id = ?";

	    try (Connection conn = Mysql.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, memberID);
	        pstmt.executeUpdate();

	        int affectedRows = pstmt.executeUpdate();
			return affectedRows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
