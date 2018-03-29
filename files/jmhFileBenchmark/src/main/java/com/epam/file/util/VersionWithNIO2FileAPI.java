package com.epam.file.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class VersionWithNIO2FileAPI {

    public static void fastMoveFile(File srcFolder, File destFolder) throws IOException {
        File[] listOfFiles = srcFolder.listFiles();
        for (File child : listOfFiles) {
            Path path = Paths.get(destFolder.getPath(), child.getName());
            Files.move(child.toPath(), path, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
