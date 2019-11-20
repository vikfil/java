package service;

import mapper.GroupMapper;
import mapper.LectorMapper;
import mapper.LessonMapper;
import model.*;
import modelDto.*;
import repository.*;
import java.sql.SQLException;
import java.util.*;

public class LessonService {
    private LessonDao lessonDao;

    public LessonService(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    public boolean addLessonDto(LessonDto lessonDto)
            throws SQLException, ClassNotFoundException {
        SubjectRepository subjectRepository = new SubjectRepository();
        GroupRepository groupRepository = new GroupRepository();
        LectorRepository lectorRepository = new LectorRepository();
        ClassroomRepository classroomRepository = new ClassroomRepository();
        Subject subject = subjectRepository.subjectById(lessonDto.getSubjectDto().getId());
        Group group = groupRepository.groupById(lessonDto.getGroupDto().getId());
        Lector lector = lectorRepository.lectorById(lessonDto.getLectorDto().getId());
        Classroom classroom = classroomRepository.classroomById(lessonDto.getClassroomDto().getId());
        Lesson lesson = new Lesson(lessonDto.getWeekday(), lessonDto.getNumberLesson(), subject, group, lector, classroom);
        return lessonDao.addLesson(lesson);
    }

    public List<LessonDto> getLessonsDto() throws SQLException, ClassNotFoundException {
        List<Lesson> list = lessonDao.getAllLessons();
        return LessonMapper.listLessonToListDto(list);
    }

    public boolean deleteLessonDtoById(long id) throws SQLException, ClassNotFoundException {
        return lessonDao.deleteLessonById(id);

    }

    public List<LessonDto> lessonsForGroup(GroupDto groupDto) throws SQLException, ClassNotFoundException {
        List<Lesson> list = lessonDao.getScheduleForGroup(GroupMapper.groupDtoToGroup(groupDto));
        return LessonMapper.listLessonToListDto(list);
    }

    public List<LessonDto> lessonsForLector(LectorDto lectorDto) throws SQLException, ClassNotFoundException {
        List<Lesson> list = lessonDao.getScheduleForLector(LectorMapper.lectorDtoToLector(lectorDto));
        return LessonMapper.listLessonToListDto(list);
    }
}
