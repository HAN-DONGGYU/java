package bookjava.GUI.DB.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookjava.GUI.DB.Mysql;
import bookjava.GUI.DB.DTO.LoanInfoDTO;

public class LoanInfoDAO extends Mysql{
	
	public List<LoanInfoDTO> getLoanInfoWithBookTitle() {
	    List<LoanInfoDTO> list = new ArrayList<>();
	    String sql = "SELECT " +
	             "    l.id AS loan_id, " +
	             "    l.id_book AS book_id, " +
	             "    b.title AS title, " +
	             "    l.due_date, " +
	             "    l.id_member AS member_id, " +
	             "    m.name AS member_name " +
	             "FROM " +
	             "    loanmanagement l " +
	             "JOIN " +
	             "    bookmanagement b ON l.id_book = b.id " +
	             "JOIN " +
	             "    membermanagement m ON l.id_member = m.id " +
	             "WHERE " +
	             "    l.status = '미납'";



	    try (Connection conn = Mysql.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            ResultSet rs = pstmt.executeQuery()) {

	        while (rs.next()) {
	            LoanInfoDTO dto = new LoanInfoDTO();
	            dto.setLoanId(rs.getInt("loan_id"));
	            dto.setBookId(rs.getInt("book_id"));
	            dto.setTitle(rs.getString("title"));
	            dto.setDueDate(rs.getDate("due_date").toLocalDate());
	            dto.setMemberId(
	            		rs.getString("member_name") + 
	            		"(" + rs.getString("member_id") + ")");
	            list.add(dto);
	        }

	    } catch (SQLException e) {
	        System.err.println("대출 정보 + 책 제목 조회 중 오류 발생");
	        e.printStackTrace();
	    }

	    return list;
	}

}
