package demo.com.cars.demo.repository;

import demo.com.cars.demo.model.request.Car;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ritjain on 12/20/2021.
 */
@Repository
public interface CustomQueryRepo extends JpaRepository<Car, Integer> {

    @Query(value = "SELECT c FROM Car c")
    List<Car> findAllCars(Sort sort);

    @Modifying
    @Query("UPDATE Car u set u.model = :model WHERE u.id = :id")
    void updateModel(@Param(value = "id") Integer id, @Param(value = "model") String model);

}