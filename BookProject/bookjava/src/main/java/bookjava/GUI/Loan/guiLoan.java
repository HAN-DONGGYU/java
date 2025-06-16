package bookjava.GUI.Loan;

import bookjava.GUI.BookJava.utils;
import bookjava.GUI.Book.Book;
import bookjava.GUI.BookJava.guiFilter;
import bookjava.GUI.BookJava.guiManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class guiLoan extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTable listTable;
	
	private static DefaultTableModel BookListModel;
	private static Object[][] data = new Object[][]{};
	private JTextField tfMember;

	/**
	 * Launch the application.
	 */
	public static JFrame main() {
		refreshFilteredData(new ArrayList<>());
		
		guiLoan frame = new guiLoan();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		return frame;
	}

	/**
	 * Create the frame.
	 */
	public guiLoan() {
		setTitle("도서 대출");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 415, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				refreshModelData();
			}
		});

		setContentPane(contentPane);
		contentPane.setLayout(null);

		BookListModel = new DefaultTableModel(
				data,
				new String[] {
						"Book ID", "Title", "Author", "Publisher"
				}
		) {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		JPanel panelBookList = new JPanel();
		panelBookList.setBounds(10, 10, 375, 233);
		contentPane.add(panelBookList);
		
		BookListModel = new DefaultTableModel(
				data,
				new String[] {
						"ID", "Title", "Author", "Publisher"
				}
				);
		panelBookList.setLayout(new BorderLayout(0, 0));
		
		listTable = new JTable(BookListModel);
		
		listTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listTable.setShowVerticalLines(false);
		
		JScrollPane scrollPane = new JScrollPane(listTable);
		panelBookList.add(scrollPane);
		
		JButton btnSearch = new JButton("검색");
		btnSearch.setBounds(295, 250, 90, 30);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiFilter.main(filters -> {
					refreshFilteredData(filters);
					refreshModelData();
				}, "도서 ID", "제목", "작가", "출판사");
			}
		});
		contentPane.add(btnSearch);

		
		tfMember = new JTextField();
		tfMember.setBounds(10, 261, 90, 30);
		contentPane.add(tfMember);
		tfMember.setColumns(10);
		
		JButton btnLoan = new JButton("대출");
		btnLoan.setBounds(193, 250, 90, 30);
		btnLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = listTable.getSelectedRow();
				String memberID = tfMember.getText().trim();
				if ((!memberID.isEmpty()) && selectedRow != -1)
				{
					String bookID = "" + BookListModel.getValueAt(selectedRow, 0);
					guiManagement.loanLoan(bookID, memberID);
					
					dispose();

				}else JOptionPane.showMessageDialog(null, "회원 ID 작성 및 도서 정보를 선택해주세요.");
			}
		});
		contentPane.add(btnLoan);
		
		JLabel lblNewLabel = new JLabel("회원 ID");
		lblNewLabel.setBounds(10, 246, 57, 15);
		contentPane.add(lblNewLabel);

		setLocationRelativeTo(null);
	}

	private static void refreshModelData()
	{
		BookListModel = new DefaultTableModel(
				data,
				new String[] {
						"ID", "Title", "Author", "Publisher"
				}) {

			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		listTable.setModel(BookListModel);

		TableRowSorter<TableModel> sorter = new TableRowSorter<>(BookListModel);
		listTable.setRowSorter(sorter);
	}

	public static void refreshFilteredData(List<String> filters) {
		List<Object[]> resultData = new ArrayList<>();
		Object[][] allofData = utils.getDBBookData();

		for (Object[] row : allofData)
		{
			boolean matches = true;

			for (int i = 0; i < filters.size(); i++) {
				String filterValue = filters.get(i);

				if (filterValue != null && (row[i] == null || !row[i].toString().contains(filterValue))) {
					matches = false;
					break;
				}
			}

			if (matches) {
				resultData.add(row);
			}
		}

		data = resultData.toArray(new Object[0][]);

	}
}


