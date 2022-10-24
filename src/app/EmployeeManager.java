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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeeManager extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeManager frame = new EmployeeManager();
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
	public EmployeeManager() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				getData();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 854, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Employee List");
		title.setFont(new Font("Malgun Gothic", Font.BOLD, 24));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(272, 33, 282, 45);
		contentPane.add(title);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 103, 723, 252);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnBack = new JButton("Back to Main Menu");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminMenu adminMenu = new AdminMenu();
				adminMenu.setVisible(true);
				adminMenu.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnBack.setBackground(SystemColor.activeCaption);
		btnBack.setBounds(60, 377, 144, 46);
		contentPane.add(btnBack);
	}
	
	private void getData () {
		Connection conn = Utils.getConnection();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Employee ID");
		model.addColumn("Employee Name");
		model.addColumn("Employee Address");
		model.addColumn("Email");
		model.addColumn("Gender");
		model.addColumn("Position");
		
		try {
			String fetchQuery = "SELECT * FROM employee";
			Statement s = conn.createStatement();
			ResultSet res = s.executeQuery(fetchQuery);
			
			while (res.next()) {
				model.addRow(new Object[] {
						res.getString("EmployeeID"),
						res.getString("EmployeeName"),
						res.getString("EmployeeAddress"),
						res.getString("EmployeeEmail"),
						res.getString("EmployeeGender"),
						res.getString("EmployeePosition"),
				});
			}
			
			res.close();
			s.close();
			conn.close();
			
			table.setModel(model);
			table.setAutoResizeMode(0);
			
			table.getColumnModel().getColumn(0).setPreferredWidth(100);
			table.getColumnModel().getColumn(1).setPreferredWidth(140);
			table.getColumnModel().getColumn(2).setPreferredWidth(140);
			table.getColumnModel().getColumn(3).setPreferredWidth(140);
			table.getColumnModel().getColumn(4).setPreferredWidth(60);
			table.getColumnModel().getColumn(5).setPreferredWidth(160);
			
		} catch (Exception e) {
			System.out.println("Failed in fetching data");
		}
	}
}
