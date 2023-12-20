import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.exceptions.RSAException;

public class CourseController implements CourseInterface {

	@Override
	public Course get(String type, String number) throws SQLException {
		try {
			Connection con = Database.getConnection2();
			Course course= null;
			
			String sql = "SELECT Type, Level, Size, Lab_Required, Assigned_Room FROM coursetable WHERE Type = ? AND Level = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, type);
			ps.setString(2, number);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String courseType = rs.getString("Type");
				String courseLevel = rs.getString("Level");
				int courseSize = rs.getInt("Size");
				String isLab = rs.getString("Lab_Required");
				String assignedRoom = rs.getString("Assigned_Room");
				
				course = new Course(courseType, courseLevel, courseSize, isLab, assignedRoom);
			}
			
			Database.closeResultSet(rs);
			Database.closePreparedStatement(ps);
			Database.closeConnection(con);
			
			return course;
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int getStudents(String type, String number) throws SQLException {
		int courseStudents = 0;
		
		try {
			//create connection to database
			Connection con = Database.getConnection2();
			
			Course course= null;
			
			String sql = "SELECT Type, Level, Size, Lab_Required, Assigned_Room FROM coursetable WHERE Type = ? AND Level = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, type);
			ps.setString(2, number);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String courseType = rs.getString("Type");
				String courseLevel = rs.getString("Level");
				int courseSize = rs.getInt("Size");
				String isLab = rs.getString("Lab_Required");
				String assignedRoom = rs.getString("Assigned_Room");
				
				course = new Course(courseType, courseLevel, courseSize, isLab, assignedRoom);
				courseStudents = courseSize;
			}
			
			Database.closeResultSet(rs);
			Database.closePreparedStatement(ps);
			Database.closeConnection(con);
			
			
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseStudents;
	}


	@Override
	public List<Course> getAll() throws SQLException {
		
		//create connection to database
		Connection con = Database.getConnection2();
		String sql = "SELECT Type, Level, Size, Lab_Required, Assigned_Room FROM coursetable";
		
		List<Course> courses = new ArrayList<>();
		
		Statement s = con.createStatement();
		
		ResultSet rs = s.executeQuery(sql);
		
		while(rs.next()) {
			
			String courseType = rs.getString("Type");
			String courseLevel = rs.getString("Level");
			int courseSize = rs.getInt("Size");
			String isLab = rs.getString("Lab_Required");
			String assignedRoom = rs.getString("Assigned_Room");
		
			
			Course course = new Course(courseType, courseLevel, courseSize, isLab, assignedRoom);
			
			courses.add(course);
			
		}
		Database.closeConnection(con);
		Database.closeStatement(s);
		Database.closeResultSet(rs);
		return courses;
	}

	@Override
	public int save(Course course) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Course course) throws SQLException {
		
		//create connection to database
		Connection con = Database.getConnection2();
		String sql  = "INSERT INTO coursetable (Type, Level, Size, Lab_Required, Assigned_Room) VALUES (?,?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, course.getType());
		ps.setString(2, course.getLevel());
		ps.setInt(3, course.getSize());
		ps.setString(4, course.getIsLab());
		ps.setString(5, course.getAssignedRoom());
		
		
		int result = ps.executeUpdate();
		
		Database.closePreparedStatement(ps);
		Database.closeConnection(con);
		
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
		
		Course course = null;
		String sql = "SELECT Type, Level, Size, Lab_Required, Assigned_Room FROM coursetable WHERE Type = ? and Level = ?";
		
		PreparedStatement ps2 = con2.prepareStatement(sql);
		
		ps2.setString(1, type);
		ps2.setString(2, level);
		
		ResultSet rs = ps2.executeQuery();
	
		
		if(rs.next()) {
			
		String courseType = rs.getString("Type");
		String courseLevel = rs.getString("Level");
		int courseSize = rs.getInt("Size");
		String isLab = rs.getString("Lab_Required");
		String assignedRoom = rs.getString("Assigned_Room");
		
		course = new Course(courseType, courseLevel, courseSize, isLab, assignedRoom);
		
			//if course is assigned a room
			if(!assignedRoom.equalsIgnoreCase("none")) {
				
				Room room = null;
				sql = "SELECT Type, Number, Size, Assigned_Course FROM roomtable WHERE Type= ? AND Number = ?";
				
				PreparedStatement ps1 = con1.prepareStatement(sql);
				
				String str = assignedRoom;
				String[] s = str.split(" ");
				
				ps1.setString(1, s[0]);
				ps1.setString(2, s[1]);
				
				
				rs = ps1.executeQuery();
				
				
				
				
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
