package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class Profile extends JFrame {

	private JPanel contentPane;
	String employeeId = Attendance.employeeId;
	Calendar calendar;
	SimpleDateFormat timeFormat;
	String time;
	JLabel currDateTime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile frame = new Profile();
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
	public Profile() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 571, 299);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProfile = new JLabel();
		lblProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfile.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblProfile.setBounds(149, 13, 259, 45);
		contentPane.add(lblProfile);
		
		JLabel checkInTimeLabel = new JLabel("New label");
		checkInTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		checkInTimeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		checkInTimeLabel.setBounds(165, 93, 227, 34);
		contentPane.add(checkInTimeLabel);
		
		JLabel checkInTime = new JLabel("New label");
		checkInTime.setHorizontalAlignment(SwingConstants.CENTER);
		checkInTime.setFont(new Font("Segoe UI", Font.BOLD, 16));
		checkInTime.setBounds(121, 128, 315, 34);
		contentPane.add(checkInTime);
		
		String currDate = java.time.LocalDate.now().toString();
		String currTime = java.time.LocalTime.now().toString();
		
		String query = "SELECT * FROM employee e JOIN attendance a ON e.EmployeeID = a.EmployeeID WHERE a.EmployeeID = '" + employeeId + "' AND Date = '" + currDate + "'";
		
		try {
			Connection conn = Utils.getConnection();
			Statement s = conn.createStatement();
			
			ResultSet res = s.executeQuery(query);
			
			if (res.next()) {
				lblProfile.setText("Hello, " + res.getString("EmployeeName") + "!");
				checkInTimeLabel.setText("Successfully checked in at:");
				checkInTime.setText(res.getString("Date") + " ," + res.getString("CheckInTime"));
			}
			
		} catch (Exception e) {
			System.out.println("Error occured!");
		}
		
		JButton btnCheckOut = new JButton("Check Out");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection conn = Utils.getConnection();
					Statement selectState = conn.createStatement();
					ResultSet selectRes = selectState.executeQuery(query);
					
					if (selectRes.next()) {
						String updateAttendance = "UPDATE attendance SET CheckOutTime = '" + currTime + "' WHERE AttendanceID = '" + selectRes.getString("AttendanceID") + "'"; 
						Statement updateState = conn.createStatement();
						updateState.execute(updateAttendance);
						
						JOptionPane.showMessageDialog(null, "Attendance successfully recorded!");
						selectState.close();
						updateState.close();
						System.exit(0);						
					}else {
						JOptionPane.showMessageDialog(null, "Can't process your checkout!");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnCheckOut.setBackground(SystemColor.activeCaption);
		btnCheckOut.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnCheckOut.setBounds(158, 186, 234, 40);
		contentPane.add(btnCheckOut);
	}
}
