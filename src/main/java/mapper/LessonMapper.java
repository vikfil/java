package mapper;

import model.Lesson;
import modelDto.LessonDto;
import java.util.List;
import java.util.stream.Collectors;

public class LessonMapper {
    public static LessonDto lessonToLessonDto(Lesson lesson) {
        LessonDto lessonDto = new LessonDto();
        lessonDto.setId(lesson.getId());
        lessonDto.setWeekday(lesson.getWeekday());
        lessonDto.setNumberLesson(lesson.getNumberLesson());
        lessonDto.setSubjectDto(SubjectMapper.subjectToSubjectDto(lesson.getSubject()));
        lessonDto.setGroupDto(GroupMapper.groupToGroupDto(lesson.getGroup()));
        lessonDto.setLectorDto(LectorMapper.lectorToLectorDto(lesson.getLector()));
        lessonDto.setClassroomDto(ClassroomMapper.classroomToClassroomDto(lesson.getClassroom()));
        return lessonDto;
    }

    public static List<LessonDto> listLessonToListDto(List<Lesson> list) {
        return list.stream().map(LessonMapper::lessonToLessonDto).collect(Collectors.toList());
    }
}
