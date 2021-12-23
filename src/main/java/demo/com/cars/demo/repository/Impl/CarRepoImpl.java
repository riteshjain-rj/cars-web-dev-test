package demo.com.cars.demo.repository.Impl;

import demo.com.cars.demo.model.response.CarScore;
import demo.com.cars.demo.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by ritjain on 12/19/2021.
 */

@Repository("carRepo")
public class CarRepoImpl implements CarRepo{

    @Autowired
    @Qualifier("carsRestTemplate")
    private RestTemplate restTemplate;

    @Value("${api.scheme}")
    private String apiScheme;

    @Value("${api.host}")
    private String apiHost;

    @Value("${api.path}")
    private String apiPath;

    @Override
    public ResponseEntity<List<CarScore>> getScore(HttpHeaders headers, String name) {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
        .scheme(apiScheme)
        .host(apiHost)
        .path(apiPath)
        .queryParam("sp", name);

        return restTemplate.exchange(builder.build().toUri(),
                HttpMethod.GET, new HttpEntity<Object>(headers),
                new ParameterizedTypeReference<List<CarScore>>() {
                });
    }
}

