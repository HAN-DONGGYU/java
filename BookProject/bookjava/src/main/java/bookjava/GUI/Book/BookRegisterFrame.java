package bookjava.GUI.Book;


import javax.swing.*;

import bookjava.GUI.BookJava.guiManagement;
import bookjava.GUI.DB.DAO.BookDAO;
import bookjava.GUI.DB.DTO.BookDTO;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class BookRegisterFrame extends JFrame {
    private JTextField titleField, authorField, pubField;

    public BookRegisterFrame() {
        setTitle("도서 등록");
        setSize(400, 250);
        setLayout(new GridLayout(5, 2, 10, 5));

        titleField = new JTextField();
        authorField = new JTextField();
        pubField = new JTextField();
      
        JButton registerButton = new JButton("등록");

        add(new JLabel("제목"));
        add(titleField);
        add(new JLabel("저자"));
        add(authorField);
        add(new JLabel("출판사"));
        add(pubField);
        
        add(new JLabel("도서 등록"));
        add(registerButton);

        registerButton.addActionListener(e -> {
        	guiManagement.bookRegister(
        			titleField.getText(), 
        			authorField.getText(), 
        			pubField.getText());
            clearFields();
        });

        setVisible(true);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	BookManagement.clearBooks();
		    	List<BookDTO> booksFromDB = new BookDAO().getBooks();
		    	for (BookDTO dto : booksFromDB) {
		    	    Book book = new Book(dto.getId(), dto.getTitle(), dto.getAuthor(), dto.getPublisher());
		    	    BookManagement.addBook(book);
		    	}
            }
        });
    }

    private void clearFields() {
        titleField.setText("");
        authorField.setText("");
        pubField.setText("");
     
    }
}

