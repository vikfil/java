package serviceDto;

import mapper.ClassroomMapper;
import model.Classroom;
import modelDto.ClassroomDto;
import repository.ClassroomRepository;

import java.sql.SQLException;
import java.util.List;

public class ClassroomServiceDto {
    public static List<ClassroomDto> getClassroomsDto() {
        List<Classroom> list = ClassroomRepository.allClassrooms();
        return ClassroomMapper.classroomListToListDto(list);
    }

    public static long addClassroomDto(ClassroomDto classroomDto) throws SQLException, ClassNotFoundException {
        return ClassroomRepository.addClassroom(ClassroomMapper.classroomDtoToClassroom(classroomDto));
    }

    public static ClassroomDto classroomDtoById(long id) throws SQLException, ClassNotFoundException {
        Classroom classroom = ClassroomRepository.classroomById(id);
        return ClassroomMapper.classroomToClassroomDto(classroom);
    }
    public static boolean updateClassroomDto(ClassroomDto classroomDto) throws SQLException, ClassNotFoundException {
        Classroom classroom = ClassroomMapper.classroomDtoToClassroom(classroomDto);
        return ClassroomRepository.updateClassroom(classroom);
    }
    public static boolean deleteClassroomDtoById(long id) throws SQLException, ClassNotFoundException {
        return ClassroomRepository.deleteClassroomById(id);
    }
}
