package com.epam.file.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class VersionWithStreamsAndBuffer {

    public static void fastMoveFile(File srcFolder, File destFolder) throws IOException {
        byte[] buffer = new byte[100 * 1024];
        File[] listOfFiles = srcFolder.listFiles();
        for (File child : listOfFiles) {
            FileInputStream inputStream = new FileInputStream(child);
            FileOutputStream outputStream = new FileOutputStream(new File(destFolder.getPath() + child.getName()));
            while (inputStream.read(buffer) != -1) {
                try {
                    outputStream.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            inputStream.close();
            outputStream.close();
            child.delete();
        }
    }
}
