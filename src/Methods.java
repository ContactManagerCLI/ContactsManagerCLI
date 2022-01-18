
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



public class Methods {

    //1. View contacts.
//2. Add a new contact.
//3. Search a contact by name.
//4. Delete an existing contact.
//5. Exit.
//Enter an option (1, 2, 3, 4 or 5):
    public static ArrayList<Contacts> sideList = new ArrayList<>();

    public static void addContact() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean confirm = true;
        while (confirm) {
// ================================First, Last Name ==================================================================
            System.out.println("Enter first name: ");
            String firstName = scanner.nextLine();
            System.out.println("Enter last name: ");
            String lastName = scanner.nextLine();

            Path contactPath = Paths.get("data", "contacts.txt");
            List<String> contactList = Files.readAllLines(contactPath);

            boolean noMore = false;
            for (String contact : contactList) {
                if (contact.toLowerCase().contains(firstName.toLowerCase()) && contact.toLowerCase().contains(lastName.toLowerCase())) {
                    System.out.println("There's already a contact named " + firstName + lastName + "." + "Do you want to overwrite it? (Yes/No)");
                    String yesNo = scanner.nextLine();
                    if (yesNo.equalsIgnoreCase("yes") || yesNo.equalsIgnoreCase("y")) {
                        break;
                    } else {
                        noMore = true;
                        addContact();
                        break;
                    }
                }
            }
            if(noMore){
                break;
            }
// ================================PHONE NUMBER==================================================================
            boolean tryAgain = true;
            while (tryAgain) {
                System.out.println("Enter a phone number: ");
                String phoneNumber = scanner.nextLine();
                String bonusNumber;
                if (phoneNumber.length() == 7) {
                    bonusNumber = phoneNumber.replaceFirst("(\\d{3})(\\d+)", "$1-$2");

                    Files.write(
                            Paths.get("data", "contacts.txt"),
                            Arrays.asList(firstName + " " + lastName + " |" + bonusNumber), // list with one item
                            StandardOpenOption.APPEND
                    );
                    sideList.add(new Contacts(firstName, lastName, phoneNumber));
                    System.out.println(firstName + " " + lastName + " has been added to your contacts.");
                    tryAgain = false;
                    confirm = false;
                } else if (phoneNumber.length() == 10) {
                    bonusNumber = phoneNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3");
                    Files.write(
                            Paths.get("data", "contacts.txt"),
                            Arrays.asList(firstName + " " + lastName + " " + bonusNumber), // list with one item
                            StandardOpenOption.APPEND
                    );
                    sideList.add(new Contacts(firstName, lastName, phoneNumber));
                    System.out.println(firstName + " " + lastName + " has been added to your contacts.");
                    tryAgain = false;
                    confirm = false;
                } else {
                    System.out.println("That's not a phone number");
                    tryAgain = true;
                }
            }

        }
    }


    public static void viewAllContacts() throws FileNotFoundException {
//        Path contactPath = Paths.get("data", "contacts.txt");
//        List<String> contactList = Files.readAllLines(contactPath);
//        System.out.println("Name | Phone Number");
//        System.out.println("------------------");
//        for(String obj : contactList){
//            System.out.println(obj);
        //        }
        Scanner input = new Scanner(new File("data/contacts.txt"));
        Contacts[] contacts = new Contacts[0];
        while(input.hasNext()) {
            String firstName = input.next();
            String lastName = input.next();
            String phoneNumber = input.next();
            Contacts newContact = new Contacts(firstName, lastName, phoneNumber);
            contacts = addContact(contacts, newContact);
        }
        String name = "Name";
        String phoneNumber = "Phone Number";
        System.out.printf("%-15s | %-12s |\n ------------------------------\n", name, phoneNumber);
        for (int i = 0; i < contacts.length; i++) {
            System.out.println(contacts[i].toString());
        }

        }



    private static Contacts[] addContact(Contacts[] contacts, Contacts contactsToAdd) {
        Contacts[] newContacts = new Contacts[contacts.length + 1];
        System.arraycopy(contacts, 0, newContacts, 0, contacts.length);
        newContacts[newContacts.length - 1] = contactsToAdd;

        return newContacts;
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


    public static void searchContactName() throws IOException {
        System.out.println("Enter a name or phone number: ");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().trim().toLowerCase();

        Path contactPath = Paths.get("data", "contacts.txt");
        List<String> contactList = Files.readAllLines(contactPath);

        boolean notFound = true;
        for (String contact : contactList) {
            if (contact.toLowerCase().contains(userInput.toLowerCase())) {
                System.out.println(contact);
                notFound = false;
            }
        }
        if(notFound){
            System.out.println("Contact not found.");
        }
    }

    public static void mainMenu() {
        System.out.println("Enter an option (1, 2, 3, 4 or 5):");
        System.out.println("1. View Contacts");
        System.out.println("2. Add a new contact");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete an existing contact");
        System.out.println("5. Exit.");
    }


}
