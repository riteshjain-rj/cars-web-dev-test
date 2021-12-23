package demo.com.cars.demo.service.Impl;

import demo.com.cars.demo.model.request.Car;
import demo.com.cars.demo.model.response.CarDetails;
import demo.com.cars.demo.model.response.CarScore;
import demo.com.cars.demo.repository.CarRepo;
import demo.com.cars.demo.repository.CarRepository;
import demo.com.cars.demo.repository.CustomQueryRepo;
import demo.com.cars.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ritjain on 12/19/2021.
 */

@Service("carService")
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    CustomQueryRepo queryRepo;

    @Autowired
    @Qualifier("carRepo")
    CarRepo carRepo;

    @Override
    public List<Car> findAll() {
        return (List<Car>) carRepository.findAll();
    }

    @Override
    public Car getCarById(int id) {
        return carRepository.findById(id).get();
    }

    @Override
    public CarDetails getCarDetailById(int id) {

        Car car = carRepository.findById(id).get();

        List<CarScore> res = carRepo.getScore(getHeaders(), car.getMake()).getBody();

        //List<CarScore> res = responseEntity.getBody();

        return CarDetails.builder()
                .id(car.getId())
                .make(car.getMake())
                .model(car.getModel())
                .year(car.getYear())
                .colour(car.getColour())
                .score(res != null ? res.get(0).getScore() : null).build();
    }

    @Override
    public void save(Car car) {
        carRepository.save(car);
    }

    @Override
    public void saveOrUpdate(Car car, int id) {
        Car updateCar = carRepository.findById(id).get();
        updateCar.setColour(car.getColour());
        updateCar.setMake(car.getMake());
        updateCar.setModel(car.getModel());
        updateCar.setYear(car.getYear());
        carRepository.save(updateCar);
    }

    @Override
    public void delete(int id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<Car> getSortByColumnName(String name) {
        return queryRepo.findAllCars(Sort.by(name));
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        return headers;
    }
}
