package repository;

import datasource.JDBCSingleton;
import model.Lector;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LectorRepository implements LectorDao {
    private final static String INSERT_LECTOR = "INSERT INTO schedule.lector(first_name, last_name) VALUES(?,?)";
    private final static String SELECT_ALL = "SELECT * FROM schedule.lector";
    private final static String DELETE = "DELETE FROM schedule.lector WHERE lector_id = ?";
    private final static String UPDATE = "UPDATE schedule.lector SET first_name = ?, last_name = ? WHERE lector_id  = ?";
    private final static String LECTOR_ID = "SELECT * FROM schedule.lector WHERE lector_id = ?";
    private final static String DROP_TABLE = "DROP TABLE schedule.lector";
    private static Logger logger = Logger.getLogger(LectorRepository.class.getName());

    @Override
    public long addLector(Lector lector) throws SQLException, ClassNotFoundException {
        logger.info("Inside method addLector");
        try(Connection connection = JDBCSingleton.getInstance().getConnection();
            PreparedStatement prep = connection.prepareStatement(INSERT_LECTOR, Statement.RETURN_GENERATED_KEYS)) {
            prep.setString(1, lector.getFirstName());
            prep.setString(2, lector.getLastName());
            prep.executeUpdate();
            ResultSet resultSet = prep.getGeneratedKeys();
            resultSet.next();
            return resultSet.getLong(1);
        }
    }

    @Override
    public List<Lector> allLectors() {
        logger.info("Inside method allLectors");
        List<Lector> list = new ArrayList<>();
        try(Connection connection = JDBCSingleton.getInstance().getConnection();
            PreparedStatement prep = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = prep.executeQuery();
            while (resultSet.next()) {
                Lector lec = new Lector();
                lec.setFirstName(resultSet.getString("first_name"));
                lec.setLastName(resultSet.getString("last_name"));
                lec.setId(resultSet.getLong("lector_id"));
                list.add(lec);
            }
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Lector lectorById(long id) throws SQLException, ClassNotFoundException {
        logger.info("Inside method lectorById");
        try(Connection connection = JDBCSingleton.getInstance().getConnection();
            PreparedStatement prep = connection.prepareStatement(LECTOR_ID)) {
            prep.setLong(1, id);
            ResultSet result = prep.executeQuery();
            while (result.next()) {
                Lector returningLector = new Lector();
                returningLector.setFirstName(result.getString("first_name"));
                returningLector.setLastName(result.getString("last_name"));
                returningLector.setId(result.getLong("lector_id"));
                return returningLector;
            }
        }
        throw new SQLException("Lector id doesn't exist");
    }

    @Override
    public boolean updateLector(Lector lector) throws SQLException, ClassNotFoundException {
        logger.info("Inside method updateLector");
        try(Connection connection = JDBCSingleton.getInstance().getConnection();
            PreparedStatement prep = connection.prepareStatement(UPDATE)) {
            prep.setString(1, lector.getFirstName());
            prep.setString(2, lector.getLastName());
            prep.setLong(3, lector.getId());
            prep.executeUpdate();
            return true;
        }
    }

    @Override
    public boolean deleteLectorById(long id) throws SQLException, ClassNotFoundException {
        logger.info("Inside method deleteLectorById");
        try(Connection connection = JDBCSingleton.getInstance().getConnection();
            PreparedStatement prep = connection.prepareStatement(DELETE)) {
            prep.setLong(1, id);
            prep.executeUpdate();
            return true;
        }
    }
}
