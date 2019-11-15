package mapper;

import model.Classroom;
import modelDto.ClassroomDto;

import java.util.List;
import java.util.stream.Collectors;

public class ClassroomMapper {
    public static ClassroomDto classroomToClassroomDto(Classroom classroom) {
        ClassroomDto classroomDto = new ClassroomDto();
        classroomDto.setId(classroom.getId());
        classroomDto.setClassroomNumber(classroom.getClassroomNumber());
        classroomDto.setTypeRoom(classroom.getTypeRoom());
        return classroomDto;
    }

    public static Classroom classroomDtoToClassroom(ClassroomDto classroomDto) {
        Classroom classroom = new Classroom();
        classroom.setId(classroomDto.getId());
        classroom.setClassroomNumber(classroomDto.getClassroomNumber());
        classroom.setTypeRoom(classroomDto.getTypeRoom());
        return classroom;
    }

    public static List<ClassroomDto> classroomListToListDto(List<Classroom> classroomList) {
        return classroomList.stream().map(ClassroomMapper::classroomToClassroomDto).collect(Collectors.toList());
    }
}
