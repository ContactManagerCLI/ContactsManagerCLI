import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;


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
            if(Files.exists(dataFile)) {
                // .write(Path filepath, List<String> list, StandardOpenOption.APPEND[optional])
                Files.write(dataFile, Arrays.asList("Zeus", "Hercules", "Aphrodite", "Poseidon", "Hephaestus", "Athena"));
                Files.write(dataFile, Arrays.asList("Hades", "Deimos", "Apollo"), StandardOpenOption.APPEND);
            }
        } catch (IOException iox){
            iox.printStackTrace();
        }



        try {
            Files.write(
                    Paths.get("dataFile", "contacts.txt"),
                    Arrays.asList("eggs"), // list with one item
                    StandardOpenOption.APPEND
            );

        } catch(IOException iox) {
            iox.printStackTrace();
        }



    }



}
