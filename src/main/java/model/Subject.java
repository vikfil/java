package model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Subject {
    @Pattern(message = "Bad formed subject name",
            regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]+\\')?[A-Z][a-z]*)))*$")
    @NotNull
    @Size(min = 5, max = 15)
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
