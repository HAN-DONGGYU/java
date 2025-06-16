package bookjava.GUI.DB.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookjava.GUI.DB.Mysql;
import bookjava.GUI.DB.DTO.MemberDTO;

public class MemberDAO extends Mysql {

    Connection conn = getConnection();

    public List<MemberDTO> getMembers() {
        List<MemberDTO> memberList = new ArrayList<>();
        String sql = "SELECT * FROM membermanagement";

        try (Connection conn = Mysql.getConnection();
        		PreparedStatement pstmt = conn.prepareStatement(sql);
        		ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                MemberDTO member = new MemberDTO();
                member.setId(rs.getInt("id"));
                member.setName(rs.getString("name"));
                member.setPhone(rs.getString("phone"));
                member.setEmail(rs.getString("email"));
                member.setAddress(rs.getString("address"));
                member.setRegisteredAt(rs.getTimestamp("registered_at").toLocalDateTime());
                member.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                memberList.add(member);
            }

        } catch (SQLException e) {
            System.err.println("회원 목록 조회 중 오류 발생");
            e.printStackTrace();
        }

        return memberList;
    }
}

