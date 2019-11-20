package service;

import mapper.ClassroomMapper;
import model.Classroom;
import modelDto.ClassroomDto;
import repository.ClassroomDao;
import java.sql.SQLException;
import java.util.List;

public class ClassroomService {
    private ClassroomDao classroomDao;

    public ClassroomService(ClassroomDao classroomDao) {
        this.classroomDao = classroomDao;
    }

    public List<ClassroomDto> getClassroomsDto() {
        List<Classroom> list = classroomDao.allClassrooms();
        return ClassroomMapper.classroomListToListDto(list);
    }

    public long addClassroomDto(ClassroomDto classroomDto) throws SQLException, ClassNotFoundException {
        return classroomDao.addClassroom(ClassroomMapper.classroomDtoToClassroom(classroomDto));
    }

    public ClassroomDto classroomDtoById(long id) throws SQLException, ClassNotFoundException {
        Classroom classroom = classroomDao.classroomById(id);
        return ClassroomMapper.classroomToClassroomDto(classroom);
    }
    public boolean updateClassroomDto(ClassroomDto classroomDto) throws SQLException, ClassNotFoundException {
        Classroom classroom = ClassroomMapper.classroomDtoToClassroom(classroomDto);
        return classroomDao.updateClassroom(classroom);
    }
    public boolean deleteClassroomDtoById(long id) throws SQLException, ClassNotFoundException {
        return classroomDao.deleteClassroomById(id);
    }
}
