package Controller;
import java.sql.SQLException;

import Model.Course;

public interface CourseInterface extends GenericInterface<Course> {
	
	Course assignRoom(String type, String number, String rType, String rNum) throws SQLException;

	Course registerStudents(String type, String level, int students) throws SQLException;

	int getStudents(String type, String number) throws SQLException;
}
