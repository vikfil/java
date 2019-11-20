package modelDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

public class ClassroomDto {
    @Valid
    @Pattern(message = "Bad formed classroom number", regexp = "[0-9a-zA-Z\\.\\-]{1,5}")
    @Size(max = 5)
    @NotNull
    private String classroomNumber;

//TODO
//    @Valid
//    @Pattern(message = "Bad formed classroom type", regexp = "[0-9a-zA-Z\\.\\-]{1,15}")
//    @Size(max = 15)
    private String typeRoom;
    private long id;

    public ClassroomDto(){}

    public ClassroomDto(String classroomNumber) {
        this.classroomNumber = classroomNumber;
    }

    public ClassroomDto( String classroomNumber, String typeRoom) {
        this.classroomNumber = classroomNumber;
        this.typeRoom = typeRoom;
    }

    public ClassroomDto(long id) {
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
    public String toString() {
        if (typeRoom == null) {
            return classroomNumber;
        }
        return classroomNumber + " " + typeRoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassroomDto that = (ClassroomDto) o;
        return id == that.id &&
                classroomNumber.equals(that.classroomNumber) &&
                Objects.equals(typeRoom, that.typeRoom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classroomNumber, typeRoom, id);
    }
}
