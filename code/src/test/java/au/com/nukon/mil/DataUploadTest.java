package au.com.myapp.mil;

import au.com.myapp.mil.config.PreloadConfig;
import au.com.myapp.mil.repositories.CustomerOrderRepository;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:config/application-dev.yml")
@SpringBootTest(classes = { Application.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DataUploadTest {

    @Autowired
    CustomerOrderRepository customerOrderRepository;

    @Test
    public void testUploadFile() throws IOException, InterruptedException {
        String path = "/tmp/change";
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
        }
        int initialCount = (int) customerOrderRepository.count();
        Resource resource = new ClassPathResource("sample.csv");
        File source = new File(resource.getURI());
        File destination = new File(path + "/" + "sample.csv");
        FileUtils.copyFile(source, destination);
        Thread.sleep(5000);
        assertEquals(initialCount + 10, customerOrderRepository.count());
    }


    @After
    public void cleanUp()  {
        String path = "/tmp/change/sample.csv";
        File directory = new File(path);
        if (directory.exists()) {
            directory.delete();
        }
    }
}
