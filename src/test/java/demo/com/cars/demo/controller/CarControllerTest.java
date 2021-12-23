package demo.com.cars.demo.controller;

import demo.com.cars.demo.service.Impl.CarServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ritjain on 10/22/2021.
 */

@RunWith(SpringRunner.class)
public class CarControllerTest {

    @InjectMocks
    CarController carController;

    @Mock
    CarServiceImpl carService;

    MockMvc mockMvc;

    @Value("classpath:request/request.json")
    private Resource requestBody;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.carController).build();
    }

    @Test
    public void givenCarController_whenRetrieveCar_thenOK() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/car/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status()
                        .is2xxSuccessful());

    }

    @Test
    public void givenCarController_whenRetrieveAllCars_thenOK() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status()
                        .is2xxSuccessful());

    }

    @Test
    public void givenCarController_whenDeleteCar_thenOK() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/car/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status()
                        .is2xxSuccessful());
    }

    @Test
    public void givenCarController_whenSaveCar_thenOK() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/car")
                .content(getResourceContent(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status()
                        .is2xxSuccessful());
    }

    @Test
    public void givenCarController_whenSaveUpdateCar_thenOK() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/api/car/1")
                .content(getResourceContent(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status()
                        .is2xxSuccessful());
    }

    @Test
    public void givenCarController_whenSortByColumnName_thenOK() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars/make")
                .content(getResourceContent(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status()
                        .is2xxSuccessful());
    }

    public static String getResourceContent(Resource resource){
        return Optional.of(resource).map(res -> {
            try {
                return org.springframework.util.StreamUtils.copyToString(res.getInputStream(), Charset.defaultCharset());
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }).orElseThrow(RuntimeException::new);
    }

}
