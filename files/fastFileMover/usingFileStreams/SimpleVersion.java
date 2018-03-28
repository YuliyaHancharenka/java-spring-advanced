import java.io.File;

public class SimpleVersion {

    public static void main(String[] args) {

        File srcFolder = new File("d:\\from");
        File destFolder = new File("d:\\to"); // это папка, в которую будем перемещать

        if (!destFolder.exists()) {
            destFolder.mkdirs();
        }

        if (srcFolder.exists() && srcFolder.isDirectory()) {
            fastMoveFile(srcFolder, destFolder);
        } else {
            System.out.println(srcFolder + "  Folder does not exists");
        }
    }

    private static void fastMoveFile(File srcFolder, File destFolder){
        File[] listOfFiles = srcFolder.listFiles();
        if (listOfFiles != null) {
            for (File child : listOfFiles) {
                child.renameTo(new File(destFolder + "\\" + child.getName()));
            }
        }
    }
}
