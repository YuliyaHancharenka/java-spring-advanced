package com.epam.file.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class VersionWithFileChannel {

    public static void fastMoveFile(File srcFolder, File destFolder) throws IOException {

        File[] listOfFiles = srcFolder.listFiles();
        for (File child : listOfFiles) {
            FileChannel source = null;
            FileChannel destination = null;
            try {
                source = new FileInputStream(child).getChannel();
                destination = new FileOutputStream(new File(destFolder.getPath() + child.getName())).getChannel();

                long count = 0;
                long size = source.size();
                while ((count += destination.transferFrom(source, count, size - count)) < size) ;
            } finally {
                if (source != null) {
                    source.close();
                }
                if (destination != null) {
                    destination.close();
                }
            }
            child.delete();
        }
    }
}
