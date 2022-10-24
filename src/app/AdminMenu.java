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

public class AdminMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenu frame = new AdminMenu();
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
	public AdminMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Welcome, Admin!");
		title.setFont(new Font("Malgun Gothic", Font.BOLD, 24));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(66, 13, 519, 44);
		contentPane.add(title);
		
		JButton btnAttendance = new JButton("Attendance Manager");
		btnAttendance.setBackground(SystemColor.activeCaption);
		btnAttendance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AttendanceManager manageAttd = new AttendanceManager();
				manageAttd.setVisible(true);
				manageAttd.setLocationRelativeTo(null);
				dispose();
			}
		});
		
		btnAttendance.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnAttendance.setBounds(197, 103, 260, 52);
		contentPane.add(btnAttendance);
		
		JButton btnAdmin = new JButton("Employee List");
		btnAdmin.setBackground(SystemColor.activeCaption);
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeManager manageEmp = new EmployeeManager();
				manageEmp.setVisible(true);
				manageEmp.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnAdmin.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnAdmin.setBounds(197, 189, 260, 52);
		contentPane.add(btnAdmin);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setForeground(Color.WHITE);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu menu = new MainMenu();
				menu.setVisible(true);
				menu.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnLogout.setBackground(Color.RED);
		btnLogout.setBounds(539, 301, 108, 37);
		contentPane.add(btnLogout);
	}
}
