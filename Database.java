package Model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//delcare class database
public class Database {
	
	//set final room file and course file
	private static final String ROOM_FILE = "C:\\Users\\Dillon\\eclipse-workspace\\CPSC PROJECT\\src\\roominfo.csv";
	private static final String COURSE_FILE = "C:\\Users\\Dillon\\eclipse-workspace\\CPSC PROJECT\\src\\courseinfo.csv";
	
	//constructor for database
	private Database() {
		
	}
	
	//method to establish connection to database
	public static Connection getConnection1() throws SQLException{
		//try
		try {
			//instantiate connection
			Connection conn = null;
			//set vars
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/roomdata";
			String username = "root";
			String password = "";
			
			//invoke driver
			Class.forName(driver);
			
			//get connection
			conn = DriverManager.getConnection(url, username, password);
	
			return conn;
			
			//catch
		}catch(Exception e) {
			System.out.println(e);
			
		}
		//return null
		return null;
	}
	
	//method to establish connection to database
	public static Connection getConnection2() throws SQLException{
		//try
		try {
			//instantiate connection
			Connection conn = null;
			
			//create vars
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/coursedata";
			String username = "root";
			String password = "";
			
			//invoke driver
			Class.forName(driver);
			
			//get connection	
			conn = DriverManager.getConnection(url, username, password);
			
			//return connection
			return conn;
			
			//catch
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		//return null
		return null;
	}
	//method to loadDatabase with files
	public static void loadDatabase() throws SQLException, NumberFormatException, IOException {
		
		//create connections and get meta data
		Connection con1 = getConnection1();
		Connection con2 = getConnection2();
		DatabaseMetaData dbm1 = con1.getMetaData();
		DatabaseMetaData dbm2 = con2.getMetaData();
		
		
		ResultSet roomTable = dbm1.getTables(null, null, "roomtable", null);
		ResultSet courseTable = dbm2.getTables(null, null, "coursetable", null);
		// check if roomTable or courseTable exists
		if (roomTable.next() || courseTable.next()) {
			
			// Table exists clear tables
			String sql  = "DELETE FROM roomtable";
			PreparedStatement ps = con1.prepareStatement(sql);
			ps.execute();
			sql = "DELETE FROM coursetable";
			ps = con2.prepareStatement(sql);
			
			//execute
			ps.execute();
			
			//close
			ps.close();
		}
		else {
		  // Table does not exist
			System.out.println("Table does not exist");
		}
		
		
		String sql1  = "INSERT INTO roomtable (Type, Number, Size, Assigned_Course) VALUES (?,?,?,?)";
		String sql2  = "INSERT INTO coursetable (Type, Level, Size, Lab_Required, Assigned_Room) VALUES (?,?,?,?,?)";
		
		PreparedStatement ps1 = con1.prepareStatement(sql1);
		
		PreparedStatement ps2 = con2.prepareStatement(sql2);
		
		
		
		
		//Read files
		FileInputStream is1 = new FileInputStream(ROOM_FILE);
		InputStreamReader isr1 = new InputStreamReader(is1, "UTF-8");
		BufferedReader lineRead1 = new BufferedReader(isr1);
		FileInputStream is2 = new FileInputStream(COURSE_FILE);
		InputStreamReader isr2 = new InputStreamReader(is2, "UTF-8");
		BufferedReader lineRead2 = new BufferedReader(isr2);
		
		//instantiate buffered readers
		String lineText1 = null;
		String lineText2 = null;
		
		//instantiate count and batchSize vars
		int count = 0;
		int batchSize = 20;
		
		//while buffered read is reading
		while((lineText1=lineRead1.readLine()) != null) {
			
			
			//split read data into data array and assign vars to indexes
			String[] data1 = lineText1.split(",");
			String rType = data1[0];
			String rNumber = data1[1];
			String rSize = data1[2];
			String rAssignedCourse = data1[3];
			
			//set prepared statement with vars
			ps1.setString(1, rType);
			ps1.setString(2, rNumber);
			ps1.setInt(3, Integer.parseInt(rSize));
			ps1.setString(4, rAssignedCourse);
			ps1.addBatch();
			
			//if done reading
			if(count%batchSize == 0) {
				
				//execute batch
				ps1.executeBatch();
				
			}
			
		}
	
		
	//while buffered reader is reading
	while((lineText2=lineRead2.readLine()) != null) {
		
			//set count back to 0
			count = 0;
			
			//split read data into data array and assign vars to indexes
			String[] data = lineText2.split(",");
			String rType = data[0];
			String rNumber = data[1];
			String rSize = data[2];
			String rLab = data[3];
			String rAssignedCourse = data[4];
			
			ps2.setString(1, rType);
			ps2.setString(2, rNumber);
			ps2.setInt(3, Integer.parseInt(rSize));
			ps2.setString(4, rLab);
			ps2.setString(5, rAssignedCourse);
			ps2.addBatch();
			
			if(count%batchSize == 0) {
				ps2.executeBatch();
				
			}
			
		}
	
	lineRead1.close();
	
	lineRead2.close();
	
	
	closeConnection(con1);
	closeConnection(con2);
	closePreparedStatement(ps1);
	closePreparedStatement(ps2);
	closeResultSet(roomTable);
	closeResultSet(courseTable);
	
	
	}
	
	public static void closeConnection(Connection connection) throws SQLException{
		connection.close();
	}
	public static void closeStatement(Statement statement) throws SQLException{
		statement.close();
	}
	public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.close();
	}
	public static void closeResultSet(ResultSet resultSet) throws SQLException{
		resultSet.close();
	}
}
