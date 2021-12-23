package demo.com.cars.demo.service;

import demo.com.cars.demo.model.request.Car;
import demo.com.cars.demo.model.response.CarDetails;

import java.util.List;

/**
 * Created by ritjain on 12/19/2021.
 */
public interface CarService {

    List<Car> findAll();

    CarDetails getCarDetailById(int id);

    void save(Car car);

    void saveOrUpdate(Car car, int id);

    void delete(int id);

    Car getCarById(int id);

    List<Car> getSortByColumnName(String brand);
}
