package repository;

import datasource.JDBCSingleton;
import model.Classroom;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassroomRepository {
    private final static String INSERT_CLASSROOM = "INSERT INTO schedule.classroom(classroom_number, type_room) VALUES(?,?)";
    private final static String SELECT_ALL = "SELECT * FROM schedule.classroom";
    private final static String DELETE = "DELETE FROM schedule.classroom WHERE classroom_id = ?";
    private final static String UPDATE = "UPDATE schedule.classroom SET classroom_number = ?, type_room = ? WHERE classroom_id  = ?";
    private final static String CLASSROOM_ID = "SELECT * FROM schedule.classroom WHERE classroom_id = ?";
    private final static String DROP_TABLE = "DROP TABLE schedule.classroom";
    private static Logger logger = Logger.getLogger(ClassroomRepository.class.getName());


    public static long addClassroom(Classroom classroom) throws SQLException, ClassNotFoundException {
        logger.info("Inside method addClassroom");
        try(Connection connection = JDBCSingleton.getInstance().getConnection();
            PreparedStatement prep = connection.prepareStatement(INSERT_CLASSROOM, Statement.RETURN_GENERATED_KEYS)) {
            prep.setString(1, classroom.getClassroomNumber());
            prep.setString(2, classroom.getTypeRoom());
            prep.executeUpdate();
            ResultSet resultSet = prep.getGeneratedKeys();
            resultSet.next();
            return resultSet.getLong(1);
        }
    }

    public static List<Classroom> allClassrooms() {
        logger.info("Inside method allClassroom");
        List<Classroom> list = new ArrayList<>();
        try(Connection connection = JDBCSingleton.getInstance().getConnection();
            PreparedStatement prep = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = prep.executeQuery();
            while (resultSet.next()) {
                Classroom room = new Classroom();
                room.setClassroomNumber(resultSet.getString("classroom_number"));
                room.setTypeRoom(resultSet.getString("type_room"));
                room.setId(resultSet.getLong("classroom_id"));
                list.add(room);
            }
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Classroom classroomById(long id) throws SQLException, ClassNotFoundException {
        logger.info("Inside method classroomById");
        try(Connection connection = JDBCSingleton.getInstance().getConnection();
            PreparedStatement prep = connection.prepareStatement(CLASSROOM_ID)) {
            prep.setLong(1, id);
            ResultSet result = prep.executeQuery();
            while (result.next()) {
                Classroom returningClassroom = new Classroom();
                returningClassroom.setClassroomNumber(result.getString("classroom_number"));
                returningClassroom.setTypeRoom(result.getString("type_room"));
                returningClassroom.setId(result.getLong("classroom_id"));
                return returningClassroom;
            }
        }
        throw new SQLException("Classroom id doesn't exist");
    }

    public static boolean updateClassroom(Classroom classroom) throws SQLException, ClassNotFoundException {
        logger.info("Inside method updateClassroom");
        try(Connection connection = JDBCSingleton.getInstance().getConnection();
            PreparedStatement prep = connection.prepareStatement(UPDATE)) {
            prep.setString(1, classroom.getClassroomNumber());
            prep.setString(2, classroom.getTypeRoom());
            prep.setLong(3, classroom.getId());
            prep.executeUpdate();
            return true;
        }
    }

    public static boolean deleteClassroomById(long id) throws SQLException, ClassNotFoundException {
        logger.info("Inside method deleteClassroomById");
        try(Connection connection = JDBCSingleton.getInstance().getConnection();
            PreparedStatement prep = connection.prepareStatement(DELETE)) {
            prep.setLong(1, id);
            prep.executeUpdate();
            return true;
        }
    }
}
