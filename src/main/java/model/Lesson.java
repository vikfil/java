package model;

import java.util.Objects;


public class Lesson {
    private long id;
    private Week weekday;
    private int numberLesson;
    private Subject subject;
    private Group group;
    private Lector lector;
    private Classroom classroom;

    public Lesson(){}

    public Lesson(Week weekday, int numberLesson, Subject subject, Group group, Lector lector, Classroom classroom) {
        this.weekday = weekday;
        this.numberLesson = numberLesson;
        this.subject = subject;
        this.group = group;
        this.lector = lector;
        this.classroom = classroom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Week getWeekday() {
        return weekday;
    }

    public void setWeekday(Week weekday) {
        this.weekday = weekday;
    }

    public int getNumberLesson() {
        return numberLesson;
    }

    public void setNumberLesson(int numberLesson) {
        this.numberLesson = numberLesson;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weekday, numberLesson, subject, group, lector, classroom);
    }

    @Override
    public boolean equals(Object otherObject) {
        if(this == otherObject) {
            return true;
        }
        if (otherObject == null) {
            return false;
        }

        if (getClass() != otherObject.getClass()) {
            return false;
        }
        Lesson other = (Lesson) otherObject;
        return Objects.equals(weekday, other.weekday) &&
                numberLesson == other.numberLesson &&
                Objects.equals(subject, other.subject) &&
                Objects.equals(group, other.group) &&
                Objects.equals(lector, other.lector) &&
                Objects.equals(classroom, other.classroom);
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "weekday=" + weekday +
                ", numberLesson=" + numberLesson +
                ", subject=" + subject +
                ", group=" + group +
                ", lector=" + lector +
                ", classroom=" + classroom +
                '}';
    }
}
