package xyz.windows.file.explorer.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import xyz.windows.file.explorer.model.Data;

public class Task implements Runnable {

    public interface TaskCompleted {
        public void onComplete(Data data);
    }

    private final Data dataObject;
    private final TaskCompleted taskCompleted;

    public Task(Data dataObject, TaskCompleted taskCompleted) {
        this.dataObject = dataObject;
        this.taskCompleted = taskCompleted;
    }
    
    @Override
    public void run() {
        try {
            loadData(dataObject, dataObject.getFileObject());
            taskCompleted.onComplete(dataObject);
        } catch (IOException ex) {
            System.err.println("Exception occurred: " + ex);
        }
    }

    private static void loadData(Data data, File file) throws IOException {
        if (file == null) {
            return;
        }

        AtomicLong directorySize = new AtomicLong(0L);
        AtomicLong numberOfFiles = new AtomicLong(0L);
        AtomicLong numberOfFolders = new AtomicLong(0L);
        AtomicLong failureCounts = new AtomicLong(0L);

        Files.walkFileTree(file.toPath(), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                directorySize.addAndGet(attrs.size());
                numberOfFiles.addAndGet(file.toFile().isFile() ? 1 : 0);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                failureCounts.addAndGet(exc != null ? 1 : 0);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                numberOfFolders.addAndGet(dir.toFile().isDirectory() ? 1 : 0);
                return FileVisitResult.CONTINUE;
            }
        });
        BasicFileAttributes attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);

        data.setName(file.getName());
        data.setSizeInBytes(directorySize.get());
        data.setNumberOfFiles(numberOfFiles.get());
        data.setNumberOfFolders(numberOfFolders.get());
        data.setCreationTimeStamp(attributes.creationTime().to(TimeUnit.MILLISECONDS));
    }
    

}
