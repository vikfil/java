package service;

import model.Classroom;
import modelDto.ClassroomDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import repository.ClassroomDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClassroomServiceTest {
    @Mock
    ClassroomDao classroomDao;

    List<Classroom> list = new ArrayList<>();

    @InjectMocks
    ClassroomService classroomService = new ClassroomService(classroomDao);

    @Before
    public void setUp() {
        list.add(new Classroom("one"));
        list.add(new Classroom("two"));
    }

    @Test
    public void getClassroomsDto_Should_return_equals() {
       when(classroomDao.allClassrooms()).thenReturn(list);
        List<ClassroomDto> classroomDtoList = classroomService.getClassroomsDto();
        Assert.assertEquals(classroomDtoList.get(0), new ClassroomDto("one"));
    }

    @Test(expected = SQLException.class)
    public void getClassroomsDto_throw_exception() {
        given(classroomDao.allClassrooms()).willThrow(SQLException.class);
        classroomService.getClassroomsDto();
    }

    @Test
    public void addClassroomDto_Should_return_true() throws SQLException, ClassNotFoundException {
        given(classroomDao.addClassroom(new Classroom("one"))).willReturn(10L);
        long existId = classroomService.addClassroomDto(new ClassroomDto("one"));
        Assert.assertEquals(existId, 10L);
        verify(classroomDao).addClassroom(new Classroom("one"));
    }

    @Test(expected = SQLException.class)
    public void addClassroomDto_Should_throw_exception() throws SQLException, ClassNotFoundException {
        given(classroomDao.addClassroom(any(Classroom.class))).willThrow(SQLException.class);
        classroomService.addClassroomDto(new ClassroomDto("One"));
    }

    @Test
    public void classroomDtoById_Should_return_true() throws SQLException, ClassNotFoundException {
        given(classroomDao.classroomById(10)).willReturn(new Classroom("One"));
        ClassroomDto classroomDto = classroomService.classroomDtoById(10);
        Assert.assertEquals(classroomDto, new ClassroomDto("One"));
        verify(classroomDao).classroomById(10);
    }
    @Test(expected = SQLException.class)
    public void classroomDtoById_Should_throw_exception() throws SQLException, ClassNotFoundException {
        given(classroomDao.classroomById(anyLong())).willThrow(SQLException.class);
       classroomService.classroomDtoById(1);
    }

    @Test
    public void updateClassroomDto_Should_return_true() throws SQLException, ClassNotFoundException {
        given(classroomDao.updateClassroom(new Classroom("one"))).willReturn(true);
        boolean isTrue = classroomService.updateClassroomDto(new ClassroomDto("one"));
        Assert.assertEquals(isTrue, true);
        verify(classroomDao).updateClassroom(new Classroom("one"));
    }

    @Test(expected = SQLException.class)
    public void updateClassroomDto_Should_throw_exception() throws SQLException, ClassNotFoundException {
        given(classroomDao.updateClassroom(any(Classroom.class))).willThrow(SQLException.class);
        classroomService.updateClassroomDto(new ClassroomDto("two"));
    }

    @Test
    public void deleteClassroomDtoById_Should_return_true() throws SQLException, ClassNotFoundException {
        given(classroomDao.deleteClassroomById(10)).willReturn(true);
        boolean isTrue = classroomService.deleteClassroomDtoById(10);
        Assert.assertEquals(isTrue, true);
        verify(classroomDao).deleteClassroomById(10);
    }

    @Test(expected = SQLException.class)
    public void deleteClassroomDtoById_Should_throw_exception() throws SQLException, ClassNotFoundException {
        given(classroomDao.deleteClassroomById(anyLong())).willThrow(SQLException.class);
        classroomService.deleteClassroomDtoById(5);
    }
}