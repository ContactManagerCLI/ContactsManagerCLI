import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;


public class ContactTesterApplication {
    public static void main(String[] args) {

//        Methods.addContact();
//        Methods.viewAllContacts();
//        Methods.deleteContact();
//        Methods.searchContactName();
//        Methods.mainMenu();

        try {
            boolean confirm = true;
            do{
                Methods.mainMenu();
                Scanner scanner = new Scanner(System.in);
                int userInput = scanner.nextInt();
                switch (userInput){
                    case 1:
                        Methods.viewAllContacts();
                        break;
                    case 2:
                         Methods.addContact();
                         break;
                    case 3:
                         Methods.searchContactName();
                         break;
                    case 4:
                        Methods.deleteContact();
                        break;
                    case 5:
                        System.out.println("Have a good day.");
                        confirm = false;
                        break;
                    default:
                        System.out.println("That's not an option");
                }
            } while(confirm);

        } catch (IOException iox) {
            iox.printStackTrace();
        }


    }

}
