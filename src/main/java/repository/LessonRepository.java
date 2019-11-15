package repository;

import model.Group;
import model.Lector;
import model.Lesson;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface LessonRepository {
    boolean addLesson(Lesson lesson);
    List<Lesson> getAllLessons();
    List<Lesson> getScheduleForGroup(Group group);
    List<Lesson> getScheduleForLector(Lector lector);

}
