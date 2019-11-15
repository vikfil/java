package serviceDto;


import mapper.SubjectMapper;
import model.Subject;
import modelDto.SubjectDto;
import repository.SubjectRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SubjectServiceDto {
    
    public static List<SubjectDto> getSubjectsDto() {
        List<Subject> list = SubjectRepository.allSubjects();
            return SubjectMapper.subjectListToListDto(list);
        }
    }

