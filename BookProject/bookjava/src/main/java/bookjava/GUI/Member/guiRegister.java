package bookjava.GUI.Member;
import java.awt.EventQueue;
import bookjava.GUI.BookJava.guiManagement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class guiRegister extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfPhone;
	private JTextField tfEmail;
	private JTextField tfAddress;
	private JTextField tfName;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JButton btnConfirm;

	/**
	 * Launch the application.
	 */
	public static JFrame main() {
		guiRegister frame = new guiRegister();
		
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
	public guiRegister() {
		setTitle("회원 정보 등록");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 440, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfPhone = new JTextField();
		tfPhone.setBounds(111, 28, 100, 30);
		contentPane.add(tfPhone);
		tfPhone.setColumns(10);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(210, 28, 100, 30);
		contentPane.add(tfEmail);
		
		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		tfAddress.setBounds(309, 28, 100, 30);
		contentPane.add(tfAddress);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(12, 28, 100, 30);
		contentPane.add(tfName);
		
		lblNewLabel = new JLabel("이름");
		lblNewLabel.setBounds(22, 10, 57, 15);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("전화번호");
		lblNewLabel_1.setBounds(121, 10, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("이메일");
		lblNewLabel_2.setBounds(220, 10, 57, 15);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("주소");
		lblNewLabel_3.setBounds(319, 10, 57, 15);
		contentPane.add(lblNewLabel_3);
		
		btnConfirm = new JButton("등록");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> changedMember = new ArrayList<>(Arrays.asList(null, null, null, null));

				if (!tfName.getText().trim().isEmpty()) changedMember.set(0, tfName.getText().trim());
				if (!tfPhone.getText().trim().isEmpty()) changedMember.set(1, tfPhone.getText().trim());
				if (!tfEmail.getText().trim().isEmpty()) changedMember.set(2, tfEmail.getText().trim());
				if (!tfAddress.getText().trim().isEmpty()) changedMember.set(3, tfAddress.getText().trim());

				if (!changedMember.contains(null))
				{
					guiManagement.memberRegister(changedMember);
				}
				dispose();
			}
		});
		btnConfirm.setBounds(309, 88, 100, 30);
		contentPane.add(btnConfirm);

		setLocationRelativeTo(null);
	}

}
