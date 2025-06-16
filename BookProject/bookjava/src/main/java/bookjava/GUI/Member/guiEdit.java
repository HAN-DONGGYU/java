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

public class guiEdit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfAfterName;
	private JTextField tfAfterEmail;
	private JTextField tfAfterPhone;
	private JTextField tfAfterAddress;
	private JTextField tfBeforeName;
	private JTextField tfBeforePhone;
	private JTextField tfBeforeEmail;
	private JTextField tfBeforeAddress;

	/**
	 * Launch the application.
	 */
	public static JFrame main(String memberid, String name, String phone, String email, String address) {
		guiEdit frame = new guiEdit(memberid, name, phone, email, address);

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
	public guiEdit(String memberid, String name, String phone, String email, String address) {
		setTitle("회원 정보 수정");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 440, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfAfterName = new JTextField();
		tfAfterName.setColumns(10);
		tfAfterName.setBounds(12, 134, 100, 30);
		contentPane.add(tfAfterName);
		
		tfAfterEmail = new JTextField();
		tfAfterEmail.setColumns(10);
		tfAfterEmail.setBounds(210, 134, 100, 30);
		contentPane.add(tfAfterEmail);
		
		tfAfterPhone = new JTextField();
		tfAfterPhone.setColumns(10);
		tfAfterPhone.setBounds(111, 134, 100, 30);
		contentPane.add(tfAfterPhone);
		
		tfAfterAddress = new JTextField();
		tfAfterAddress.setColumns(10);
		tfAfterAddress.setBounds(309, 134, 100, 30);
		contentPane.add(tfAfterAddress);
		
		JButton btnConfirm = new JButton("수정");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> changedMember = new ArrayList<>(Arrays.asList(
						tfBeforeName.getText(), 
						tfBeforePhone.getText(), 
						tfBeforeEmail.getText(), 
						tfBeforeAddress.getText()
						));
				
				if (!tfAfterName.getText().trim().equals("")) changedMember.set(0, tfAfterName.getText().trim());
				if (!tfAfterPhone.getText().trim().equals("")) changedMember.set(1, tfAfterPhone.getText().trim());
				if (!tfAfterEmail.getText().trim().equals("")) changedMember.set(2, tfAfterEmail.getText().trim());
				if (!tfAfterAddress.getText().trim().equals("")) changedMember.set(3, tfAfterAddress.getText().trim());
				
				if (!changedMember.equals(Arrays.asList(
						tfBeforeName.getText(), 
						tfBeforePhone.getText(), 
						tfBeforeEmail.getText(), 
						tfBeforeAddress.getText()
						)));
				{
					guiManagement.memberEdit(memberid, changedMember);
				}
				dispose();
			}
		});
		btnConfirm.setBounds(309, 221, 100, 30);
		contentPane.add(btnConfirm);
		
		JButton btnDelete = new JButton("삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiManagement.memberDelete(memberid);
				dispose();
			}
		});
		btnDelete.setBounds(210, 221, 100, 30);
		contentPane.add(btnDelete);
		
		tfBeforeName = new JTextField();
		tfBeforeName.setText(name);
		tfBeforeName.setColumns(10);
		tfBeforeName.setBounds(12, 54, 100, 30);
		tfBeforeName.setEditable(false);
		contentPane.add(tfBeforeName);
		
		tfBeforePhone = new JTextField();
		tfBeforePhone.setText(phone);
		tfBeforePhone.setColumns(10);
		tfBeforePhone.setBounds(111, 54, 100, 30);
		tfBeforePhone.setEditable(false);
		contentPane.add(tfBeforePhone);
		
		tfBeforeEmail = new JTextField();
		tfBeforeEmail.setText(email);
		tfBeforeEmail.setColumns(10);
		tfBeforeEmail.setBounds(210, 54, 100, 30);
		tfBeforeEmail.setEditable(false);
		contentPane.add(tfBeforeEmail);
		
		tfBeforeAddress = new JTextField();
		tfBeforeAddress.setText(address);
		tfBeforeAddress.setColumns(10);
		tfBeforeAddress.setBounds(309, 54, 100, 30);
		tfBeforeAddress.setEditable(false);
		contentPane.add(tfBeforeAddress);
		
		JLabel lblNewLabel_4 = new JLabel("기존 내용");
		lblNewLabel_4.setBounds(12, 36, 57, 15);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("수정 내용");
		lblNewLabel_5.setBounds(12, 116, 57, 15);
		contentPane.add(lblNewLabel_5);

		setLocationRelativeTo(null);
	}

}


