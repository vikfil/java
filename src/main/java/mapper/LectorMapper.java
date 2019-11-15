package mapper;

import model.Lector;
import modelDto.LectorDto;

import java.util.List;
import java.util.stream.Collectors;

public class LectorMapper {
    public static LectorDto lectorToLectorDto (Lector lector) {
        LectorDto lectorDto = new LectorDto();
        lectorDto.setId(lector.getId());
        lectorDto.setFirstName(lector.getFirstName());
        lectorDto.setLastName(lector.getLastName());
        return lectorDto;
    }

    public static Lector lectorDtoToLector(LectorDto lectorDto) {
        Lector lector = new Lector();
        lector.setId(lectorDto.getId());
        lector.setFirstName(lectorDto.getFirstName());
        lector.setLastName(lectorDto.getLastName());
        return lector;
    }

    public static List<LectorDto> lectorListToListDto(List<Lector> lectorList) {
        return lectorList.stream().map(LectorMapper::lectorToLectorDto).collect(Collectors.toList());
    }
}
