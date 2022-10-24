package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Attendance extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmpId;
	private JLabel lblNewLabel_1;
	static String employeeId;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Attendance frame = new Attendance();
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
	public Attendance() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtEmpId = new JTextField();
		txtEmpId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtEmpId.setBounds(153, 117, 327, 46);
		contentPane.add(txtEmpId);
		txtEmpId.setColumns(10);
		
		JButton btnCheckIn = new JButton("Check In");
		btnCheckIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Emplid = txtEmpId.getText();
				
				if (Emplid.length() == 0) {
					 JOptionPane.showMessageDialog(null, "Employee ID can't be empty!");
				}else {
					try {
						Connection conn = Utils.getConnection();
						String currDate = LocalDate.now().toString();
						
						String checkEmplIdQuery = "SELECT * FROM employee WHERE EmployeeID = '" + Emplid + "'";
						ResultSet emplId = conn.createStatement().executeQuery(checkEmplIdQuery);
						
						if (emplId.next()) {
							boolean isCheckedIn = false;
							String checker = "SELECT * FROM attendance WHERE EmployeeID = '" + txtEmpId.getText() + "'" + "AND Date = '" + currDate + "'";
							ResultSet checkerRes = conn.createStatement().executeQuery(checker);
							
							if (checkerRes.next()) isCheckedIn = true;
							
							if (!isCheckedIn) {
								String getLastId = "SELECT * FROM attendance ORDER BY AttendanceID DESC";
								ResultSet lastId = conn.createStatement().executeQuery(getLastId);
								String attId = "";
								
								if (!lastId.next()) {
									attId = "ATD001";
								}else {
									String Id = lastId.getString("AttendanceID").substring(3);
									int tempId = Integer.parseInt(Id) + 1;
									if (tempId < 10) {
										attId = "ATD00" + tempId;
									}else if (tempId < 100 && tempId > 10) {
										attId = "ATD0" + tempId;
									}else {
										attId = "ATD" + tempId;
									}
								}
								try {
									String insertAttendace = "INSERT INTO attendance VALUES (?, ?, ?, ?, ?)";
									PreparedStatement p = conn.prepareStatement(insertAttendace);
									
									p.setString(1, attId);
									p.setString(2, emplId.getString("EmployeeID"));
									p.setString(3,LocalTime.now().toString());
									p.setNull(4, Types.NULL);
									p.setString(5, currDate);
									p.executeUpdate();
									p.close();
									emplId.close();
									conn.close();
									
									employeeId = txtEmpId.getText();
									JOptionPane.showMessageDialog(null, "Check In Success!");
									Profile profile = new Profile();
									profile.setVisible(true);
									profile.setLocationRelativeTo(null);
									dispose();
									
									
								} catch (Exception e) {
									e.printStackTrace();
								}
							}else {
								JOptionPane.showMessageDialog(null, "You have already checked in for today!");
							}
						}else {
							JOptionPane.showMessageDialog(null, "Employee ID Not Found!");
						}
						
					}catch (Exception e) {
						System.out.println("Failed to start");
					}
				}
				
			}
		});
		btnCheckIn.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnCheckIn.setBackground(SystemColor.activeCaption);
		btnCheckIn.setBounds(153, 204, 327, 40);
		contentPane.add(btnCheckIn);
		
		JLabel lblNewLabel = new JLabel("Employee ID");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel.setBounds(153, 91, 99, 16);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Employee Attendance");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel_1.setBounds(105, 13, 419, 46);
		contentPane.add(lblNewLabel_1);
	}

}
