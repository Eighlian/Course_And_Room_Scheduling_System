package Controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Database;
import Model.Room;

public class RoomController implements RoomInterface {

	@Override
	public Room get(String type, String number) throws SQLException {
		
		try {
			Connection con = Database.getConnection1();
			Room room= null;
			
			String sql = "SELECT Type, Number, Size, Assigned_Course FROM roomtable WHERE Type = ? AND Number = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, number);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String roomType = rs.getString("Type");
				String roomNumber = rs.getString("Number");
				int roomSize = rs.getInt("Size");
				String assignedCourse = rs.getString("Assigned_Course");
				room = new Room(roomType, roomNumber, roomSize, assignedCourse);
			}
			
			Database.closeResultSet(rs);
			Database.closePreparedStatement(ps);
			Database.closeConnection(con);
			
			return room;
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	@Override
	public List<Room> getAll() throws SQLException {
	
		Connection con = Database.getConnection1();
		String sql = "SELECT Type, Number, Size, Assigned_Course FROM roomtable";
		
		List<Room> rooms = new ArrayList<>();
		
		Statement s = con.createStatement();
		
		ResultSet rs = s.executeQuery(sql);
		
		while(rs.next()) {
			
			String roomType = rs.getString("Type");
			String roomNumber = rs.getString("Number");
			int roomSize = rs.getInt("Size");
			String assignedCourse = rs.getString("Assigned_Course");
			
			Room room = new Room(roomType, roomNumber, roomSize, assignedCourse);
			
			rooms.add(room);
			
		}
		Database.closeConnection(con);
		Database.closeStatement(s);
		Database.closeResultSet(rs);
		
		return rooms;
		
		
	}

	@Override
	public int save(Room room) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Room room) throws SQLException {
		Connection con = Database.getConnection1();
		String sql  = "INSERT INTO roomtable (Type, Number, Size, Assigned_Course) VALUES (?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, room.getType());
		ps.setString(2, room.getRoomNumber());
		ps.setInt(3, room.getSize());
		ps.setString(4, room.getAssignedCourse());
		
		int result = ps.executeUpdate();
		
		Database.closePreparedStatement(ps);
		Database.closeConnection(con);
		
		return result;
	}


	@Override
	public int delete(Room room) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int update(Room t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
