package View;

import Model.Database;

public class TestDriver {
	
	//main method
	public static void main(String[] args) throws Exception {
		
	//load database
	Database.loadDatabase();
	
	//TEST CASES
	//RoomController rc =  new RoomController();
	//CourseController cc = new CourseController();
	
	
	/*
	 *  //test Display room information
	 *	System.out.println(rc.getAll()); //displays correct
	*/
	
	
	
	/*
	 *  //test display number of students
	 *	System.out.println("Number of students in COSC 1173 = " + cc.getStudents("COSC", "1173"));  //displays correct
	 *  System.out.println("Number of students in COSC 1173 = " + cc.getStudents("COSC", "11"));	//displays course does not exists
	*/
	
	
	
	/*
	 * //test generate report
	 *	System.out.println(rc.getAll()); //displays correct
	 *	System.out.println(cc.getAll()); //displays correct
	*/
	
	 	/*//Test assign course to room restraints
	    * cc.assignRoom("COSC", "2375", "Lab", "213"); // assigns course type(lab) to lab 213
		* cc.assignRoom("COSC", "2372", "Room", "111"); // assigns course type(no lab) to room 111
		* cc.assignRoom("COSC", "2375", "Lab", "215"); //tries to assign room but course already has assigned room and displays Course has already been or room is filled
		* cc.assignRoom("COSC", "2372", "Lab", "213"); //tries to assign room  but room is filled and displays Course has already been or room is filled
		* cc.assignRoom("COSC", "2375", "Lab", "211"); // does not assign room and displays no valid course or room (Lab 211) is not a valid room
		* cc.assignRoom("COSC", "1337", "Lab", "212B"); //tries to assign course that does not require lab to lab 212B
		* cc.assignRoom("MATH", "2414", "Room", "111"); //tries to assign course that requires lab to room 111
		* cc.assignRoom("ENGL", "2302", "Room", "108"); //tries to assign course that is too large into smaller room
		* cc.assignRoom("ENGL", "123", "Room", "108"); //tries to assign invalid course to valid room
		* cc.assignRoom("ENGL", "2302", "Room", "10"); //tries to assign course to invalid room
		*/
	
	
		/*//test assign students to room with course
		* cc.registerStudents("COSC", "2375", 1);		//add students to course without room
		* System.out.println(cc.get("COSC", "2375"));
		* 
		* cc.assignRoom("COSC", "2375", "Lab", "213");
		* cc.registerStudents("COSC", "2375", 2);		//add students to course with room
		* System.out.println(cc.get("COSC", "2375"));
		* 
		* cc.registerStudents("COSC", "2375", 6);		//add students to course within restraints equals class max size
		* System.out.println(cc.get("COSC", "2375"));
		*
		* cc.registerStudents("COSC", "2375", 1);		//add students to course out of restraints moves to bigger room
		* System.out.println(cc.get("COSC", "2375"));
		*
		* cc.registerStudents("COSC", "2375", 1);		//add students to course in bigger room
		* System.out.println(cc.get("COSC", "2375"));
		*/
	}
}
