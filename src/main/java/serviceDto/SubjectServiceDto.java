package serviceDto;

import mapper.SubjectMapper;
import model.Subject;
import modelDto.SubjectDto;
import repository.SubjectRepository;

import java.sql.SQLException;
import java.util.List;


public class SubjectServiceDto {
    
    public static List<SubjectDto> getSubjectsDto() {
        List<Subject> list = SubjectRepository.allSubjects();
            return SubjectMapper.subjectListToListDto(list);
        }
    public static long addSubjectDto(SubjectDto subjectDto) throws SQLException, ClassNotFoundException {
        return SubjectRepository.addSubject(SubjectMapper.subjectDtoToSubject(subjectDto));
    }

    public static SubjectDto subjectDtoById(long id) throws SQLException, ClassNotFoundException {
       Subject subject = SubjectRepository.subjectById(id);
        return SubjectMapper.subjectToSubjectDto(subject);
    }
    public static boolean updateSubjectDto(SubjectDto subjectDto) throws SQLException, ClassNotFoundException {
        Subject subject = SubjectMapper.subjectDtoToSubject(subjectDto);
        return SubjectRepository.updateSubject(subject);
    }
    public static boolean deleteSubjectDtoById(long id) throws SQLException, ClassNotFoundException {
        return SubjectRepository.deleteSubjectById(id);
    }
    }

