import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ContactTesterApplication {
    public static void main(String[] args) {
    // Creating the folder
    String directory = "data";
    String fileName = "contacts.txt";

    Path dataDirectory = Paths.get(directory);
    Path dataFile = Paths.get(directory, fileName);

        System.out.println(dataDirectory);
        System.out.println(dataFile);

        try {
            if(Files.notExists(dataDirectory)){
                Files.createDirectory(dataDirectory);
            }
            if(!Files.exists((dataFile))){
                Files.createFile(dataFile);
            }
        } catch (IOException iox){
            iox.printStackTrace();
        }

    }

}
