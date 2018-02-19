package au.com.nukon.mil.config;

import au.com.nukon.mil.service.FileMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class FileMonitorConfig {

    @Autowired
    private FileMonitorService fileMonitorService;

    @PostConstruct
    public void updateData() throws IOException {
        Path path = Paths.get("/tmp/change");
        fileMonitorService.monitor(path);
    }

}
