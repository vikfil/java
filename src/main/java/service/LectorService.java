package service;

import mapper.LectorMapper;
import model.Lector;
import modelDto.LectorDto;
import repository.LectorDao;
import java.sql.SQLException;
import java.util.List;

public class LectorService {
    private LectorDao lectorDao;

    public LectorService(LectorDao lectorDao) {
        this.lectorDao = lectorDao;
    }

    public List<LectorDto> getLectorsDto() {
        List<Lector> list = lectorDao.allLectors();
        return LectorMapper.lectorListToListDto(list);
    }

    public long addLectorDto(LectorDto lectorDto) throws SQLException, ClassNotFoundException {
        return lectorDao.addLector(LectorMapper.lectorDtoToLector(lectorDto));
    }

    public LectorDto lectorDtoById(long id) throws SQLException, ClassNotFoundException {
        Lector lector = lectorDao.lectorById(id);
        return LectorMapper.lectorToLectorDto(lector);
    }
    public boolean updateLectorDto(LectorDto lectorDto) throws SQLException, ClassNotFoundException {
        Lector lector = LectorMapper.lectorDtoToLector(lectorDto);
        return lectorDao.updateLector(lector);
    }
    public boolean deleteLectorDtoById(long id) throws SQLException, ClassNotFoundException {
        return lectorDao.deleteLectorById(id);
    }
}
