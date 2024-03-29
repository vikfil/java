package repository;

import datasource.JDBCSingleton;
import model.*;
import org.apache.log4j.Logger;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;
import java.util.*;

public class LessonRepository implements LessonDao {
    private final static String INSERT_LESSON = "INSERT INTO schedule.lesson(weekday, lesson_number, subject_id, group_id, lector_id, classroom_id)"
            + "VALUES(?, ?, ?, ?, ?, ?)";

    private final static String SELECT_LESSON_FOR_GROUP = "SELECT * FROM schedule.lesson WHERE group_id = ?";
    private final static String SELECT_LESSON_FOR_LECTOR = "SELECT * FROM schedule.lesson WHERE lector_id = ?";
    private final static String SELECT_DAY = "SELECT * FROM schedule.lesson WHERE weekday = ?";
    private final static String SELECT_LESSONS = "SELECT * FROM schedule.lesson";
    private final static String DELETE = "DELETE FROM schedule.lesson WHERE lesson_id = ?";
    private static Logger logger = Logger.getLogger(LessonRepository.class.getName());

    private SubjectRepository subjectRepository;
    private GroupRepository groupRepository;
    private LectorRepository lectorRepository;
    private ClassroomRepository classroomRepository;

    public LessonRepository(SubjectRepository subjectRepository, GroupRepository groupRepository,
                            LectorRepository lectorRepository, ClassroomRepository classroomRepository) {
        this.subjectRepository = subjectRepository;
        this.groupRepository = groupRepository;
        this.lectorRepository = lectorRepository;
        this.classroomRepository = classroomRepository;
    }

    @Override
    public boolean addLesson(Lesson lesson) throws SQLException, ClassNotFoundException {
        logger.info("Inside addLesson");
        if (lesson == null) {
            return false;
        }
        List<Map<String, Long>> lessons = lessonsForDay(lesson);
        if (lessons.size() == 0) {
            return recordData(lesson);
        }
        for (Map<String, Long> map : lessons) {
            if (map.get("subject_id").equals(lesson.getSubject().getId())
                    && map.get("lector_id").equals(lesson.getLector().getId())
                    && map.get("classroom_id").equals(lesson.getClassroom().getId())) {
                return recordData(lesson);
            }
            if (!map.get("subject_id").equals(lesson.getSubject().getId())
                    && !map.get("lector_id").equals(lesson.getLector().getId())
                    && !map.get("classroom_id").equals(lesson.getClassroom().getId())) {
                return recordData(lesson);
            }
        }
        return false;
    }

    private boolean recordData(Lesson lesson) throws SQLException, ClassNotFoundException {
        logger.info("Inside recordData");
        try (Connection connection = JDBCSingleton.getConnection();
             PreparedStatement prep = connection.prepareStatement(INSERT_LESSON, Statement.RETURN_GENERATED_KEYS)) {
            prep.setString(1, lesson.getWeekday().getValue());
            prep.setInt(2, lesson.getNumberLesson());
            prep.setLong(3, lesson.getSubject().getId());
            prep.setLong(4, lesson.getGroup().getId());
            prep.setLong(5, lesson.getLector().getId());
            prep.setLong(6, lesson.getClassroom().getId());
            prep.executeUpdate();
            return true;
        }
    }

    private List<Map<String, Long>> lessonsForDay(Lesson lesson) throws SQLException, ClassNotFoundException {
        logger.info("Inside lessonsForDay");
        List<Map<String, Long>> list = new ArrayList<>();
        try (Connection connection = JDBCSingleton.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_DAY)) {
            statement.setString(1, lesson.getWeekday().getValue());
            ResultSet result = statement.executeQuery();
            RowSetFactory factory = RowSetProvider.newFactory();
            CachedRowSet cached = factory.createCachedRowSet();
            cached.populate(result);
            connection.close();
            while (cached.next()) {
                Map<String, Long> map = new HashMap<>();
                map.put("subject_id", cached.getLong("subject_id"));
                map.put("lector_id", cached.getLong("lector_id"));
                map.put("classroom_id", cached.getLong("classroom_id"));
                list.add(map);
            }
        }
        return list;
    }

    @Override
    public List<Lesson> getScheduleForGroup(Group group) throws SQLException, ClassNotFoundException {
        try (Connection connection = JDBCSingleton.getConnection();
             PreparedStatement prep = connection.prepareStatement(SELECT_LESSON_FOR_GROUP)) {
             prep.setLong(1, group.getId());
             ResultSet result = prep.executeQuery();
             RowSetFactory factory = RowSetProvider.newFactory();
             CachedRowSet cached = factory.createCachedRowSet();
             cached.populate(result);
             connection.close();
            List<Lesson> lessonForGroup = new ArrayList<>();
             while (cached.next()) {
                 Lesson lesson = new Lesson();
                 lesson.setId(cached.getLong("lesson_id"));
                 lesson.setWeekday(Week.valueOf(cached.getString("weekday").toUpperCase()));
                 lesson.setNumberLesson(cached.getInt("lesson_number"));
                 lesson.setSubject(subjectRepository.subjectById(cached.getLong("subject_id")));
                 lesson.setGroup(group);
                 lesson.setLector(lectorRepository.lectorById(cached.getLong("lector_id")));
                 lesson.setClassroom(classroomRepository.classroomById(cached.getLong("classroom_id")));
                 lessonForGroup.add(lesson);
             }
            return lessonForGroup;
        }
    }

    @Override
    public List<Lesson> getScheduleForLector(Lector lector) throws SQLException, ClassNotFoundException {
        try (Connection connection = JDBCSingleton.getConnection();
             PreparedStatement prep = connection.prepareStatement(SELECT_LESSON_FOR_LECTOR)) {
            prep.setLong(1, lector.getId());
            ResultSet result = prep.executeQuery();
            RowSetFactory factory = RowSetProvider.newFactory();
            CachedRowSet cached = factory.createCachedRowSet();
            cached.populate(result);
            connection.close();
            List<Lesson> lessonForLector = new ArrayList<>();
            while (cached.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(cached.getLong("lesson_id"));
                lesson.setWeekday(Week.valueOf(cached.getString("weekday").toUpperCase()));
                lesson.setNumberLesson(cached.getInt("lesson_number"));
                lesson.setSubject(subjectRepository.subjectById(cached.getLong("subject_id")));
                lesson.setGroup(groupRepository.groupById(cached.getLong("group_id")));
                lesson.setLector(lector);
                lesson.setClassroom(classroomRepository.classroomById(cached.getLong("classroom_id")));
                lessonForLector.add(lesson);
            }
            return lessonForLector;
        }
    }

    @Override
    public List<Lesson> getAllLessons() throws SQLException, ClassNotFoundException {
        try (Connection connection = JDBCSingleton.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
             ResultSet result = statement.executeQuery(SELECT_LESSONS);
             RowSetFactory factory = RowSetProvider.newFactory();
             CachedRowSet cached = factory.createCachedRowSet();
             cached.populate(result);
             connection.close();
             List<Lesson> lessons = new ArrayList<>();
             while (cached.next()) {
                 Lesson les = new Lesson();
                 les.setId(cached.getLong("lesson_id"));
                 les.setWeekday(Week.valueOf(cached.getString("weekday").toUpperCase()));
                 les.setNumberLesson(cached.getInt("lesson_number"));
                 les.setSubject(subjectRepository.subjectById(cached.getLong("subject_id")));
                 les.setGroup(groupRepository.groupById(cached.getLong("group_id")));
                 les.setLector(lectorRepository.lectorById(cached.getLong("lector_id")));
                 les.setClassroom(classroomRepository.classroomById(cached.getLong("classroom_id")));
                 lessons.add(les);
             }
             return lessons;
        }
    }

    @Override
    public boolean deleteLessonById(long id) throws SQLException, ClassNotFoundException {
       logger.info("Inside method deleteLessonById");
        try(Connection connection = JDBCSingleton.getInstance().getConnection();
            PreparedStatement prep = connection.prepareStatement(DELETE)) {
            prep.setLong(1, id);
            prep.executeUpdate();
            return true;
        }
    }
}
