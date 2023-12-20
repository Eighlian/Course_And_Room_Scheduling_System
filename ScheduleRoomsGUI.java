package View;
import Model.*;
import Controller.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.sql.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
public class ScheduleRoomsGUI {

	private JFrame frame;
	private JTextField txtCname;
	private JTextField txtClevel;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduleRoomsGUI window = new ScheduleRoomsGUI();
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
	public ScheduleRoomsGUI() {
		initialize();
		Connect();
		table_load();
		Connect_1();
		table_1load();
	}
	//database1
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	//database2
	Connection con_1;
	PreparedStatement pst_1;
	ResultSet rs_1;
	
	private JTextField txtRtype;
	private JTextField txtRnumber;
	private JTextField txtsType;
	private JTextField txtsLevel;
	private JTextField txtsAmount;
	private JTextField txtclevelAdd;
	private JTextField txtcsizeAdd;
	private JTextField txtclabAdd;
	private JTextField txtctypeAdd;
	
	
	//db1
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
	//db2
	public void Connect_1()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con_1 = DriverManager.getConnection("jdbc:mysql://localhost/roomdata","root","");
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
	public void table_1load()
	{
		try {
			pst_1 = con_1.prepareStatement("select * from roomtable");
			rs_1 = pst_1.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs_1));
			
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
		frame.setBounds(100, 100, 1356, 727);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Admin Control Page");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(150, -15, 326, 68);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Assign Course to Room", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 87, 302, 245);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Course Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(13, 19, 118, 48);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Course Level:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(13, 58, 118, 48);
		panel.add(lblNewLabel_1_1);
		
		txtCname = new JTextField();
		txtCname.setBounds(149, 35, 118, 20);
		panel.add(txtCname);
		txtCname.setColumns(10);
		
		txtClevel = new JTextField();
		txtClevel.setColumns(10);
		txtClevel.setBounds(149, 74, 118, 20);
		panel.add(txtClevel);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Room Type:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(13, 91, 118, 48);
		panel.add(lblNewLabel_1_1_1);
		
		txtRtype = new JTextField();
		txtRtype.setColumns(10);
		txtRtype.setBounds(149, 107, 118, 20);
		panel.add(txtRtype);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Room Number:");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1_1.setBounds(13, 122, 126, 48);
		panel.add(lblNewLabel_1_1_1_1);
		
		txtRnumber = new JTextField();
		txtRnumber.setColumns(10);
		txtRnumber.setBounds(149, 138, 118, 20);
		panel.add(txtRnumber);
		
		JButton btnAssign = new JButton("Assign");
		btnAssign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//new obj
				CourseInterface courseControl = new CourseController();
				
				String ctype, clevel, rtype, rnumber;
				//course name
				ctype = txtCname.getText();
				//course level
				clevel = txtClevel.getText();
				//room type (Lab/Room);
				rtype =	txtRtype.getText();
				
				
				//room number
				rnumber =txtRnumber.getText();
			
					
				
				try {
					//send info to assignRoom
									//TYPE      LEVEL	     	RTYPE    RNUMBER
					courseControl.assignRoom(ctype, clevel, rtype, rnumber);
					
					JOptionPane.showMessageDialog(null,"Course Assigned!");
					//reload table
					table_load();
					table_1load();
					
			
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			
				
			}
		});
		btnAssign.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAssign.setBounds(48, 181, 183, 43);
		panel.add(btnAssign);
		
		//database 1 
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(322, 86, 510, 483);
		frame.getContentPane().add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		//end of databsae
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnBack.setBounds(10, 11, 93, 68);
		frame.getContentPane().add(btnBack);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Add Students to Course", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(353, 580, 694, 97);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("Course Type:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(31, 11, 107, 48);
		panel_1.add(lblNewLabel_1_2);
		
		txtsType = new JTextField();
		txtsType.setColumns(10);
		txtsType.setBounds(31, 53, 111, 20);
		panel_1.add(txtsType);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Course Level:");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_2_1.setBounds(194, 11, 127, 48);
		panel_1.add(lblNewLabel_1_2_1);
		
		txtsLevel = new JTextField();
		txtsLevel.setColumns(10);
		txtsLevel.setBounds(194, 53, 111, 20);
		panel_1.add(txtsLevel);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Amount");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_2_1_1.setBounds(401, 11, 92, 48);
		panel_1.add(lblNewLabel_1_2_1_1);
		
		txtsAmount = new JTextField();
		txtsAmount.setColumns(10);
		txtsAmount.setBounds(379, 53, 111, 20);
		panel_1.add(txtsAmount);
		
		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Add student
				
				String addstype, addslevel, addsamount;
				
				addstype = txtsType.getText();
				addslevel = txtsLevel.getText();
				addsamount = txtsAmount.getText();

				int amount = Integer.parseInt(addsamount);
			

				try {
					
				CourseInterface courseControl = new CourseController();
				
				courseControl.registerStudents(addstype, addslevel, amount);
					
					JOptionPane.showMessageDialog(null,"Student added!");
					//reload table
					table_load();
					table_1load();
			
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(527, 40, 84, 43);
		panel_1.add(btnNewButton_1);
		
		//database 2
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(842, 86, 452, 482);
		frame.getContentPane().add(scrollPane_1);
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		//end of databse 2
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Add a Course", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 343, 302, 258);
		frame.getContentPane().add(panel_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Course Level:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_3.setBounds(10, 52, 118, 48);
		panel_2.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Course Size:");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_2.setBounds(10, 91, 118, 48);
		panel_2.add(lblNewLabel_1_1_2);
		
		txtclevelAdd = new JTextField();
		txtclevelAdd.setColumns(10);
		txtclevelAdd.setBounds(156, 68, 118, 20);
		panel_2.add(txtclevelAdd);
		
		txtcsizeAdd = new JTextField();
		txtcsizeAdd.setColumns(10);
		txtcsizeAdd.setBounds(156, 107, 118, 20);
		panel_2.add(txtcsizeAdd);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Lab Required:");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1_2.setBounds(10, 124, 139, 48);
		panel_2.add(lblNewLabel_1_1_1_2);
		
		txtclabAdd = new JTextField();
		txtclabAdd.setColumns(10);
		txtclabAdd.setBounds(156, 140, 118, 20);
		panel_2.add(txtclabAdd);
		
		JButton btnAssign_1 = new JButton("Add");
		btnAssign_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				//ADD COURSE
				String addctype, addclevel, addclab;
				int addcsize;
				
				
				
				
				//set vars = to textbox
				addctype = txtctypeAdd.getText();
				addclevel = txtclevelAdd.getText();
				addcsize = Integer.parseInt(txtcsizeAdd.getText());
				addclab = txtclabAdd.getText();
			
				
				CourseController courseController = new CourseController();
				Course c = new Course(addctype, addclevel, addcsize, addclab, "none");
				
				try {
					
					courseController.insert(c);
					JOptionPane.showMessageDialog(null,"Course added!");
					//reload table
					table_load();
					table_1load();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnAssign_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAssign_1.setBounds(48, 183, 183, 43);
		panel_2.add(btnAssign_1);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Course Type:");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_3_1.setBounds(10, 11, 118, 48);
		panel_2.add(lblNewLabel_1_3_1);
		
		txtctypeAdd = new JTextField();
		txtctypeAdd.setColumns(10);
		txtctypeAdd.setBounds(156, 27, 118, 20);
		panel_2.add(txtctypeAdd);
		
		JLabel lblNewLabel_2 = new JLabel("Course Database");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(508, 57, 130, 22);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Room Database");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(967, 57, 130, 22);
		frame.getContentPane().add(lblNewLabel_2_1);
	}
}
