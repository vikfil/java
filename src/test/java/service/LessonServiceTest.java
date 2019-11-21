package service;

import model.*;
import modelDto.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import repository.*;

import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LessonServiceTest {

    @Mock
    LessonDao lessonDao;
    @Mock
    SubjectDao subjectDao;
    @InjectMocks
    SubjectService subjectService = new SubjectService(subjectDao);
    @Mock
    GroupDao groupDao;
    @InjectMocks
    GroupService groupService = new GroupService(groupDao);

    @Mock
    LectorDao lectorDao;
    @InjectMocks
    LectorService lectorService = new LectorService(lectorDao);

    @Mock
    ClassroomDao classroomDao;
    @InjectMocks
    ClassroomService classroomService = new ClassroomService(classroomDao);

    private LessonDto lessonDto;

    private Lesson lesson;
    private Subject subject;
    private Group group;
    private Lector lector;
    private Classroom classroom;

    private SubjectDto subjectDto;
    private GroupDto groupDto;
    private LectorDto lectorDto;
    private ClassroomDto classroomDto;

    @InjectMocks
    LessonService lessonService = new LessonService(lessonDao);

    @Before
    public void setUp() throws Exception {
        subject = new Subject("Java");
        group = new Group("1", "one");
        lector = new Lector("Ivan", "Petrovich");
        classroom = new Classroom("1", "one");
        lesson = new Lesson(Week.MONDAY, 3, subject, group, lector, classroom);

        subjectDto = new SubjectDto("Java" );
        subjectDto.setId(1);
        groupDto = new GroupDto("1", "one");
        groupDto.setId(1);
        lectorDto = new LectorDto("Ivan", "Petrovich");
        lectorDto.setId(1);
        classroomDto = new ClassroomDto("1");
        classroomDto.setId(1);
        lessonDto = new LessonDto(Week.MONDAY, 3, subjectDto, groupDto, lectorDto, classroomDto);
    }

    @Test
    public void addLessonDto_Should_return_true() throws SQLException, ClassNotFoundException {
//        given(subjectDao.subjectById(1)).willReturn(new Subject("Java"));
//        SubjectDto subjectDto = subjectService.subjectDtoById(1);
//        given(lectorDao.lectorById(1)).willReturn(new Lector("Ivan"," fsf"));
//        LectorDto lectorDto = lectorService.lectorDtoById(1);
//        given(groupDao.groupById(1)).willReturn(new Group("1", "one"));
//        GroupDto groupDto = groupService.groupDtoById(1);
//        given(classroomDao.classroomById(1)).willReturn(new Classroom("1","onw"));
//        ClassroomDto classroomDto = classroomService.classroomDtoById(1);
        given(lessonDao.addLesson(lesson)).willReturn(true);
        boolean isTrue = lessonService.addLessonDto(lessonDto);
//        boolean isTrue = lessonService.addLessonDto(new LessonDto(Week.MONDAY, 3, subjectService.subjectDtoById(1), groupService.groupDtoById(1), lectorService.lectorDtoById(1), classroomService.classroomDtoById(1)));
        Assert.assertEquals(isTrue, true);
        verify(lessonDao).addLesson(new Lesson(Week.MONDAY, 1, new Subject("Java"), new Group("1", "one"), new Lector("Ivan"," fsf"), new Classroom("1","onw")));

    }

    @Test
    public void getLessonsDto() {
    }

    @Test
    public void deleteLessonDtoById() {
    }

    @Test
    public void lessonsForGroup() {
    }

    @Test
    public void lessonsForLector() {
    }
}