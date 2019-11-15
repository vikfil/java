package model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Classroom {
    private String classroomNumber;
    private String typeRoom;
    private long id;

    public Classroom() {}
    public Classroom(String classroomNumber) {
        this.classroomNumber = classroomNumber;
    }

    public Classroom(String classroomNumber, String typeRoom) {
        this.classroomNumber = classroomNumber;
        this.typeRoom = typeRoom;
    }

    public Classroom(String classroomNumber, String typeRoom, long id) {
        this.classroomNumber = classroomNumber;
        this.typeRoom = typeRoom;
        this.id = id;
    }

    public String getClassroomNumber() {
        return classroomNumber;
    }

    public void setClassroomNumber(String classroomNumber) {
        this.classroomNumber = classroomNumber;
    }

    public String getTypeRoom() {
        return typeRoom;
    }

    public void setTypeRoom(String typeRoom) {
        this.typeRoom = typeRoom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(classroomNumber, typeRoom, id);
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
        Classroom other = (Classroom) otherObject;
        return Objects.equals(classroomNumber, other.classroomNumber)
                && Objects.equals(typeRoom, other.typeRoom)
                && id == other.id;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "classroomNumber='" + classroomNumber + '\'' +
                ", typeRoom='" + typeRoom + '\'' +
                ", id=" + id +
                '}';
    }

//    private boolean limitationClassFields(String str) {
//        if(str == null || str.isEmpty()) {
//            return false;
//        }
//        if(str.length() > 15) {
//            return false;
//        }
//        return Pattern.matches("\\w", str);
//    }
}
