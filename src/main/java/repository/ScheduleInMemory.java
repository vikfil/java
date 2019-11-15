package repository;

import model.Group;
import model.Lector;
import model.Lesson;
import java.util.*;
import java.util.stream.Collectors;

public class ScheduleInMemory implements LessonRepository {
    private   List<Lesson> lessons;

    public ScheduleInMemory(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public boolean addLesson(Lesson lesson) {
        if (lesson == null) {
            return false;
        }
        if (lessons.size() == 0) {
            lessons.add(lesson);
            return true;
        }
        for(Lesson les : lessons) {
            if (les.equals(lesson)) {
                return false;
            }
            return equalsDayAndLessonNumber(les, lesson ) ? equalsBetweenLectorSubjectClassroom(les, lesson)
                    ? lessons.add(lesson) : tripleInequality(les, lesson)
                    ? lessons.add(lesson): false
                    : lessons.add(lesson);
        }
        return false;
    }

    @Override
    public List<Lesson> getAllLessons() {
        return new ArrayList<>(lessons);
    }

    @Override
    public List<Lesson> getScheduleForGroup(Group group) {
        if (group == null) {
            throw new IllegalArgumentException();
        }
        return lessons.stream()
                .filter(Lesson -> Lesson.getGroup().equals(group))
                .collect(Collectors.toList());
    }

    @Override
    public List<Lesson> getScheduleForLector(Lector lector) {
        if (lector == null) {
            throw new IllegalArgumentException();
        }
        return lessons.stream()
                .filter(Lesson -> Lesson.getLector().equals(lector))
                .collect(Collectors.toList());
    }

    private boolean equalsDayAndLessonNumber(Lesson lesson1, Lesson lesson2) {
        return (lesson1.getWeekday().equals(lesson2.getWeekday())) && (lesson1.getNumberLesson() == lesson2.getNumberLesson());
    }

    private boolean tripleInequality(Lesson lesson1, Lesson lesson2) {
        return !(lesson1.getLector().equals(lesson2.getLector())) && !(lesson1.getClassroom().equals(lesson2.getClassroom()))
                && !(lesson1.getSubject().equals(lesson2.getSubject()));
    }

    private boolean equalsBetweenLectorSubjectClassroom(Lesson lesson1, Lesson lesson2) {
        return (lesson1.getLector().equals(lesson2.getLector())) && (lesson1.getClassroom().equals(lesson2.getClassroom()))
                && (lesson1.getSubject().equals(lesson2.getSubject()));
    }
}
