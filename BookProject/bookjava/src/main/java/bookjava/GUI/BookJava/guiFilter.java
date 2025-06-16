package bookjava.GUI.BookJava;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class guiFilter extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfFilter1;

	private Consumer<List<String>> onFilterApplied;

	/**
	 * Launch the application.
	 */
	public static void main(Consumer<List<String>> onFilterApplied, String... filters) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiFilter frame = new guiFilter(onFilterApplied, filters);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public guiFilter(Consumer<List<String>> onFilterApplied, String... filters) {
		this.onFilterApplied = onFilterApplied;

		setTitle("필터");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 170, 100 + (55 * filters.length));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel filterPanelList = new JPanel();
		contentPane.add(filterPanelList, BorderLayout.CENTER);

		List<JTextField> tfFilteredList = new ArrayList<>();
		for (String row : filters)
		{
			JPanel filterPanel1 = new JPanel();
			filterPanel1.setBorder(BorderFactory.createTitledBorder(row));
			filterPanel1.setLayout(new BorderLayout(0, 0));

			tfFilter1 = new JTextField();
			filterPanel1.add(tfFilter1);
			tfFilter1.setColumns(10);
			filterPanelList.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			filterPanelList.add(filterPanel1);

			tfFilteredList.add(tfFilter1);
		}
		
		JButton btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> tfTextList = new ArrayList<>();

				for (JTextField tf : tfFilteredList)
				{
					if (!tf.getText().trim().isEmpty()) tfTextList.add(tf.getText());
					else tfTextList.add(null);
				}

				onFilterApplied.accept(tfTextList);
				guiFilter.super.dispose();
			}
		});
		contentPane.add(btnSearch, BorderLayout.SOUTH);

		setLocationRelativeTo(null);
	}

}