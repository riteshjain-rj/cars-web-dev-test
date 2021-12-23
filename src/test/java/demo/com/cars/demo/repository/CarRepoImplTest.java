package demo.com.cars.demo.repository;

import demo.com.cars.demo.model.response.CarScore;
import demo.com.cars.demo.repository.Impl.CarRepoImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by ritjain on 10/22/2021.
 */


@SpringBootTest
@RunWith(SpringRunner.class)
public class CarRepoImplTest {

    @InjectMocks
    CarRepoImpl carRepo;

    @Mock
    RestTemplate restTemplate;

    private String apiScheme;
    private String apiHost;
    private String apiPath;

    @Before
    public void setup()  {
        apiScheme = "https";
        apiHost = "api.datamuse.com";
        apiPath = "/words";
        ReflectionTestUtils.setField(carRepo, "apiScheme", apiScheme);
        ReflectionTestUtils.setField(carRepo, "apiHost", apiHost);
        ReflectionTestUtils.setField(carRepo, "apiPath", apiPath);
    }

    @Test
    public void getScoreTest() throws Exception {

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme(apiScheme)
                .host(apiHost)
                .path(apiPath)
                .queryParam("sp", "Audi").encode();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        BDDMockito.given(restTemplate.exchange(builder.build().toUri(),
                HttpMethod.GET, new HttpEntity<Object>(headers),
                new ParameterizedTypeReference<List<CarScore>>() {
                })).willReturn(new ResponseEntity<>(HttpStatus.OK));

        ResponseEntity<List<CarScore>> entity = carRepo.getScore(headers, "Audi");
        Assert.assertEquals(new ResponseEntity<List<CarScore>>(HttpStatus.OK), entity);
    }
}
