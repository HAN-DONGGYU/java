package bookjava.GUI.DB.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bookjava.GUI.DB.Mysql;
import bookjava.GUI.DB.DTO.LoanDTO;

public class LoanDAO extends Mysql {

    Connection conn = getConnection();

    public List<LoanDTO> getLoans() {
        List<LoanDTO> loanList = new ArrayList<>();
        String sql = "SELECT * FROM loanmanagement";

        try (Connection conn = Mysql.getConnection();
        		PreparedStatement pstmt = conn.prepareStatement(sql);
        		ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                LoanDTO loan = new LoanDTO();
                loan.setId(rs.getInt("id"));
                loan.setIdBook(rs.getInt("id_book"));
                loan.setIdMember(rs.getInt("id_member"));
                loan.setLoanDate(rs.getDate("loan_date").toLocalDate());
                loan.setDueDate(rs.getDate("due_date").toLocalDate());
                Timestamp returnTs = rs.getTimestamp("return_date");
                loan.setReturnDate(returnTs != null ? returnTs.toLocalDateTime() : null);
                loan.setStatus(rs.getString("status"));
                loanList.add(loan);
            }

        } catch (SQLException e) {
            System.err.println("대출 정보 조회 중 오류 발생");
            e.printStackTrace();
        }

        return loanList;
    }
}
