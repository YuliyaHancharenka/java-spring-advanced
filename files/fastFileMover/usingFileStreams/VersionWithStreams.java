import java.io.*;

public class VersionWithStreams {

    public static void main(String[] args) throws IOException {

        File srcFolder = new File("D:\\EPAM\\from\\3.txt");
        File destFolder = new File("D:\\EPAM\\to\\6.txt");

        if (!destFolder.exists()) {
            destFolder.mkdirs();
        }

        if (srcFolder.exists()) {
            long startTime = System.currentTimeMillis();
            fastMoveFile(srcFolder, destFolder);
            long endTime = System.currentTimeMillis();
            long operationTime = endTime - startTime;
            System.out.println("Moving took: " + operationTime + " millisec");
        }
    }

    private static void fastMoveFile(File srcFolder, File destFolder) throws IOException {

        FileInputStream fip = new FileInputStream(srcFolder);
        BufferedInputStream fbs = new BufferedInputStream(fip);

        int size = fbs.available();
        byte b[] = new byte[size];
        fbs.read(b);

        FileOutputStream fos = new FileOutputStream(destFolder);
        fos.write(b);

        fip.close();
        fbs.close();
        fos.close();

        srcFolder.delete();
    }
}
