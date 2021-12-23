import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URISyntaxException;

/**
 * Created by ritjain on 12/21/2021.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootDemoApplicationTests
{
    @LocalServerPort
    int randomServerPort;

    @Test
    public void testGetEmployeeListSuccess() throws URISyntaxException
    {

    }
}
