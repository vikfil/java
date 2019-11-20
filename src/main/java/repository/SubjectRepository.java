package repository;

import datasource.JDBCSingleton;
import model.Subject;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepository implements SubjectDao{
    private final static String INSERT_SUBJECT = "INSERT INTO schedule.subject(subject_name) VALUES(?)";
    private final static String SELECT_ALL = "SELECT * FROM schedule.subject";
    private final static String DELETE = "DELETE FROM schedule.subject WHERE subject_id = ?";
    private final static String UPDATE = "UPDATE schedule.subject SET subject_name = ? WHERE subject_id  = ?";
    private final static String SUBJECT_ID = "SELECT * FROM schedule.subject WHERE subject_id = ?";
    private static Logger logger = Logger.getLogger(SubjectRepository.class.getName());


    public long addSubject(Subject subject) throws SQLException, ClassNotFoundException {
       logger.info("Inside method addSubject");
        try(Connection connection = JDBCSingleton.getInstance().getConnection();
            PreparedStatement prep = connection.prepareStatement(INSERT_SUBJECT, Statement.RETURN_GENERATED_KEYS)){
            prep.setString(1, subject.getSubjectName());
            prep.executeUpdate();
            ResultSet resultSet = prep.getGeneratedKeys();
            resultSet.next();
            return resultSet.getLong(1);
            }
        }

    public List<Subject> allSubjects() {
        List<Subject> list = new ArrayList<>();
        try (Connection connection = JDBCSingleton.getInstance().getConnection();
             PreparedStatement prep = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = prep.executeQuery();
            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setId(resultSet.getLong("subject_id"));
                subject.setSubjectName(resultSet.getString("subject_name"));
                list.add(subject);
            }
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Subject subjectById(long id) throws SQLException, ClassNotFoundException {
        logger.info("Inside method subjectById");
        try (Connection connection = JDBCSingleton.getInstance().getConnection();
             PreparedStatement prep = connection.prepareStatement(SUBJECT_ID)) {
             prep.setLong(1, id);
             ResultSet result = prep.executeQuery();
             while (result.next()) {
                Subject returningSubject = new Subject();
                returningSubject.setSubjectName(result.getString("subject_name"));
                returningSubject.setId(result.getLong("subject_id"));
                return returningSubject;
             }
        }
        throw new SQLException("Subject id doesn't exist");
    }

    public boolean updateSubject (Subject subject) throws SQLException, ClassNotFoundException {
        logger.info("Inside method updateSubject");
        try (Connection connection = JDBCSingleton.getInstance().getConnection();
             PreparedStatement prep = connection.prepareStatement(UPDATE)) {
             prep.setString(1, subject.getSubjectName());
             prep.setLong(2, subject.getId());
             prep.executeUpdate();
             return true;
        }
    }

    public boolean deleteSubjectById (long id) throws SQLException, ClassNotFoundException {
        logger.info("Inside method deleteSubjectById");
        try (Connection connection = JDBCSingleton.getInstance().getConnection();
             PreparedStatement prep = connection.prepareStatement(DELETE)) {
             prep.setLong(1, id);
             prep.executeUpdate();
             return true;
            }
        }

    }

