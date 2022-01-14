import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class Methods {

    public static void addContact() throws IOException {

        Files.write(
                Paths.get("data", "contacts.txt"),
                Arrays.asList("1st contact"), // list with one item
                StandardOpenOption.APPEND
        );
    }


}
