package modelDto;

public class ClassroomDto {
    private String classroomNumber;
    private String typeRoom;
    private long id;

    public ClassroomDto(){}
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
}
