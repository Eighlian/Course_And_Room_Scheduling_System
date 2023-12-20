package Controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.Course;
import Model.Database;
import Model.Room;

//declare Course controller class
public class CourseController implements CourseInterface {

	//method to get course object from parameters passed in
	@Override
	public Course get(String type, String number) throws SQLException {
		try {
			
			//create connection
			Connection con = Database.getConnection2();
			
			//Instantiate course
			Course course= null;
			
			//create sql statement
			String sql = "SELECT Type, Level, Size, Lab_Required, Assigned_Room FROM coursetable WHERE Type = ? AND Level = ?";
			
			//create prepared statement
			PreparedStatement ps = con.prepareStatement(sql);
			
			//set prepared statement vars
			ps.setString(1, type);
			ps.setString(2, number);
			
			//create result set and assign ps excuted query
			ResultSet rs = ps.executeQuery();
			
			//if result set has next do
			if(rs.next()) {
				
				//create parameters
				String courseType = rs.getString("Type");
				String courseLevel = rs.getString("Level");
				int courseSize = rs.getInt("Size");
				String isLab = rs.getString("Lab_Required");
				String assignedRoom = rs.getString("Assigned_Room");
				
				
				//create course object with parameters
				course = new Course(courseType, courseLevel, courseSize, isLab, assignedRoom);
			}
			
			//close
			Database.closeResultSet(rs);
			Database.closePreparedStatement(ps);
			Database.closeConnection(con);
			
			//return course
			return course;
		
			//catch
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		//return
		return null;
	}
	//method to get number of students in course
	@Override
	public int getStudents(String type, String number) throws SQLException {
		int courseStudents = 0;
		
		//try
		try {
			
			//create connection to database
			Connection con = Database.getConnection2();
			
			//Instantiate course
			Course course= null;
			
			//create sql statement
			String sql = "SELECT Type, Level, Size, Lab_Required, Assigned_Room FROM coursetable WHERE Type = ? AND Level = ?";
			
			//create prepared statement
			PreparedStatement ps = con.prepareStatement(sql);
			
			//set ps vars
			ps.setString(1, type);
			ps.setString(2, number);
			
			//create result set
			ResultSet rs = ps.executeQuery();
			
			
			//if result set has next do
			if(rs.next()) {
				
				//create parameters
				String courseType = rs.getString("Type");
				String courseLevel = rs.getString("Level");
				int courseSize = rs.getInt("Size");
				String isLab = rs.getString("Lab_Required");
				String assignedRoom = rs.getString("Assigned_Room");
				
				//create course object
				course = new Course(courseType, courseLevel, courseSize, isLab, assignedRoom);
				
				//set number of students to coursesize
				courseStudents = courseSize;
			}
			
			//close
			Database.closeResultSet(rs);
			Database.closePreparedStatement(ps);
			Database.closeConnection(con);
			
			
		
		//catch
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		//return number of students
		return courseStudents;
	}

	//method to get all courses
	@Override
	public List<Course> getAll() throws SQLException {
		
		//create connection to database
		Connection con = Database.getConnection2();
		
		//create sql query
		String sql = "SELECT Type, Level, Size, Lab_Required, Assigned_Room FROM coursetable";
		
		//create courses list
		List<Course> courses = new ArrayList<>();
		
		//create statement
		Statement s = con.createStatement();
		
		//create result set and execute query sql
		ResultSet rs = s.executeQuery(sql);
		
		//while result set has next do
		while(rs.next()) {
			
			//create vars for result set
			String courseType = rs.getString("Type");
			String courseLevel = rs.getString("Level");
			int courseSize = rs.getInt("Size");
			String isLab = rs.getString("Lab_Required");
			String assignedRoom = rs.getString("Assigned_Room");
		
			
			//create course object
			Course course = new Course(courseType, courseLevel, courseSize, isLab, assignedRoom);
			
			//add course to list
			courses.add(course);
			
		
		}
		
		//close
		Database.closeConnection(con);
		Database.closeStatement(s);
		Database.closeResultSet(rs);
		
		
		//return courses list
		return courses;
	}

	@Override
	public int save(Course course) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//insert method to insert/add course to database
	@Override
	public int insert(Course course) throws SQLException {
		
		//create connection to database
		Connection con = Database.getConnection2();
		String sql  = "INSERT INTO coursetable (Type, Level, Size, Lab_Required, Assigned_Room) VALUES (?,?,?,?,?)";
		
		//create prepared statement
		PreparedStatement ps = con.prepareStatement(sql);
		
		//set prepared statement vars
		ps.setString(1, course.getType());
		ps.setString(2, course.getLevel());
		ps.setInt(3, course.getSize());
		ps.setString(4, course.getIsLab());
		ps.setString(5, course.getAssignedRoom());
		
		//create result var and update prepared statement
		int result = ps.executeUpdate();
		
		//close
		Database.closePreparedStatement(ps);
		Database.closeConnection(con);
		
		//return result
		return result;
	}

	@Override
	public int delete(Course course) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Course t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	//method to add/register students
	@Override
	public Course registerStudents(String type, String level, int students) throws SQLException {
		
		//create connection to database
		Connection con1 = Database.getConnection1();
		Connection con2 = Database.getConnection2();
		
		//Instantiate course object
		Course course = null;
		String sql = "SELECT Type, Level, Size, Lab_Required, Assigned_Room FROM coursetable WHERE Type = ? and Level = ?";
		
		//create prepared statement
		PreparedStatement ps2 = con2.prepareStatement(sql);
		
		//set statement
		ps2.setString(1, type);
		ps2.setString(2, level);
		
		//create result set
		ResultSet rs = ps2.executeQuery();
	
		//move pointer if result set has next do
		if(rs.next()) {
		
		//create vars for result set
		String courseType = rs.getString("Type");
		String courseLevel = rs.getString("Level");
		int courseSize = rs.getInt("Size");
		String isLab = rs.getString("Lab_Required");
		String assignedRoom = rs.getString("Assigned_Room");
		
		//create new object with parameters
		course = new Course(courseType, courseLevel, courseSize, isLab, assignedRoom);
		
			//if course is assigned a room
			if(!assignedRoom.equalsIgnoreCase("none")) {
				
				Room room = null;
				sql = "SELECT Type, Number, Size, Assigned_Course FROM roomtable WHERE Type= ? AND Number = ?";
				
				PreparedStatement ps1 = con1.prepareStatement(sql);
				
				//split assigned room and set
				String str = assignedRoom;
				String[] s = str.split(" ");
				
				ps1.setString(1, s[0]);
				ps1.setString(2, s[1]);
				
				//execute ps1 query
				rs = ps1.executeQuery();
				
				
				
				//move pointer if result set has next do
				if(rs.next()) {
					
					
					String roomType = rs.getString("Type");
					String roomNum = rs.getString("Number");
					int roomSize = rs.getInt("Size");
					String assignedCourse = rs.getString("Assigned_Course");
					
					//create room object
					room = new Room(roomType, roomNum, roomSize, assignedCourse);
					
				//if students added to course size is less than or equal to size
				if((students + courseSize)<=roomSize) {
					
				
					//update course table and set course size in data base 
					sql = "UPDATE coursetable SET Size = ? WHERE Type = ? AND Level = ?";
					ps2 = con2.prepareStatement(sql);
					ps2.setInt(1, students+courseSize);
					ps2.setString(2, course.getType());
					ps2.setString(3, course.getLevel());
					ps2.executeUpdate();
					
				
				}
				//else find course is too big for room find a suitable room and move 
				else {
						//create room controller
						RoomInterface roomControl = new RoomController();
						
						//create rooms list
						List<Room> rooms = roomControl.getAll();
						
						//search rooms list
						for(Room r : rooms) {
							
							//if assigned room doesnt have an assigned course and size is suitable
							if((r.getAssignedCourse().equalsIgnoreCase("none"))&& ((students+course.getSize()) <= r.getSize())) {
							
							//create bigger room and use method to compare room with r
							Room biggerRoom = room.getLargerRoom(r);
							
							
							//if bigger room is greater than original room and bigger room matches type of course
							if((biggerRoom.getSize() > room.getSize()) &&(biggerRoom.getType().equalsIgnoreCase("Lab")  && course.getIsLab().equalsIgnoreCase("Yes")) || (biggerRoom.getType().equalsIgnoreCase("Room") && course.getIsLab().equalsIgnoreCase("No"))) {
								
							
							
							//update course table 
							sql = "UPDATE coursetable SET Assigned_Room = ? WHERE Type = ? AND Level = ?";
							ps2 = con2.prepareStatement(sql);
							ps2.setString(1, "none");
							ps2.setString(2, course.getType());
							ps2.setString(3, course.getLevel());
							ps2.executeUpdate();
							
							//update room table 
							sql = "UPDATE roomtable SET Assigned_Course = ? WHERE Type = ? AND Number = ?";
							ps1 = con1.prepareStatement(sql);
							ps1.setString(1, "none");
							ps1.setString(2, room.getType());
							ps1.setString(3, room.getRoomNumber());
							ps1.executeUpdate();
							
							
							////update course table 
							sql = "UPDATE coursetable SET Assigned_Room = ? WHERE Type = ? AND Level = ?";
							ps2= con2.prepareStatement(sql);
							ps2.setString(1, biggerRoom.getType() + " " + biggerRoom.getRoomNumber());
							ps2.setString(2, course.getType());
							ps2.setString(3, course.getLevel());
							ps2.executeUpdate();
							
							
							//update room table 
							sql = "UPDATE roomtable SET Assigned_Course = ? WHERE Type = ? AND Number = ?";
							ps1 = con1.prepareStatement(sql);
							ps1.setString(1, course.getType() + " " + course.getLevel());
							ps1.setString(2, biggerRoom.getType());
							ps1.setString(3, biggerRoom.getRoomNumber());
							ps1.executeUpdate();
							
							//close ps1
							Database.closePreparedStatement(ps1);
							
							//display that a room was found
							System.out.println("Room was found\n");
							
							//break out of loop
							break;	
							
							}
							
							}
							//else room was not found
							else {
								
								System.out.println("No room was found\n");
								
							}
							
						}
					//display that system is moving room.
					System.out.println("Course size is too big for room. Moving to bigger room.\n");
				}
			}
			}
			
			//Course has no assigned room and admin can add as many students they would like
			else {
					sql = "UPDATE coursetable SET Size = ? WHERE Type = ? AND Level = ?";
					PreparedStatement ps = con2.prepareStatement(sql);
					ps.setInt(1, students+courseSize);
					ps.setString(2, course.getType());
					ps.setString(3, course.getLevel());
					ps.executeUpdate();
					
				
			}
			
			//Close 
			Database.closeConnection(con1);
			Database.closeConnection(con2);
			Database.closePreparedStatement(ps2);
			Database.closeResultSet(rs);
			
			//return course
			return course;
		}
		
		//return course
		return course;
		
		
	}
	//method to assign course to rooms
	@Override
	public Course assignRoom(String type, String level, String rType, String rNum) throws SQLException {
		
		//create connection to database
		Connection con1 = Database.getConnection1();
		Connection con2 = Database.getConnection2();
		
		//create sql statement 
		String sql = "SELECT Type, Level, Size, Lab_Required, Assigned_Room FROM coursetable WHERE Type = ? and Level = ?";
		
		//create prepared statemment
		PreparedStatement ps2 = con2.prepareStatement(sql);
		
		//set ps2 var
		ps2.setString(1, type);
		ps2.setString(2, level);
		
		//create result set and execute query
		ResultSet rs1 = ps2.executeQuery();
	
		//create room object
		Room room = null;
		
		//create course object
		Course course = null;
		
		sql = "SELECT Type, Number, Size, Assigned_Course FROM roomtable WHERE Type = ? and Number = ?";
		
		PreparedStatement ps1 = con1.prepareStatement(sql);
		
		ps1.setString(1, rType);
		ps1.setString(2, rNum);
		
		//create result set
		ResultSet rs2 = ps1.executeQuery();
	
		//if result move cursor through rows
		if(rs1.next() && rs2.next()) {
		
			
		//assign variables	
		String courseType = rs1.getString("Type");
		String courseLevel = rs1.getString("Level");
		int courseSize = rs1.getInt("Size");
		String isLab = rs1.getString("Lab_Required");
		String assignedRoom = rs1.getString("Assigned_Room");
		
		//create course object with parameters
		course = new Course(courseType, courseLevel, courseSize, isLab, assignedRoom);
		
		//assign variables
		String roomType = rs2.getString("Type");
		String roomNum = rs2.getString("Number");
		int roomSize = rs2.getInt("Size");
		String assignedCourse = rs2.getString("Assigned_Course");
		
		//create room object with parameters
		room = new Room(roomType, roomNum, roomSize, assignedCourse);
		
		//if course has no assigned room and room has no assigned course
		if(assignedRoom.equalsIgnoreCase("none") && assignedCourse.equalsIgnoreCase("none")){	
			
			//if type of course matches room type
			if((isLab.equalsIgnoreCase("Yes") && rType.equalsIgnoreCase("Lab")) || (isLab.equalsIgnoreCase("No") && rType.equalsIgnoreCase("Room"))) {
				
				//if course size is less that or equal to room size
				if(course.getSize()<=room.getSize()) {
					
						//update and assign room to course
						sql = "UPDATE coursetable SET Assigned_Room = ? WHERE Type = ? AND Level = ?";
						ps2 = con2.prepareStatement(sql);
						ps2.setString(1, rType + " " + rNum);
						ps2.setString(2, course.getType());
						ps2.setString(3, course.getLevel());
						ps2.executeUpdate();
						
						//update adn assign course to room
						sql = "UPDATE roomtable SET Assigned_Course = ? WHERE Type = ? AND Number = ?";
						ps1 = con1.prepareStatement(sql);
						ps1.setString(1, type + " " + level);
						ps1.setString(2, room.getType());
						ps1.setString(3, room.getRoomNumber());
						ps1.executeUpdate();
						
						
						
				return course;
				}
				//else display course was too large for room
				else {
					System.out.println("Course is too Large for Room\n");
				}
			}
			//else display course does not match type
			else {
				System.out.println("Course does not match type (Lab/Regular)\n");
			}
			}
		//else display course has already be assigned a room
		else {
			System.out.println("Course has already be assigned a room\n");
		}
		}
		//else display no valid course or room
		else {
			System.out.println("No valid course or room\n");
		}
		
		//close
		Database.closePreparedStatement(ps1);
		Database.closePreparedStatement(ps2);
		Database.closeResultSet(rs1);
		Database.closeResultSet(rs2);
		Database.closeConnection(con1);
		Database.closeConnection(con2);
		
		//return course
		return course;
	}

}
