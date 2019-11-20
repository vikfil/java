package mapper;

import model.Subject;
import modelDto.SubjectDto;
import java.util.List;
import java.util.stream.Collectors;

public class SubjectMapper {
    public static SubjectDto subjectToSubjectDto(Subject subject) {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subject.getId());
        subjectDto.setSubjectName(subject.getSubjectName());
        return subjectDto;
    }

    public static Subject subjectDtoToSubject(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setId(subjectDto.getId());
        subject.setSubjectName(subjectDto.getSubjectName());
        return subject;
    }

    public static List<SubjectDto> subjectListToListDto(List<Subject> subjectsList) {
        return subjectsList.stream().map(SubjectMapper::subjectToSubjectDto).collect(Collectors.toList());
    }
}
