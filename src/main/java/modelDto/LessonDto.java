package modelDto;

import model.*;

public class LessonDto {
    private long id;
    private Week weekday;
    private int numberLesson;
    private SubjectDto subjectDto;
    private GroupDto groupDto;
    private LectorDto lectorDto;
    private ClassroomDto classroomDto;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Week getWeekday() {
        return weekday;
    }

    public void setWeekday(Week weekday) {
        this.weekday = weekday;
    }

    public int getNumberLesson() {
        return numberLesson;
    }

    public void setNumberLesson(int numberLesson) {
        this.numberLesson = numberLesson;
    }

    public SubjectDto getSubjectDto() {
        return subjectDto;
    }

    public void setSubjectDto(SubjectDto subjectDto) {
        this.subjectDto = subjectDto;
    }

    public GroupDto getGroupDto() {
        return groupDto;
    }

    public void setGroupDto(GroupDto groupDto) {
        this.groupDto = groupDto;
    }

    public LectorDto getLectorDto() {
        return lectorDto;
    }

    public void setLectorDto(LectorDto lectorDto) {
        this.lectorDto = lectorDto;
    }

    public ClassroomDto getClassroomDto() {
        return classroomDto;
    }

    public void setClassroomDto(ClassroomDto classroomDto) {
        this.classroomDto = classroomDto;
    }

}
