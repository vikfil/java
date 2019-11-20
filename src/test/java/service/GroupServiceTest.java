package service;

import model.Group;
import modelDto.GroupDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import repository.GroupDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class GroupServiceTest {
    @Mock
    GroupDao groupDao;

    List<Group> list = new ArrayList<>();

    @InjectMocks
    GroupService groupService = new GroupService(groupDao);

    @Before
    public void setUp() throws Exception {
        list.add(new Group("1","one"));
        list.add(new Group("2","two"));
    }

    @Test
    public void getGroupsDto_Should_return_equals() {
        given(groupDao.allGroups()).willReturn(list);
        List<GroupDto> groupDtoList = groupService.getGroupsDto();
        Assert.assertEquals(groupDtoList.get(0), new GroupDto("1", "one"));
    }

    @Test(expected = SQLException.class)
    public void getGroupsDto_Should_throw_exception() {
        given(groupDao.allGroups()).willThrow(SQLException.class);
        groupService.getGroupsDto();
    }

    @Test
    public void addGroupDto_Should_return_true() throws SQLException, ClassNotFoundException {
        given(groupDao.addGroup(list.get(0))).willReturn(10L);
        long existId = groupService.addGroupDto(new GroupDto("1", "one"));
        Assert.assertEquals(existId, 10L);
        verify(groupDao).addGroup(list.get(0));
    }

    @Test(expected = SQLException.class)
    public void addGroupDto_Should_throw_exception() throws SQLException, ClassNotFoundException {
        given(groupDao.addGroup(any(Group.class))).willThrow(SQLException.class);
        groupService.addGroupDto(new GroupDto("3", "three"));
    }

    @Test
    public void groupDtoById_Should_return_true() throws SQLException, ClassNotFoundException {
        given(groupDao.groupById(10)).willReturn(new Group("1", "one"));
        GroupDto groupDto = groupService.groupDtoById(10);
        Assert.assertEquals(groupDto, new GroupDto("1", "one"));
        verify(groupDao).groupById(10);
    }
    @Test(expected = SQLException.class)
    public void groupDtoById_Should_throw_true() throws SQLException, ClassNotFoundException {
        given(groupDao.groupById(anyLong())).willThrow(SQLException.class);
        groupService.groupDtoById(89);
    }

    @Test
    public void updateGroupDto_Should_return_true() throws SQLException, ClassNotFoundException {
        given(groupDao.updateGroup(list.get(0))).willReturn(true);
        boolean isTrue = groupService.updateGroupDto(new GroupDto("1", "one"));
        Assert.assertEquals(isTrue, true);
        verify(groupDao).updateGroup(list.get(0));
    }

    @Test(expected = SQLException.class)
    public void updateGroupDto_Should_throw_exception() throws SQLException, ClassNotFoundException {
        given(groupDao.updateGroup(any(Group.class))).willThrow(SQLException.class);
        groupService.updateGroupDto(new GroupDto("4", "four"));
    }

    @Test
    public void deleteGroupDtoById_Should_return_true() throws SQLException, ClassNotFoundException {
        given(groupDao.deleteGroupById(10)).willReturn(true);
        boolean isTrue = groupService.deleteGroupDtoById(10);
        Assert.assertEquals(isTrue, true);
        verify(groupDao).deleteGroupById(10);
    }

    @Test(expected = SQLException.class)
    public void deleteGroupDto_Should_throw_exception() throws SQLException, ClassNotFoundException {
        given(groupDao.deleteGroupById(anyLong())).willThrow(SQLException.class);
        groupService.deleteGroupDtoById(90);
    }
}