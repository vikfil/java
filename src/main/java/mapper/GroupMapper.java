package mapper;

import model.Group;
import modelDto.GroupDto;

import java.util.List;
import java.util.stream.Collectors;

public class GroupMapper {
    public static GroupDto groupToGroupDto (Group group) {
        GroupDto groupDto = new GroupDto();
        groupDto.setId(group.getId());
        groupDto.setGroupNumber(group.getGroupNumber());
        groupDto.setGroupName(group.getGroupName());
        return groupDto;
    }

    public static Group groupDtoToGroup(GroupDto groupDto) {
        Group group = new Group();
        group.setId(groupDto.getId());
        group.setGroupNumber(groupDto.getGroupNumber());
        group.setGroupName(groupDto.getGroupName());
        return group;
    }

    public static List<GroupDto> groupListToListDto(List<Group> groupList) {
        return groupList.stream().map(GroupMapper::groupToGroupDto).collect(Collectors.toList());
    }
}
