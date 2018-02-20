package au.com.nukon.mil.config;

import au.com.nukon.mil.service.DataUploadService;
import au.com.nukon.mil.service.FileMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class PreloadConfig {

    @Autowired
    private DataUploadService dataUploadService;

    @Autowired
    private Environment env;

    @PostConstruct
    public void updateData() throws IOException {
        String fixturePath = env.getProperty("datasource.model.fixtures");
        if (fixturePath != null) {
            Path path = Paths.get(new ClassPathResource(fixturePath).getURI());
            File file = new File(path.toString());
            if (file.exists()) {
                dataUploadService.uploadData(path);
            } else {
                System.out.println("Path does not exists, hence no monitoring of files");
            }
        }
    }

}
