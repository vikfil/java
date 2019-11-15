package repository;

import datasource.JDBCSingleton;
import model.Group;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupRepository {
    private final static String INSERT_GROUP = "INSERT INTO schedule.group(group_number, group_name) VALUES(?,?)";
    private final static String SELECT_ALL = "SELECT * FROM schedule.group";
    private final static String DELETE = "DELETE FROM schedule.group WHERE group_id = ?";
    private final static String UPDATE = "UPDATE schedule.group SET group_number = ?, group_name = ? WHERE group_id  = ?";
    private final static String GROUP_ID = "SELECT * FROM schedule.group WHERE group_id = ?";
    private final static String DROP_TABLE = "DROP TABLE schedule.group";
    private static Logger logger = Logger.getLogger(GroupRepository.class.getName());


    public static long addGroup(Group group) throws SQLException, ClassNotFoundException {
        logger.info("Inside method addGroup");
        try(Connection connection = JDBCSingleton.getInstance().getConnection()) {
            PreparedStatement prep = connection.prepareStatement(INSERT_GROUP, Statement.RETURN_GENERATED_KEYS);
            prep.setString(1, group.getGroupNumber());
            prep.setString(2, group.getGroupName());
            prep.executeUpdate();
            ResultSet resultSet = prep.getGeneratedKeys();
            resultSet.next();
            return resultSet.getLong(1);
        }
    }

    public static List<Group> allGroups() {
        logger.info("Inside method allGroups");
        List<Group> list = new ArrayList<>();
        try(Connection connection = JDBCSingleton.getInstance().getConnection();
            Statement prep = connection.createStatement()) {
            ResultSet resultSet = prep.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                Group group = new Group();
                group.setGroupNumber(resultSet.getString("group_number"));
                group.setGroupName(resultSet.getString("group_name"));
                group.setId(resultSet.getLong("group_id"));
                list.add(group);
            }
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean updateGroup(Group group) throws SQLException, ClassNotFoundException {
        logger.info("Inside method updateGroup");
        try(Connection connection = JDBCSingleton.getInstance().getConnection();
            PreparedStatement prep = connection.prepareStatement(UPDATE)) {
            prep.setString(1, group.getGroupNumber());
            prep.setString(2, group.getGroupName());
            prep.setLong(3, group.getId());
            prep.executeUpdate();
            return true;
        }
    }

    public static Group groupById(long id) throws SQLException, ClassNotFoundException {
        logger.info("Inside method groupById");
        try(Connection connection = JDBCSingleton.getInstance().getConnection();
            PreparedStatement prep = connection.prepareStatement(GROUP_ID)) {
            prep.setLong(1, id);
            ResultSet result = prep.executeQuery();
            while (result.next()) {
                Group returningGroup = new Group();
                returningGroup.setId(result.getLong("group_id"));
                returningGroup.setGroupNumber((result.getString("group_number")));
                returningGroup.setGroupName(result.getString("group_name"));
                return returningGroup;
            }
        }
        throw new SQLException("Group id doesn't exist");
    }

    public static boolean deleteGroupById(long id) throws SQLException, ClassNotFoundException {
        try(Connection connection = JDBCSingleton.getInstance().getConnection();
            PreparedStatement prep = connection.prepareStatement(DELETE)) {
            prep.setLong(1, id);
            prep.executeUpdate();
            return true;

        }
    }

}
