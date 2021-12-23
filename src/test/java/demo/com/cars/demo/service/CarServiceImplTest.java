package demo.com.cars.demo.service;

import demo.com.cars.demo.model.request.Car;
import demo.com.cars.demo.model.response.CarDetails;
import demo.com.cars.demo.model.response.CarScore;
import demo.com.cars.demo.repository.CarRepo;
import demo.com.cars.demo.repository.CarRepository;
import demo.com.cars.demo.repository.CustomQueryRepo;
import demo.com.cars.demo.service.Impl.CarServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by ritjain on 12/21/2021.
 */

@RunWith(SpringRunner.class)

public class CarServiceImplTest {

    @InjectMocks
    CarServiceImpl carService;

    @Mock
    CarRepository carRepository;

    @Mock
    CustomQueryRepo queryRepo;

    @Mock
    CarRepo carRepo;

    public static final String LIST_RESPONSE = "mock/response/list.json";

    @Autowired
    ResourceLoader resourceLoader;

    @Before
    public void setup() throws Exception{
        Car car = new Car();
        car.setId(100);
        car.setColour("Red");
        car.setYear(1999);
        car.setModel("A3");
        car.setMake("Audi");

        Mockito.when((carRepository.save(car)))
                .thenReturn(car);

        Mockito.when((carRepository.findById(Mockito.anyInt())))
                .thenReturn(Optional.of(new Car()));

        Mockito.when((carRepo.getScore(Mockito.any(), Mockito.any())))
                .thenReturn(new ResponseEntity<List<CarScore>>(HttpStatus.OK));
    }

    @Test
    public void givenCarRepository_whenSaveAndRetreiveCar_thenOK() {
        carService.save(Mockito.anyObject());
        Car retreiveCar = carService.getCarById(Mockito.anyInt());
        assertNotNull(retreiveCar);
    }

    @Test
    public void givenCarRepository_whenSortByColumnName_thenOK() {
        Mockito.when((queryRepo.findAllCars(Mockito.any())))
                .thenReturn(new ArrayList<>());
        Assert.assertEquals(Boolean.TRUE, carService.getSortByColumnName("make").isEmpty());
    }

    @Test
    public void givenCarRepository_whenSaveOrUpdate_thenOK() {
        Mockito.when((carRepository.save(Mockito.any())))
                .thenReturn(new Car());
        carService.saveOrUpdate(new Car(), 1);
        verify(carRepository, times(1)).save(Mockito.any());
    }

    @Test
    public void givenCarRepository_whenCarDetailById_thenOK() {
        Mockito.when((carRepository.findById(Mockito.any())))
                .thenReturn(Optional.of(new Car()));
        Mockito.when((carRepo.getScore(Mockito.any(), Mockito.any())))
                .thenReturn(new ResponseEntity<List<CarScore>>(HttpStatus.OK));
        Assert.assertEquals(new CarDetails(), carService.getCarDetailById(1));
    }
}
