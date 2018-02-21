package au.com.myapp.mil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.nio.file.*;
import java.io.IOException;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

@Async
@Service
public class FileMonitorService {

    @Autowired
    private DataUploadService dataUploadService;

    private final WatchService watcher;

    public FileMonitorService() throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
    }

    public void monitor(Path dir) throws IOException {
        // See StandardWatchEventKinds for more event types.
        dir.register(this.watcher, ENTRY_MODIFY);

        System.out.println("Starting monitoring of: " + dir);

        boolean monitor = true;
        while (monitor) {
            WatchKey key = waitForChange();

            if (key == null) {
                continue;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                if (event.kind() == OVERFLOW) {
                    System.err.println("Overflow event: rechecking.");
                    continue;
                }

                // Modified path is relative to parent:
                Path modifiedPath = (Path) event.context();
                System.out.printf("%s modified %d times.%n",
                        modifiedPath, event.count());
                dataUploadService.uploadData(Paths.get(dir.toString(), modifiedPath.toString()));

                // Reset to receive further events:
                // False means that the key became invalid.
                monitor = key.reset();
            }
        }

        System.err.printf("The key for %s is no longer valid!%n", dir);

        watcher.close();
    }

    private WatchKey waitForChange() {
        try {
            return watcher.take();
        } catch (InterruptedException e) {
            System.err.println("Error while waiting for key" + e.getMessage());
        }
        return null;
    }
}
