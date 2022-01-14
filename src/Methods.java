import javax.crypto.spec.PSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

public class Methods {

//1. View contacts.
//2. Add a new contact.
//3. Search a contact by name.
//4. Delete an existing contact.
//5. Exit.
//Enter an option (1, 2, 3, 4 or 5):

    public static void addContact() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean confirm = true;
        while(confirm){
// ================================First, Last Name ==================================================================
            System.out.println("Enter first name: ");
            String firstName = scanner.nextLine();
            System.out.println("Enter last name: ");
            String lastName = scanner.nextLine();

            Path contactPath = Paths.get("data", "contacts.txt");
            List<String> contactList = Files.readAllLines(contactPath);

            for(String contact : contactList) {
                if(contact.toLowerCase().contains(firstName) && contact.toLowerCase().contains(lastName)){
                    System.out.println("There's already a contact named " + firstName + lastName +"." + "Do you want to overwrite it? (Yes/No)");
                    String yesNo = scanner.nextLine();
                    if(yesNo.equalsIgnoreCase("yes") || yesNo.equalsIgnoreCase("y")){
                    break;
                    } else {
                        addContact();
                    }
                }
            }
// ================================PHONE NUMBER==================================================================
            System.out.println("Enter a phone number: ");
            String phoneNumber = scanner.nextLine();
            String bonusNumber;
            if(phoneNumber.length() == 7){
                bonusNumber = phoneNumber.replaceFirst("(\\d{3})(\\d+)", "$1-$2");
                Files.write(
                        Paths.get("data", "contacts.txt"),
                        Arrays.asList(firstName + " " + lastName + " | " + bonusNumber), // list with one item
                        StandardOpenOption.APPEND
                );
                System.out.println(firstName + " " + lastName + " has been added to your contacts.");
                confirm = false;
            } else if(phoneNumber.length() == 10) {
                bonusNumber = phoneNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
                Files.write(
                        Paths.get("data", "contacts.txt"),
                        Arrays.asList(firstName + " " + lastName + " | " + bonusNumber), // list with one item
                        StandardOpenOption.APPEND
                );
                System.out.println(firstName + " " + lastName + " has been added to your contacts.");
                confirm = false;
            } else {
                System.out.println("That's not a number");
//                addContact();
            }
        }
    }


    public static void viewAllContacts() throws IOException{
        Path contactPath = Paths.get("data", "contacts.txt");
        List<String> contactList = Files.readAllLines(contactPath);
        System.out.println("Name | Phone Number");
        System.out.println("------------------");
        for (int i = 0; i < contactList.size(); i += 1) {
            System.out.println(contactList.get(i));
        }
    }

    public static void deleteContact() throws IOException {
        System.out.println("Who would you like to delete?");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().trim();

        List<String> lines = Files.readAllLines(Paths.get("data", "contacts.txt"));
        List<String> newList = new ArrayList<>();

        for (String line : lines) {
            if (line.toLowerCase().contains(userInput.toLowerCase())) {
                continue;
            }
            newList.add(line);
        }

        Files.write(Paths.get("data", "contacts.txt"), newList);
    }

    public static void searchContactName () throws IOException {
        System.out.println("Enter a name or phone number: ");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().trim().toLowerCase();

        Path contactPath = Paths.get("data", "contacts.txt");
        List<String> contactList = Files.readAllLines(contactPath);

                for(String contact : contactList) {
                    if(contact.toLowerCase().contains(userInput)){
                    System.out.println(contact);
                    }
                }
    }

    public static void mainMenu(){
        System.out.println("Enter an option (1, 2, 3, 4 or 5):");
        System.out.println("1. View Contacts");
        System.out.println("2. Add a new contact");
        System.out.println("3. Delete an existing contact");
        System.out.println("4. Exit.");
    }








}
