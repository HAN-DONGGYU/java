package bookjava.GUI.Book;

import javax.swing.*;

import bookjava.GUI.DB.DAO.BookDAO;
import bookjava.GUI.DB.DTO.BookDTO;

import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class BookListFrame extends JFrame implements BookManagement.ChangeListener {
    private DefaultListModel<String> listModel;
    private JList<String> bookJList;

    public BookListFrame() {
    	
        setTitle("도서 등록 및 수정");
        setSize(474, 503);
        getContentPane().setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        bookJList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(bookJList);

        JButton registerBtn = new JButton("등록");
        registerBtn.setFont(new Font("굴림", Font.BOLD, 20));
        registerBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		BookRegisterFrame brf = new BookRegisterFrame();
    				brf.setVisible(true);
        	}
        });
        JButton updateBtn = new JButton("수정");
        updateBtn.setFont(new Font("굴림", Font.BOLD, 20));
        updateBtn.addActionListener(e -> {
            int index = bookJList.getSelectedIndex();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "수정할 도서를 선택하세요.");
                return;
            }

            Book selectedBook = BookManagement.getBooks().get(index);
            new BookEditFrame(selectedBook);
        });
        JButton searchBtn = new JButton("검색");
        searchBtn.setFont(new Font("굴림", Font.BOLD, 20));
        searchBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "도서번호(BookNum)를 입력하세요:");
            if (input == null || input.trim().isEmpty()) {
                return;
            }

            List<Book> books = BookManagement.getBooks();
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getBookNum().equalsIgnoreCase(input.trim())) {
                    bookJList.setSelectedIndex(i);
                    bookJList.ensureIndexIsVisible(i);
                    JOptionPane.showMessageDialog(this, "도서가 선택되었습니다:\n" + books.get(i));
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "일치하는 도서번호가 없습니다.");
        });

        // 버튼 패널에 추가
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(registerBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(searchBtn);

        

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        BookManagement.addChangeListener(this); // 변경 감지 등록
        refreshList();

        setVisible(true);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	saveBooksToDatabase();
            }
        });

    }

    @Override
    public void onChange() {
        refreshList();
    }

    private void refreshList() {
        listModel.clear();
        List<Book> books = BookManagement.getBooks();
        for (Book book : books) {
            listModel.addElement(book.toString());
        }
    }
    
    private void saveBooksToDatabase() {
        BookDAO dao = new BookDAO();
        for (Book book : BookManagement.getBooks()) {
            BookDTO dto = new BookDTO();
            
            String bookname = book.getBookNum().substring(1);
            
            dto.setId(Integer.parseInt(bookname));
            dto.setTitle(book.getTitle());
            dto.setAuthor(book.getAuthor());
            dto.setPublisher(book.getPublisher());

            dao.saveOrUpdateBook(dto);
        }
        
        System.out.println("도서 저장 성공");
    }
}