package revolut;

import java.util.ArrayList;

public class Contact {
    private String name;
    ArrayList<String> phoneContacts = new ArrayList<String>();

    //create an instance of a contact
    public Contact(String name) {
        phoneContacts.add("Jack");
        phoneContacts.add("Shane");
        phoneContacts.add("Tim");
        this.name = name;

    }

    //check if the contact is in the revolut contacts list
    public boolean isValidRevolutContact(String name) {
        return phoneContacts.contains(name);
    }
}
