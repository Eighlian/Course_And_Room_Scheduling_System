package Model;
//declare class Room
public class Room {
	//field data
	private String type;
	private String roomNumber;
	private int size;
	private String assignedCourse;
	
	//room constructor
	public Room(String type, String roomNumber, int size, String assignedCourse) {
		this.type = type;
		this.roomNumber = roomNumber;
		this.size = size;
		this.assignedCourse = assignedCourse;
	
	}
	//getter getType
	public String getType() {
		return type;
	}
	//setter setType
	public void setType(String type) {
		this.type = type;
	}
	//getter getRoomNumber
	public String getRoomNumber() {
		return roomNumber;
	}
	//setter setRoomNumber
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	//getter getSize
	public int getSize() {
		return size;
	}
	//setter setSize
	public void setSize(int size) {
		this.size = size;
	}
	//getter getAssignedCourse
	public String getAssignedCourse() {
		return assignedCourse;
	}
	//setter setAssignedCourse
	public void setAssignedCourse(String assignedCourse) {
		this.assignedCourse = assignedCourse;
	}
	//compare method to get Larger Room
	public Room getLargerRoom(Room r) {
		
		if(this.size > r.getSize()) {
		return this;
		}
		
		else {
		return r;
		}
	}
	//override toString method
	@Override
	public String toString() {
		
		return "Room Type=" + type + "  Room Number=" + roomNumber + "  Room Size=" + size + "  Assigned Course= " + assignedCourse + "\n" 
				+ "=========================================================================================="+ "\n";
		
	}
	
	
}
