package demo.com.cars.demo.repository;

import demo.com.cars.demo.model.request.Car;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ritjain on 10/21/2021.
 */
public interface CarRepository extends CrudRepository<Car, Integer> {}