package petrbouda;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Task implements Callable<List<Contact>> {

    private final int NUM_OF_INTERATION_LIFESET = 200_000;

    private final int NUM_OF_INTERATION_GARBAGE = 500_000;

    /*
     * Number of iteration is variable to avoid optimizations (loop enrolling).
     */
    private final int index;

    /*
     * Make a difference between parallel running tasks.
     */
    private final String mark;

    /*
     * Have to be global to avoid Stack allocation.
     */
    private List<Contact> contacts = new ArrayList<>();

    Task(int index, String mark) {
        this.index = index;
        this.mark = mark;
    }

    @Override
    public List<Contact> call() {
        /*
         * Generation Garbage
         */
        for (int i = 0; i < (NUM_OF_INTERATION_GARBAGE + index); i++) {
            String garbage = "Garbage: " + i;
            System.out.println("Index: " + index + " Generated " + garbage);
        }

        /*
         * Live Set
         */
        for (int i = 0; i < (NUM_OF_INTERATION_LIFESET + index); i++) {
            String city = "My Beautiful City " + i + " | " + mark;
            String street = "The Best Street " + i + " | " + mark;

            Address address = new Address(city, street);

            String firstname = "My FirstName " + i + " | " + mark;
            String lastname = "My LastName " + i + " | " + mark;
            Contact contact = new Contact(firstname, lastname, address);
            contacts.add(contact);
        }

        return contacts;
    }
}
