package serviceDto;

import mapper.GroupMapper;
import model.Group;
import modelDto.GroupDto;
import repository.GroupRepository;

import java.sql.SQLException;
import java.util.List;

public class GroupServiceDto {
    public static List<GroupDto> getGroupsDto() {
        List<Group> list = GroupRepository.allGroups();
        return GroupMapper.groupListToListDto(list);
    }

    public static long addGroupDto(GroupDto groupDto) throws SQLException, ClassNotFoundException {
        return GroupRepository.addGroup(GroupMapper.groupDtoToGroup(groupDto));
    }

    public static GroupDto groupDtoById(long id) throws SQLException, ClassNotFoundException {
        Group group = GroupRepository.groupById(id);
        return GroupMapper.groupToGroupDto(group);
    }
    public static boolean updateGroupDto(GroupDto groupDto) throws SQLException, ClassNotFoundException {
       Group group = GroupMapper.groupDtoToGroup(groupDto);
        return GroupRepository.updateGroup(group);
    }
    public static boolean deleteGroupDtoById(long id) throws SQLException, ClassNotFoundException {
        return GroupRepository.deleteGroupById(id);
    }
}
