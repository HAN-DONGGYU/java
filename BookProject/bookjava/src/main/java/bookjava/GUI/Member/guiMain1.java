package bookjava.GUI.Member;
import bookjava.GUI.BookJava.guiFilter;
import bookjava.GUI.BookJava.guiManagement;
import bookjava.GUI.BookJava.utils;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class guiMain1 extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTable memberTable;
	private JPanel panelBtn;
	private JButton btnRegister;
	private JButton btnEdit;
	private JButton btnSearch;
	
	private static DefaultTableModel memberTableModel;
	private static Object[][] data = null;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		refreshFilteredData(new ArrayList<>());

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiMain1 frame = new guiMain1();
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
	public guiMain1() {
		setTitle("회원 관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 0, 10));

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				refreshModelData();
			}
		});

		memberTableModel = new DefaultTableModel(
			data,
			new String[] {
				"ID", "Name", "Phone", "Email", "Address"
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
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		memberTable = new JTable(memberTableModel);
		
		memberTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		memberTable.setShowVerticalLines(false);
		
		scrollPane = new JScrollPane(memberTable);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(scrollPane);
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(memberTableModel);
        memberTable.setRowSorter(sorter);
		
		panelBtn = new JPanel();
		contentPane.add(panelBtn, BorderLayout.SOUTH);
		
		btnRegister = new JButton("등록");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = guiRegister.main();
				
				frame.addWindowListener(new WindowAdapter() {
				    @Override
				    public void windowClosed(WindowEvent e) {
				        refreshFilteredData(List.of(""));
				        refreshModelData();
				    }
				});
			}
		});
		panelBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelBtn.add(btnRegister);
		
		btnEdit = new JButton("수정");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = memberTable.getSelectedRow();
				if (selectedRow != -1)
				{
					String memberID = "" + memberTableModel.getValueAt(selectedRow, 0);
					String name = "" + memberTableModel.getValueAt(selectedRow, 1);
					String phone = "" + memberTableModel.getValueAt(selectedRow, 2);
					String email = "" + memberTableModel.getValueAt(selectedRow, 3);
					String address = "" + memberTableModel.getValueAt(selectedRow, 4);

					JFrame frame = guiEdit.main(memberID, name, phone, email, address);
					
					frame.addWindowListener(new WindowAdapter() {
					    @Override
					    public void windowClosed(WindowEvent e) {
					        refreshFilteredData(List.of(""));
					        refreshModelData();
					    }
					});

				}else JOptionPane.showMessageDialog(null, "수정 할 회원 내용을 선택해주세요.");

			}
		});
		panelBtn.add(btnEdit);
		
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiFilter.main(filters -> {
					refreshFilteredData(filters);
					refreshModelData();
				}, "회원 ID", "이름", "전화번호", "이메일", "주소");
			}
		});
		panelBtn.add(btnSearch);

		setLocationRelativeTo(null);
		
		refreshFilteredData(List.of((String) ""));
		refreshModelData();
	}

	private static void refreshModelData()
	{
		memberTableModel = new DefaultTableModel(
				data,
				new String[] {
						"ID", "Name", "Phone", "Email", "Address"
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

		memberTable.setModel(memberTableModel);

		TableRowSorter<TableModel> sorter = new TableRowSorter<>(memberTableModel);
		memberTable.setRowSorter(sorter);
	}

	public static void refreshFilteredData(List<String> filters) {
		List<Object[]> resultData = new ArrayList<>();
		Object[][] allofData = utils.getDBMmberData();

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


