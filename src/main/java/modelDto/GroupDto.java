package modelDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

public class GroupDto {
    @Valid
    @Pattern(message = "Bad formed group number", regexp = "[0-9a-zA-Z\\._\\-]{1,5}")
    @Size(max = 5, min = 1)
    @NotNull
    private String groupNumber;

    @Valid
    @Pattern(message = "Bad formed group name", regexp = "[a-zA-Z\\._\\-]{1,13}")
    @Size(max = 10, min = 1)
    @NotNull
    private String groupName;
    private long id;

    public GroupDto(){}
    public GroupDto(long id) {
        this.id = id;
    }

    public GroupDto(String groupNumber, String groupName) {
        this.groupNumber = groupNumber;
        this.groupName = groupName;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        if (groupName.isEmpty()) {
            return groupNumber;
        }
        return groupNumber + " " + groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupDto groupDto = (GroupDto) o;
        return id == groupDto.id &&
                groupNumber.equals(groupDto.groupNumber) &&
                Objects.equals(groupName, groupDto.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupNumber, groupName, id);
    }
}
