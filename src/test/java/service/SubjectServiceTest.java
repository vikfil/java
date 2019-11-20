package service;

import model.Subject;
import modelDto.SubjectDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import repository.SubjectDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class SubjectServiceTest {

    @Mock
    SubjectDao subjectDao;

    List<Subject> list = new ArrayList<>();

    @InjectMocks
    SubjectService subjectService = new SubjectService(subjectDao);

    @Before
    public void setUp() {
        list.add(new Subject("Java"));
        list.add(new Subject("Math"));
    }

    @Test
    public void getSubjectsDto_Should_return_equals() {
        given(subjectDao.allSubjects()).willReturn(list);
        List<SubjectDto> subjectDto = subjectService.getSubjectsDto();
        Assert.assertEquals(subjectDto.get(0), new SubjectDto("Java"));

    }

    @Test(expected = SQLException.class)
    public void getSubjectsDto_Should_throw_exception() {
       given(subjectDao.allSubjects()).willThrow(SQLException.class);
       subjectService.getSubjectsDto();
    }

    @Test
    public void addSubjectDto_Should_return_true() throws SQLException, ClassNotFoundException {
     given(subjectDao.addSubject(new Subject("Javac"))).willReturn(10L);
     long existId = subjectService.addSubjectDto(new SubjectDto("Javac"));
     Assert.assertEquals(existId, 10L);
     verify(subjectDao).addSubject(new Subject("Javac"));
    }

    @Test(expected = SQLException.class)
    public void addSubjectDto_Should_throw_exception() throws SQLException, ClassNotFoundException {
      given(subjectDao.addSubject(any(Subject.class))).willThrow(SQLException.class);
      subjectService.addSubjectDto(new SubjectDto("Math"));
    }

    @Test
    public void subjectDtoById_Should_return_true() throws SQLException, ClassNotFoundException {
        given(subjectDao.subjectById(10)).willReturn(new Subject("Javac"));
        SubjectDto subjectDto = subjectService.subjectDtoById(10);
        Assert.assertEquals(subjectDto, new SubjectDto("Javac"));
        verify(subjectDao).subjectById(10);
    }

    @Test(expected = SQLException.class)
    public void subjectDtoById_Should_throw_exception() throws SQLException, ClassNotFoundException {
      given(subjectDao.subjectById(anyLong())).willThrow(SQLException.class);
      subjectService.subjectDtoById(1);
    }

    @Test
    public void updateSubjectDto_Should_return_true() throws SQLException, ClassNotFoundException {
    given(subjectDao.updateSubject(new Subject("Math"))).willReturn(true);
    boolean isTrue = subjectService.updateSubjectDto(new SubjectDto("Math"));
    Assert.assertEquals(isTrue, true);
    verify(subjectDao).updateSubject(new Subject("Math"));
    }

    @Test(expected = SQLException.class)
    public void updateSubjectDto_Should_throw_exception() throws SQLException, ClassNotFoundException {
       given(subjectDao.updateSubject(any(Subject.class))).willThrow(SQLException.class);
       subjectService.updateSubjectDto(new SubjectDto("Algebra"));
    }

    @Test
    public void deleteSubjectDtoById_Should_return_true() throws SQLException, ClassNotFoundException {
        given(subjectDao.deleteSubjectById(10)).willReturn(true);
        boolean isTrue = subjectService.deleteSubjectDtoById(10);
        Assert.assertEquals(isTrue, true);
        verify(subjectDao).deleteSubjectById(10);
    }

    @Test(expected = SQLException.class)
    public void deleteSubjectDtoById_Throw_exception() throws SQLException, ClassNotFoundException {
       given(subjectDao.deleteSubjectById(anyLong())).willThrow(SQLException.class);
       subjectService.deleteSubjectDtoById(3);
    }
}