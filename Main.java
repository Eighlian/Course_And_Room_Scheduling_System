package View;
import Controller.*;
import Model.*;

import java.util.Scanner;

public class Main {
	
	
	public static String user = "username";
	public static String userPassword = "password";
	public static boolean adminStatus = false;
	public static boolean loginSucessful = false;
	
	
	
		public static void main(String[] args) throws Exception {
			
		Database.loadDatabase();
		
		
		//test Display room information

		//test display number of students

		//test generate report

		//Test assign course to room

		//test assign students to room with course

		//test assign students to room with course and course moving 

		//test assigning lab to room

		//test assigning room to lab

		//test assigning course to room that already has room
		
		//test assigning room to course that already has course

		//test if room is invalid room
		
		

		//test if course is in valid course
		
		
		Scanner input = new Scanner(System.in);
		
		boolean running = true;
		
		System.out.println("Welcome to Schedule Program!");
		System.out.println("Enter login type. (Admin or User), or enter 'Quit' to quit.\n");				
				
		String userInput = input.nextLine();	// gets user input for login type
		
		while(running)
		{
			
		
		if(userInput.equalsIgnoreCase("Quit")) {
			System.out.println("Software Exiting");
			break;
		}
		
		else if(userInput.equalsIgnoreCase("User")) {
			while(running) {
			RoomController roomController = new RoomController();
			CourseController courseController = new CourseController();
			
			System.out.println("Please choose an option\n");
			System.out.println("1: Display Room Information");
			System.out.println("2: Display Number of Students in Course");
			System.out.println("3: Generate report");
			System.out.println("4: Quit");
			
			userInput = input.next();
			input.nextLine();
			switch(userInput) {
				case "1":
					System.out.println(roomController.getAll());
					
					break;
					
				case "2":
					System.out.println("Please enter the Course Type\n");
					String courseType = input.next();
					System.out.println("Please enter the Course Level\n");
					String courseLevel = input.next();
					
					System.out.println("Number of students in "+ courseType + " "+ courseLevel + " = " +courseController.getStudents(courseType, courseLevel) + "\n");
					
					break;
				case "3":
					System.out.println(roomController.getAll());
					System.out.println(courseController.getAll());
					break;
				case "4":
					System.out.println("Software Exiting");
					running = false;
					break;
					
				default:
					System.out.println("Please enter a correct choice");
					break;
					
			}
		}
		}
		else if(userInput.equalsIgnoreCase("Admin")) {
			
			System.out.println("Please Enter User Name\n");
			userInput = input.next();
			input.nextLine();
			
		}
		
		
		if(userInput.equals(user)) {
					
					
					System.out.println("Please Enter Password\n");
					userInput = input.next();
					
		}
		
		if(userInput.equals(userPassword)) {
				while(running) {
							RoomController roomController = new RoomController();
							CourseController courseController = new CourseController();
							
							//display options
							System.out.println("Please choose an option\n");
							System.out.println("1: Display Room Information");
							System.out.println("2: Display Number of Students in Course");
							System.out.println("3: Generate report");
							System.out.println("4: Add students to course");
							System.out.println("5: Add a room");
							System.out.println("6: Add a course");
							System.out.println("7: Assign a Course to a Room");
							System.out.println("8: Quit");
							
							//grab userinput
							userInput = input.next();
							
							//switch case based on user input
							switch(userInput) {
							
								//case 1 Display room info
								case "1":
									System.out.println(roomController.getAll());	
									break;
									
								//case 2 Display Number of Students in Course
								case "2":
									System.out.println("Please enter the Course Type\n");
									String courseType = input.next();
									System.out.println("Please enter the Course Level\n");
									String courseLevel = input.next();
									
									System.out.println("Number of students in "+ courseType + " "+ courseLevel + " = " + courseController.getStudents(courseType, courseLevel) + "\n");
									
									
									break;
								
								//case 3 Generate report
								case "3":
									System.out.println(roomController.getAll());
									System.out.println(courseController.getAll());
									break;
									
								//case 4 Add students to course
								case "4":
									System.out.println("Please enter the Course Type\n");
									courseType = input.next();
									
									System.out.println("Please enter the Course Level\n");
									courseLevel = input.next();
									
									System.out.println("Please enter the Number of Student to Add\n");
									int numberOfStudents = input.nextInt();
									
									courseController.registerStudents(courseType, courseLevel, numberOfStudents);
									break;
								case "5":
									
									Room r;
									System.out.println("Please enter the Room Type\n");
									String roomType = input.next();
									while(!roomType.equalsIgnoreCase("Lab") || !roomType.equalsIgnoreCase("Room")) {
										System.out.println("Please insert a valid type.");
										roomType = input.next();
										if(roomType.equalsIgnoreCase("Lab")|| roomType.equalsIgnoreCase("Room")) {
											break;
										}
									}
									
									System.out.println("Please enter the Room Number\n");
									String roomNumber = input.next();
									
									System.out.println("Please enter the Room Size\n");
									int roomSize = input.nextInt();
									
									
									r = new Room(roomType, roomNumber, roomSize, "none");
									roomController.insert(r);
									break;
									
									
								case "6":
									
									Course c;
									
									
									System.out.println("Please enter the Course Type\n");
									courseType = input.next();
									
									System.out.println("Please enter the Course Level\n");
									courseLevel = input.next();
									
									System.out.println("Please enter the Course Size\n");
									int studentNumber = input.nextInt();
									
									System.out.println("Is this class a Lab?");
									String isLab = input.next();
									
									
									c = new Course(courseType, courseLevel, studentNumber, isLab, "none");
									courseController.insert(c);
									break;
									
									
								case "7":
									
									System.out.println("Please enter the Course Type\n");
									courseType = input.next();
									
									System.out.println("Please enter the Course Level\n");
									courseLevel = input.next();
									
									System.out.println("Please enter the Room Type(Lab/Room)\n");
									roomType = input.next();
									
									System.out.println("Please enter the Room Number\n");
									roomNumber = input.next();
									
									courseController.assignRoom(courseType, courseLevel, roomType, roomNumber);
									
									break;
									
									
								//Display exit software and set running to false
								case "8":
									System.out.println("Software Exiting");
									running = false;
									break;
								
								//Display to enter correct choice
								default:
									System.out.println("Please enter a correct choice");
									break;
									
							}
				}
		}
	}
}
		
			
			
			
			
			
		
}
