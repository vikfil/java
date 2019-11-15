package modelDto;

public class GroupDto {
    private String groupNumber;
    private String groupName;
    private long id;

    public GroupDto(){}
    public GroupDto(long id) {
        this.id = id;
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
}
