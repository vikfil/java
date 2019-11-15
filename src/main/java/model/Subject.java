package model;

import java.util.Objects;

public class Subject {
    private String subjectName;
    private long id;

    public Subject(String subjectName, long id) {
        this.subjectName = subjectName;
        this.id = id;
    }
    public Subject(long id) {
        this.id = id;
    }

    public Subject(String subjectName) {
        this.subjectName = subjectName;
    }
    public Subject() {}

    public void setSubjectName(String subjectName) {

        this.subjectName = subjectName;
    }

    public String getSubjectName() {

        return subjectName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectName, id);
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
        Subject other = (Subject) otherObject;
        return  Objects.equals(subjectName, other.subjectName)
                && id == other.id;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectName='" + subjectName + '\'' +
                ", id=" + id +
                '}';
    }
}
