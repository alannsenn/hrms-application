package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AttendanceManager extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AttendanceManager frame = new AttendanceManager();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AttendanceManager() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				getData();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 671, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Attendances Manager");
		title.setFont(new Font("Malgun Gothic", Font.BOLD, 24));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(184, 24, 282, 45);
		contentPane.add(title);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 103, 533, 188);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnBack = new JButton("Back to Main Menu");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminMenu adminMenu = new AdminMenu();
				adminMenu.setVisible(true);
				adminMenu.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnBack.setBackground(SystemColor.activeCaption);
		btnBack.setBounds(61, 316, 144, 46);
		contentPane.add(btnBack);
	}
	
	private void getData () {
		Connection conn = Utils.getConnection();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Employee ID");
		model.addColumn("Check In Time");
		model.addColumn("Check Out Time");
		model.addColumn("Date");
		
		try {
			String fetchQuery = "SELECT * FROM attendance";
			Statement s = conn.createStatement();
			ResultSet res = s.executeQuery(fetchQuery);
			
			while (res.next()) {
				model.addRow(new Object[] {
						res.getString("EmployeeID"),
						res.getString("CheckInTime"),
						res.getString("CheckOutTime"),
						res.getString("Date")
				});
			}
			
			res.close();
			s.close();
			conn.close();
			
			table.setModel(model);
			table.setAutoResizeMode(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(110);
			table.getColumnModel().getColumn(1).setPreferredWidth(140);
			table.getColumnModel().getColumn(2).setPreferredWidth(140);
			table.getColumnModel().getColumn(3).setPreferredWidth(140);
			
		} catch (Exception e) {
			System.out.println("Failed in fetching data");
		}
	}
}
