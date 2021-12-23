package demo.com.cars.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ritjain on 12/19/2021.
 */

@Configuration
public class HTTPClientConfig {

    @Bean(name = "carsRestTemplate")
    public RestTemplate carsRestTemplate(){
        return new RestTemplate();
    }
}
