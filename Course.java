public class Course {
	private String type;
	private String level;
	private int size;
	private String isLab;
	private String assignedRoom;
	
	
	public Course(String type, String level, int size, String isLab, String assignedRoom) {
		this.type = type;
		this.level = level;
		this.size = size;
		this.isLab = isLab;
		this.assignedRoom = assignedRoom;
	}

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getLevel() {
		return level;
	}


	public void setLevel(String level) {
		this.level = level;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}
	
	public String getIsLab() {
		return isLab;
	}
	
	public void setIsLab(String isLab) {
		this.isLab = isLab;
	}
	
	public String getAssignedRoom() {
		return assignedRoom;
	}
	
	public void setAssignedRoom(String assignedRoom) {
		this.assignedRoom = assignedRoom;
	}

	@Override
	public String toString() {
		return "Course Type=" + type + "  Course Level=" + level + "  Course Size=" + size + "  Is Lab=" + isLab + "  Assigned Room=" + assignedRoom + "\n";
	}
	

}
