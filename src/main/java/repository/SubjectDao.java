package repository;

import model.Subject;
import java.sql.SQLException;
import java.util.List;

public interface SubjectDao {
    long addSubject(Subject subject) throws SQLException, ClassNotFoundException;
    List<Subject> allSubjects();
    Subject subjectById(long id) throws SQLException, ClassNotFoundException;
    boolean updateSubject (Subject subject) throws SQLException, ClassNotFoundException;
    boolean deleteSubjectById (long id) throws SQLException, ClassNotFoundException;
}
