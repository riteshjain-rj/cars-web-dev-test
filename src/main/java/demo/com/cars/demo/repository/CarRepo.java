package demo.com.cars.demo.repository;

import demo.com.cars.demo.model.response.CarScore;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by ritjain on 12/20/2021.
 */
public interface CarRepo {
    ResponseEntity<List<CarScore>> getScore(HttpHeaders headers, String name);
}
