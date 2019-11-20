package repository;

import model.Classroom;
import java.sql.SQLException;
import java.util.List;

public interface ClassroomDao {
    long addClassroom(Classroom classroom) throws SQLException, ClassNotFoundException;
    List<Classroom> allClassrooms();
    Classroom classroomById(long id) throws SQLException, ClassNotFoundException;
    boolean updateClassroom (Classroom classroom) throws SQLException, ClassNotFoundException;
    boolean deleteClassroomById (long id) throws SQLException, ClassNotFoundException;
}
