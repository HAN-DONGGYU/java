package bookjava.GUI.Book;

import javax.swing.*;
import java.awt.*;

public class BookEditFrame extends JFrame {
    private JTextField BookNum, titleField, authorField, pubField;
    private Book targetBook;

    public BookEditFrame(Book bookToEdit) {
        this.targetBook = bookToEdit;

        setTitle("도서 수정");
        setSize(400, 250);
        setLayout(new GridLayout(5, 2, 10, 5));
        BookNum = new JTextField(bookToEdit.getBookNum());
        titleField = new JTextField(bookToEdit.getTitle());
        authorField = new JTextField(bookToEdit.getAuthor());
        pubField = new JTextField(bookToEdit.getPublisher());
        
        JButton saveButton = new JButton("수정 완료");
        add(new JLabel("Book ID"));
        add(BookNum);
        add(new JLabel("제목"));
        add(titleField);
        add(new JLabel("저자"));
        add(authorField);
        add(new JLabel("출판사"));
        add(pubField);
        
        add(new JLabel("")); // 빈 칸
        add(saveButton);

        saveButton.addActionListener(e -> {
        	targetBook.setTitle(BookNum.getText());
            targetBook.setTitle(titleField.getText());
            targetBook.setAuthor(authorField.getText());
            targetBook.setPublisher(pubField.getText());
           
            BookManagement.notifyListeners();

            JOptionPane.showMessageDialog(this, "도서 정보가 수정되었습니다!");
            dispose();
        });

        setVisible(true);
    }
}
