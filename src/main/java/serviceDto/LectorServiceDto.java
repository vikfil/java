package serviceDto;

import mapper.LectorMapper;
import model.Lector;
import modelDto.LectorDto;
import repository.LectorRepository;

import java.sql.SQLException;
import java.util.List;

public class LectorServiceDto {
    public static List<LectorDto> getLectorsDto() {
        List<Lector> list = LectorRepository.allLectors();
        return LectorMapper.lectorListToListDto(list);
    }

    public static long addLectorDto(LectorDto lectorDto) throws SQLException, ClassNotFoundException {
        return LectorRepository.addLector(LectorMapper.lectorDtoToLector(lectorDto));
    }

    public static LectorDto lectorDtoById(long id) throws SQLException, ClassNotFoundException {
        Lector lector = LectorRepository.lectorById(id);
        return LectorMapper.lectorToLectorDto(lector);
    }
    public static boolean updateLectorDto(LectorDto lectorDto) throws SQLException, ClassNotFoundException {
        Lector lector = LectorMapper.lectorDtoToLector(lectorDto);
        return LectorRepository.updateLector(lector);
    }
    public static boolean deleteLectorDtoById(long id) throws SQLException, ClassNotFoundException {
        return LectorRepository.deleteLectorById(id);
    }
}
