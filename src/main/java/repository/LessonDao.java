package repository;

import model.Group;
import model.Lector;
import model.Lesson;
import java.sql.SQLException;
import java.util.List;


public interface LessonDao {
    boolean addLesson(Lesson lesson) throws SQLException, ClassNotFoundException;
    List<Lesson> getAllLessons() throws SQLException, ClassNotFoundException;
    List<Lesson> getScheduleForGroup(Group group) throws SQLException, ClassNotFoundException;
    List<Lesson> getScheduleForLector(Lector lector) throws SQLException, ClassNotFoundException;
    boolean deleteLessonById(long id) throws SQLException, ClassNotFoundException;

}
