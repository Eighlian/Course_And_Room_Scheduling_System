package Controller;
import java.sql.SQLException;
import java.util.List;

public interface GenericInterface<T> {
	
	T get(String type, String number) throws SQLException;
	
	List<T> getAll() throws SQLException;
	
	int save(T t) throws SQLException;
	
	int insert(T t) throws SQLException;
	
	int update(T t) throws SQLException;
	
	int delete(T t);
	
	
}
