package Model;
//declare course class
public class Course {
	//field data
	private String type;
	private String level;
	private int size;
	private String isLab;
	private String assignedRoom;
	
	//course constructor
	public Course(String type, String level, int size, String isLab, String assignedRoom) {
		this.type = type;
		this.level = level;
		this.size = size;
		this.isLab = isLab;
		this.assignedRoom = assignedRoom;
	}
	//getter getType
	public String getType() {
		return type;
	}
	//setter setType
	public void setType(String type) {
		this.type = type;
	}

	//getter getLevel
	public String getLevel() {
		return level;
	}

	//setter setLevel
	public void setLevel(String level) {
		this.level = level;
	}
	//getter getSize
	public int getSize() {
		return size;
	}
	//setter setSize
	public void setSize(int size) {
		this.size = size;
	}
	//getter getIsLab
	public String getIsLab() {
		return isLab;
	}
	//setter setIsLab
	public void setIsLab(String isLab) {
		this.isLab = isLab;
	}
	//getter getAssignedRoom
	public String getAssignedRoom() {
		return assignedRoom;
	}
	//setter setAssignedRoom
	public void setAssignedRoom(String assignedRoom) {
		this.assignedRoom = assignedRoom;
	}
	//override toString method
	@Override
	public String toString() {
		return "Course Type=" + type + "  Course Level=" + level + "  Course Size=" + size 
				+ "  Is Lab=" + isLab + "  Assigned Room=" + assignedRoom + "\n" 
				+ "=========================================================================================="+ "\n";
	}
	

}
