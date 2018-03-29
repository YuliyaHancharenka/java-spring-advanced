package com.epam.file.util;

import java.io.*;

public class VersionWithStreams {

    public static void fastMoveFile(File srcFolder, File destFolder) throws IOException {

        File[] listOfFiles = srcFolder.listFiles();
        for (File child : listOfFiles) {

            FileInputStream inputStream = new FileInputStream(child);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            int size = inputStream.available();
            byte b[] = new byte[size];
            bufferedInputStream.read(b);

            FileOutputStream outputStream = new FileOutputStream(new File(destFolder.getPath() + child.getName()));
            outputStream.write(b);

            inputStream.close();
            bufferedInputStream.close();
            outputStream.close();
            child.delete();
        }
    }
}
