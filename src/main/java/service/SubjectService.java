package service;

import mapper.SubjectMapper;
import model.Subject;
import modelDto.SubjectDto;
import repository.SubjectDao;
import java.sql.SQLException;
import java.util.List;


public class SubjectService {
    private SubjectDao subjectDao;

    public SubjectService(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    public List<SubjectDto> getSubjectsDto() {
        List<Subject> list = subjectDao.allSubjects();
            return SubjectMapper.subjectListToListDto(list);
        }

    public long addSubjectDto(SubjectDto subjectDto) throws SQLException, ClassNotFoundException {
        return subjectDao.addSubject(SubjectMapper.subjectDtoToSubject(subjectDto));
    }

    public SubjectDto subjectDtoById(long id) throws SQLException, ClassNotFoundException {
       Subject subject = subjectDao.subjectById(id);
        return SubjectMapper.subjectToSubjectDto(subject);
    }
    public boolean updateSubjectDto(SubjectDto subjectDto) throws SQLException, ClassNotFoundException {
        Subject subject = SubjectMapper.subjectDtoToSubject(subjectDto);
        return subjectDao.updateSubject(subject);
    }
    public boolean deleteSubjectDtoById(long id) throws SQLException, ClassNotFoundException {
        return subjectDao.deleteSubjectById(id);
    }
    }

