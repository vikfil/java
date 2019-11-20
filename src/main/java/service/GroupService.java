package service;

import mapper.GroupMapper;
import model.Group;
import modelDto.GroupDto;
import repository.GroupDao;
import java.sql.SQLException;
import java.util.List;

public class GroupService {
    private GroupDao groupDao;

    public GroupService(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public List<GroupDto> getGroupsDto() {
        List<Group> list = groupDao.allGroups();
        return GroupMapper.groupListToListDto(list);
    }

    public long addGroupDto(GroupDto groupDto) throws SQLException, ClassNotFoundException {
        return groupDao.addGroup(GroupMapper.groupDtoToGroup(groupDto));
    }

    public GroupDto groupDtoById(long id) throws SQLException, ClassNotFoundException {
        Group group = groupDao.groupById(id);
        return GroupMapper.groupToGroupDto(group);
    }
    public boolean updateGroupDto(GroupDto groupDto) throws SQLException, ClassNotFoundException {
       Group group = GroupMapper.groupDtoToGroup(groupDto);
        return groupDao.updateGroup(group);
    }
    public boolean deleteGroupDtoById(long id) throws SQLException, ClassNotFoundException {
        return groupDao.deleteGroupById(id);
    }
}
