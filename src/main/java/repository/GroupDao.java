package repository;

import model.Group;
import java.sql.SQLException;
import java.util.List;

public interface GroupDao {
    long addGroup(Group group) throws SQLException, ClassNotFoundException;
    List<Group> allGroups();
    Group groupById(long id) throws SQLException, ClassNotFoundException;
    boolean updateGroup (Group group) throws SQLException, ClassNotFoundException;
    boolean deleteGroupById (long id) throws SQLException, ClassNotFoundException;
}
