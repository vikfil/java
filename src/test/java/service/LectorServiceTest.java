package service;

import model.Lector;
import modelDto.LectorDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import repository.LectorDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LectorServiceTest {

    @Mock
    LectorDao lectorDao;

    List<Lector> list = new ArrayList<>();

    @InjectMocks
    LectorService lectorService = new LectorService(lectorDao);

    @Before
    public void setUp() throws Exception {
        list.add(new Lector("Ivan", "Petrovich"));
        list.add(new Lector("Igor", "Semenovich"));
    }

    @Test
    public void getLectorsDto() {
        given(lectorDao.allLectors()).willReturn(list);
        List<LectorDto> lectorDtoList = lectorService.getLectorsDto();
        Assert.assertEquals(lectorDtoList.get(0), new LectorDto("Ivan", "Petrovich"));
    }

    @Test(expected = SQLException.class)
    public void getLectorsDto_Should_throw_exception() {
        given(lectorDao.allLectors()).willThrow(SQLException.class);
        lectorService.getLectorsDto();
    }

    @Test
    public void addLectorDto_Should_return_true() throws SQLException, ClassNotFoundException {
        given(lectorDao.addLector(list.get(0))).willReturn(10L);
        long existId = lectorService.addLectorDto(new LectorDto("Ivan", "Petrovich"));
        Assert.assertEquals(existId, 10L);
        verify(lectorDao).addLector(list.get(0));
    }

    @Test(expected = SQLException.class)
    public void addLectorDto_Should_throw_exception() throws SQLException, ClassNotFoundException {
        given(lectorDao.addLector(any(Lector.class))).willThrow(SQLException.class);
        lectorService.addLectorDto(new LectorDto("Petro", "Ivanovich"));
    }

    @Test
    public void lectorDtoById_Should_return_true() throws SQLException, ClassNotFoundException {
        given(lectorDao.lectorById(10)).willReturn(list.get(0));
        LectorDto lectorDto = lectorService.lectorDtoById(10);
        Assert.assertEquals(lectorDto, new LectorDto("Ivan", "Petrovich"));
        verify(lectorDao).lectorById(10);
    }

    @Test(expected = SQLException.class)
    public void lectorDtoById_Should_throw_exception() throws SQLException, ClassNotFoundException {
        given(lectorDao.lectorById(anyLong())).willThrow(SQLException.class);
        lectorService.lectorDtoById(7);
    }

    @Test
    public void updateLectorDto_Should_return_true() throws SQLException, ClassNotFoundException {
        given(lectorDao.updateLector(list.get(0))).willReturn(true);
        boolean isTrue = lectorService.updateLectorDto(new LectorDto("Ivan", "Petrovich"));
        Assert.assertEquals(isTrue, true);
        verify(lectorDao).updateLector(list.get(0));
    }

    @Test(expected = SQLException.class)
    public void updateLectorDto_Should_throw_exception() throws SQLException, ClassNotFoundException {
    given(lectorDao.updateLector(any(Lector.class))).willThrow(SQLException.class);
    lectorService.updateLectorDto(new LectorDto("Petro", "Ivanovich"));
    }

    @Test
    public void deleteLectorDtoById_Should_return_true() throws SQLException, ClassNotFoundException {
        given(lectorDao.deleteLectorById(10)).willReturn(true);
        boolean isTrue = lectorService.deleteLectorDtoById(10);
        Assert.assertEquals(isTrue, true);
        verify(lectorDao).deleteLectorById(10);
    }

    @Test(expected = SQLException.class)
    public void deleteLectorDtoById_Should_throw_exception() throws SQLException, ClassNotFoundException {
        given(lectorDao.deleteLectorById(anyLong())).willThrow(SQLException.class);
        lectorService.deleteLectorDtoById(5);
    }
}