import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class VersionWithFileChannel {

    public static void main(String[] args) throws IOException {

        File srcFolder = new File("D:\\EPAM\\from\\4.txt");
        File destFolder = new File("D:\\EPAM\\to\\5.txt");

        if (!destFolder.exists()) {
            destFolder.mkdirs();
        }

        if (srcFolder.exists()) {
            fastMoveFile(srcFolder, destFolder);
        }
    }

    private static void fastMoveFile(File srcFolder, File destFolder) throws IOException {
        if (!destFolder.exists()) {
            destFolder.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;
        try {
            source = new FileInputStream(srcFolder).getChannel();
            destination = new FileOutputStream(destFolder).getChannel();

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
    }
}
