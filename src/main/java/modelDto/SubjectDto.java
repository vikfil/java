package modelDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

public class SubjectDto {
    @Valid
    @Pattern(message = "Bad formed subject name", regexp = "[a-zA-Z]{1,16}")
    @Size(max = 15, min = 3)
    @NotNull
    private String subjectName;
    private long id;

    public SubjectDto() {}

    public SubjectDto(String subjectName) {
        this.subjectName = subjectName;
    }
    public SubjectDto(long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return subjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectDto that = (SubjectDto) o;
        return id == that.id &&
                subjectName.equals(that.subjectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectName, id);
    }
}
