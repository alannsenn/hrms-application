package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setLocationRelativeTo(null);
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
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Human Resources Management System");
		title.setFont(new Font("Malgun Gothic", Font.BOLD, 24));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(66, 13, 519, 44);
		contentPane.add(title);
		
		JButton btnAttendance = new JButton("Employee Attendance");
		btnAttendance.setBackground(SystemColor.activeCaption);
		btnAttendance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Attendance attd = new Attendance();
				attd.setVisible(true);
				attd.setLocationRelativeTo(null);
				dispose();
			}
		});
		
		btnAttendance.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnAttendance.setBounds(197, 103, 260, 52);
		contentPane.add(btnAttendance);
		
		JButton btnAdmin = new JButton("System Administrator");
		btnAdmin.setBackground(SystemColor.activeCaption);
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				login.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnAdmin.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnAdmin.setBounds(197, 189, 260, 52);
		contentPane.add(btnAdmin);
		
		JLabel lblNewLabel = new JLabel("Copyright \u00A9 2021 Group 2");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(236, 294, 160, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("V1.0");
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(288, 265, 56, 16);
		contentPane.add(lblNewLabel_1);
	}
}
