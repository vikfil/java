package serviceDto;

import mapper.LessonMapper;
import model.*;
import modelDto.*;
import repository.*;
import java.sql.SQLException;
import java.util.List;

public class LessonServiceDto {

    public static boolean addLessonDto (LessonDto lessonDto)
                                       throws SQLException, ClassNotFoundException {
        Subject subject = SubjectRepository.subjectById(lessonDto.getSubjectDto().getId());
        Group group = GroupRepository.groupById(lessonDto.getGroupDto().getId());
        Lector lector = LectorRepository.lectorById(lessonDto.getLectorDto().getId());
        Classroom classroom = ClassroomRepository.classroomById(lessonDto.getClassroomDto().getId());
        Lesson lesson = new Lesson(lessonDto.getWeekday(), lessonDto.getNumberLesson(), subject, group, lector, classroom);
        return ScheduleDataAccess.addLesson(lesson);
    }
   public static List<LessonDto> getLessonsDto() throws SQLException, ClassNotFoundException {
        List<Lesson> list = ScheduleDataAccess.getAllLessons();
        return LessonMapper.listLessonToListDto(list);
   }
   public static boolean deleteLessonDtoById(long id) throws SQLException, ClassNotFoundException {
        return ScheduleDataAccess.deleteLessonById(id);

   }

}
