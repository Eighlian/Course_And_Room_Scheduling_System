package View;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import net.proteanit.sql.DbUtils;


import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GeneralGUI {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GeneralGUI window = new GeneralGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GeneralGUI() {
		initialize();
		Connect();
		table_load();
	}
	
	
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	
	public void Connect()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/coursedata","root","");
		}
		catch (ClassNotFoundException ex)
		{
			
		}
		catch(SQLException ex)
		{
			
		}
	}
	
	
	
	public void table_load()
	{
		try {
			pst = con.prepareStatement("select * from coursetable");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 835, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("General View");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblNewLabel.setBounds(288, 0, 186, 99);
		frame.getContentPane().add(lblNewLabel);
		
		//SCROLL PANE WILL CONTAIN DATABASE INFORMATION;
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 198, 799, 301);
		frame.getContentPane().add(scrollPane);
		
		
		
		//TABLE WILL BE DATABASE;;;;;
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Rooms");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(31, 111, 107, 45);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Display");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1.setBounds(31, 49, 117, 65);
		frame.getContentPane().add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(31, 97, 141, 19);
		frame.getContentPane().add(separator);
		
		JButton btnStudentsInCourse = new JButton("Student/Course");
		btnStudentsInCourse.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnStudentsInCourse.setBounds(158, 111, 204, 45);
		frame.getContentPane().add(btnStudentsInCourse);
		
		JButton btnAll = new JButton("All");
		btnAll.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAll.setBounds(389, 110, 107, 45);
		frame.getContentPane().add(btnAll);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack.setBounds(683, 22, 107, 45);
		frame.getContentPane().add(btnBack);
		
	}
}
