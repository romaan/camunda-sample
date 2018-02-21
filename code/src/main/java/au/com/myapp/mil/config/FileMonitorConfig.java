package au.com.myapp.mil.config;

import org.springframework.core.env.Environment;
import au.com.myapp.mil.service.FileMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class FileMonitorConfig {

    @Autowired
    private FileMonitorService fileMonitorService;

    @Autowired
    private Environment env;

    @PostConstruct
    public void updateData() throws IOException {
        String monitorPath = env.getProperty("file-monitor.path");
        if (monitorPath != null) {
            Path path = Paths.get(monitorPath);
            File file = new File(path.toString());
            if (file.exists()) {
                fileMonitorService.monitor(path);
            } else {
                System.out.println("Path does not exists, hence no monitoring of files");
            }
        }
    }

}
