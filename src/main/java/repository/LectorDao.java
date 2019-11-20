package repository;

import model.Lector;
import java.sql.SQLException;
import java.util.List;

public interface LectorDao {
    long addLector(Lector lector) throws SQLException, ClassNotFoundException;
    List<Lector> allLectors();
    Lector lectorById(long id) throws SQLException, ClassNotFoundException;
    boolean updateLector (Lector lector) throws SQLException, ClassNotFoundException;
    boolean deleteLectorById (long id) throws SQLException, ClassNotFoundException;
}
