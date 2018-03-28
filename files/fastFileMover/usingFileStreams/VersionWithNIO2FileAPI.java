import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VersionWithNIO2FileAPI {

    public static void main(String[] args) throws IOException {
        Path srcFolder = Paths.get("D:\\EPAM\\from\\6.txt");
        Path destFolder = Paths.get("D:\\EPAM\\to\\6.txt");
        Files.copy(srcFolder, destFolder);
    }
}
