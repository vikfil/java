package model;

import java.util.Objects;

public class Group {
    private String groupNumber;
    private String groupName;
    private long id;

    public Group() {}

    public Group(String groupNumber, String groupName) {
        this.groupNumber = groupNumber;
        this.groupName = groupName;
    }

    public Group(String groupNumber, String groupName, long id) {
        this.groupNumber = groupNumber;
        this.groupName = groupName;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupNumber( String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupNumber, groupName, id);
    }

    @Override
    public boolean equals(Object otherObject) {
        if(this == otherObject) {
            return true;
        }
        if (otherObject == null) {
            return false;
        }

        if (getClass() != otherObject.getClass()) {
            return false;
        }
        Group other = (Group) otherObject;
        return Objects.equals(groupNumber, other.groupNumber)
                && Objects.equals(groupName, other.groupName)
                && id == other.id;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupNumber='" + groupNumber + '\'' +
                ", groupName='" + groupName + '\'' +
                ", id=" + id +
                '}';
    }
}
