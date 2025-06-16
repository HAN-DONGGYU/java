package bookjava.GUI.Loan;

import bookjava.GUI.BookJava.guiFilter;
import bookjava.GUI.BookJava.guiManagement;
import bookjava.GUI.BookJava.utils;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static bookjava.GUI.BookJava.utils.getExpirationStatus;
import static bookjava.GUI.BookJava.utils.isValidDateFormat;

public class guiMain extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTable loanTable;
	private JPanel panelBtn;
	private JButton btnLoan;
	private JButton btnReturn;
	private JButton btnSearch;

	private static DefaultTableModel loanTableModel;
	private static Object[][] data = new Object[][]{};
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiMain frame = new guiMain();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public guiMain() {
		setTitle("도서 대출 관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				refreshModelData();
			}
		});

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		loanTableModel = new DefaultTableModel(
				data,
				new String[] {
						"Loan ID", "Book ID", "Title", "Date", "Member(id)"
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

		loanTable = new JTable(loanTableModel);
		
		loanTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		loanTable.setShowVerticalLines(false);
		
		scrollPane = new JScrollPane(loanTable);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(scrollPane);
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(loanTableModel);
        loanTable.setRowSorter(sorter);
		
		panelBtn = new JPanel();
		contentPane.add(panelBtn, BorderLayout.SOUTH);
		
		btnLoan = new JButton("대출");
		btnLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame loanFrame = guiLoan.main();
				
				loanFrame.addWindowListener(new WindowAdapter() {
				    @Override
				    public void windowClosed(WindowEvent e) {
				        refreshFilteredData(List.of(""));
				        refreshModelData();
				    }
				});
			}
		});
		panelBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelBtn.add(btnLoan);
		
		btnReturn = new JButton("반납");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = loanTable.getSelectedRow();
				if (selectedRow != -1)
				{
					JOptionPane.showMessageDialog(null, "\"" + loanTableModel.getValueAt(selectedRow, 2) + "\" 책이 반납되었습니다.");
					
					String loanID = "" + loanTableModel.getValueAt(selectedRow, 0);
					guiManagement.loanReturn(loanID);
					
					refreshFilteredData(List.of((String) ""));
					refreshModelData();

				}else JOptionPane.showMessageDialog(null, "반납 할 대출 정보를 선택해주세요.");

			}
		});
		panelBtn.add(btnReturn);
		
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiFilter.main(filters -> {
					refreshFilteredData(filters);
					refreshModelData();
				}, "대출 ID", "도서 ID", "도서 제목", "대출 날짜", "회원 ID");
			}
		});
		panelBtn.add(btnSearch);
		setLocationRelativeTo(null);
		pack();
		
		refreshFilteredData(List.of((String) ""));
		refreshModelData();
	}

	private static void refreshModelData()
	{
		loanTableModel = new DefaultTableModel(
				data,
				new String[] {
						"Loan ID", "Book ID", "Title", "Date", "Member(id)"
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

		loanTable.setModel(loanTableModel);

		TableRowSorter<TableModel> sorter = new TableRowSorter<>(loanTableModel);
		sorter.setComparator(0, utils.getDateStringComparator());
		loanTable.setRowSorter(sorter);	
		
	}

	public static void refreshFilteredData(List<String> filters) {
		List<Object[]> resultData = new ArrayList<>();
		Object[][] allofData = utils.replaceDBDateFormat(utils.getDBLoanData());

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
