package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.rmi.CORBA.Util;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JLabel lblNewLabel_2;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Admin Login");
		title.setBounds(0, 13, 422, 51);
		title.setFont(new Font("Segoe UI", Font.BOLD, 24));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(title);
		
		username = new JTextField();
		username.setHorizontalAlignment(SwingConstants.LEFT);
		username.setBounds(81, 82, 269, 32);
		contentPane.add(username);
		username.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String uName = username.getText();
				String pass = passwordField.getText();
				
				if (uName.length() == 0 || pass.length() == 0) {
					 JOptionPane.showMessageDialog(null, "Please fill all fields!");
				}else {
					try {
						Connection conn = Utils.getConnection();
						java.sql.Statement s = conn.createStatement();
						String query = "SELECT * FROM admin WHERE Username = '" + uName + "' and Password = '" + pass + "'";
						ResultSet res = s.executeQuery(query);
						if (res.next()) {
							JOptionPane.showMessageDialog(null, "Login success!");
							AdminMenu adminMenu = new AdminMenu();
							adminMenu.setVisible(true);
							adminMenu.setLocationRelativeTo(null);
							dispose();
						}else {
							JOptionPane.showMessageDialog(null, "Credentials not found!");
						}
					} catch (Exception e) {
						System.out.println("failed to connect");
					}					
				}
			}
		});
		 
		
		btnLogin.setBackground(SystemColor.activeCaption);
		btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnLogin.setBounds(81, 208, 269, 32);
		contentPane.add(btnLogin);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(81, 63, 81, 16);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(81, 133, 81, 16);
		contentPane.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(81, 152, 269, 32);
		contentPane.add(passwordField);
	}
}
