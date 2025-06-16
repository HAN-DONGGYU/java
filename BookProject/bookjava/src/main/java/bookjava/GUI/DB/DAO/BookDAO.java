package bookjava.GUI.DB.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import bookjava.GUI.DB.Mysql;
import bookjava.GUI.DB.DTO.BookDTO;

public class BookDAO extends Mysql {

    static Connection conn = getConnection();

    public List<BookDTO> getBooks() {
        List<BookDTO> bookList = new ArrayList<>();
        String sql = "SELECT * FROM bookmanagement";

        try (Connection conn = Mysql.getConnection();
        	PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                BookDTO book = new BookDTO();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublishDate(rs.getObject("publish_date", LocalDate.class));
                book.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
                book.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));
                bookList.add(book);
            }

        } catch (SQLException e) {
            System.err.println("도서 목록 조회 중 오류 발생");
            e.printStackTrace();
        }

        return bookList;
    }
    
    public void saveOrUpdateBook(BookDTO book) {
        try (Connection conn = Mysql.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("UPDATE bookManagement SET title=?, author=?, publisher=?, updated_at=NOW() WHERE id=?")) {

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getPublisher());
            pstmt.setInt(4, book.getId());	

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
            	try (PreparedStatement pstmt1 = conn.prepareStatement("INSERT INTO bookManagement (title, author, publisher, publish_date) VALUES (?, ?, ?, CURDATE())")) {

                       pstmt1.setString(1, book.getTitle());
                       pstmt1.setString(2, book.getAuthor());
                       pstmt1.setString(3, book.getPublisher());

                       pstmt1.executeUpdate();
            	} catch (Exception e) {
            		System.err.println("도서 생성 중 오류 발생");
                    e.printStackTrace();
				}
            } 

        } catch (SQLException e) {
            System.err.println("도서 저장 중 오류 발생");
            e.printStackTrace();
        }
    }
}
