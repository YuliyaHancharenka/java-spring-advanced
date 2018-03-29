package com.epam.file.util;

import java.io.*;

public class VersionWithStreams {

    public static void fastMoveFile(File srcFolder, File destFolder) throws IOException {

        File[] listOfFiles = srcFolder.listFiles();
        for (File child : listOfFiles) {
            FileInputStream inputStream = null;
            BufferedInputStream bufferedInputStream = null;
            FileOutputStream outputStream = null;

            try {
                inputStream = new FileInputStream(child);
                bufferedInputStream = new BufferedInputStream(inputStream);

                int size = inputStream.available();
                byte b[] = new byte[size];
                bufferedInputStream.read(b);

                outputStream = new FileOutputStream(new File(destFolder.getPath() + "/" + child.getName()));
                outputStream.write(b);
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                child.delete();
            }

        }
    }
}
