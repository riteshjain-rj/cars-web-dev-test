package demo.com.cars.demo.controller;


import demo.com.cars.demo.model.request.Car;
import demo.com.cars.demo.model.response.CarDetails;
import demo.com.cars.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CarController {


    @Autowired
    @Qualifier("carService")
    CarService carService;

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carService.findAll();
    }

    @GetMapping("/car/{id}")
    public CarDetails getCar(@PathVariable("id") int id) {
        return carService.getCarDetailById(id);
    }

    @RequestMapping(value = "/car/{id}", method = RequestMethod.DELETE)
    public void deleteCar(@PathVariable("id") int id) {
        carService.delete(id);
    }

    @PostMapping("/car")
    public int saveCar(@RequestBody Car car) {
        carService.save(car);
        return car.getId();
    }

    @PutMapping("/car/{id}")
    public void saveUpdateCar(@RequestBody Car car, @PathVariable("id") int id) {
        carService.saveOrUpdate(car, id);
    }

    @GetMapping("/cars/{sortByName}")
    public List<Car> getSortByColumnName(@PathVariable("sortByName") String name) {
        return carService.getSortByColumnName(name);
    }
}