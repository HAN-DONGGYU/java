package bookjava.GUI.BookJava;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bookjava.GUI.Book.Book;
import bookjava.GUI.Book.BookListFrame;
import bookjava.GUI.Book.BookManagement;
import bookjava.GUI.DB.Mysql;
import bookjava.GUI.DB.DAO.BookDAO;
import bookjava.GUI.DB.DTO.BookDTO;
import bookjava.GUI.Loan.guiLoan;
import bookjava.GUI.Loan.guiMain;
import bookjava.GUI.Member.guiMain1;

import javax.swing.JLabel;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class BBB extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BBB frame = new BBB();
					frame.setVisible(true);
					
					Mysql.main(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BBB() {
		setTitle("도서 관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("도서 관리 프로그램");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 45));
		lblNewLabel.setBounds(150, 30, 600, 100);
		contentPane.add(lblNewLabel);
		
		JButton btn1 = new JButton("도서 등록 및 수정");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookListFrame blf = new BookListFrame();
				blf.setVisible(true);
				
				BookManagement.clearBooks();
		    	List<BookDTO> booksFromDB = new BookDAO().getBooks();
		    	for (BookDTO dto : booksFromDB) {
		    	    Book book = new Book(dto.getId(), dto.getTitle(), dto.getAuthor(), dto.getPublisher());
		    	    BookManagement.addBook(book);
		    	}
		    	
				}
		});
		btn1.setFont(new Font("굴림", Font.BOLD, 31));
		btn1.setBounds(150, 175, 400, 69);
		contentPane.add(btn1);
		
		JButton btn2 = new JButton("도서 대출 관리");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiMain gL = new guiMain();
				gL.setVisible(true);
			}
		});
		btn2.setFont(new Font("굴림", Font.BOLD, 31));
		btn2.setBounds(150, 280, 400, 69);
		contentPane.add(btn2);
		
		JButton btn3 = new JButton("회원 등록 및 관리");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiMain1 gM = new guiMain1();
				gM.setVisible(true);
				
			}
		});
		btn3.setFont(new Font("굴림", Font.BOLD, 31));
		btn3.setBounds(150, 385, 400, 69);
		contentPane.add(btn3);
	}
}
