public class Contacts {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Contacts(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }


    public String toString() {
        String fullName = firstName + " " + lastName;
        return String.format("%-" + 15 + "s | %-12s |", fullName, phoneNumber);
    }
    public String toString(int num) {
        String fullName = firstName + " " + lastName;
        return String.format("%-" + num + "s | %-12s |", fullName, phoneNumber);
    }

}
