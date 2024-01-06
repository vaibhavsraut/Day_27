package com.day27;
import java.util.List;
import java.util.Scanner;

public class AddressBookMain {
    private static final String FILE_PATH = "contacts.txt";

    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        FileIO fileIO = new FileIO();

        List<Contact> contacts = fileIO.readContactsFromFile(FILE_PATH);
        addressBook.addContacts(contacts);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Address Book Menu");
            System.out.println("1. Add Contact");
            System.out.println("2. Edit Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. Search Contacts");
            System.out.println("5. View All Contacts");
            System.out.println("6. Save and Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addContact(scanner, addressBook);
                    break;
                case 2:
                    editContact(scanner, addressBook);
                    break;
                case 3:
                    deleteContact(scanner, addressBook);
                    break;
                case 4:
                    searchContacts(scanner, addressBook);
                    break;
                case 5:
                    viewAllContacts(addressBook);
                    break;
                case 6:
                    saveAndExit(addressBook, fileIO);
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addContact(Scanner scanner, AddressBook addressBook) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        Contact contact = new Contact(firstName, lastName, phoneNumber, email);
        addressBook.addContact(contact);

        System.out.println("Contact added successfully.");
    }

    private static void editContact(Scanner scanner, AddressBook addressBook) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        List<Contact> matchingContacts = addressBook.searchByFirstName(firstName);
        matchingContacts.retainAll(addressBook.searchByLastName(lastName));

        if (matchingContacts.isEmpty()) {
            System.out.println("Contact not found.");
            return;
        }

        System.out.println("Select the contact you want to edit:");
        for (int i = 0; i < matchingContacts.size(); i++) {
            System.out.println((i + 1) + ". " + matchingContacts.get(i));
        }

        System.out.print("Enter the index of the contact: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 1 || index > matchingContacts.size()) {
            System.out.println("Invalid index.");
            return;
        }

        Contact selectedContact = matchingContacts.get(index - 1);

        System.out.print("Enter new first name: ");
        String newFirstName = scanner.nextLine();

        System.out.print("Enter new last name: ");
        String newLastName = scanner.nextLine();

        System.out.print("Enter new phone number: ");
        String newPhoneNumber = scanner.nextLine();

        System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine();

        Contact newContact = new Contact(newFirstName, newLastName, newPhoneNumber, newEmail);
        addressBook.editContact(selectedContact, newContact);
        System.out.println("Contact edited successfully.");
    }

    private static void deleteContact(Scanner scanner, AddressBook addressBook) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        List<Contact> matchingContacts = addressBook.searchByFirstName(firstName);
        matchingContacts.retainAll(addressBook.searchByLastName(lastName));

        if (matchingContacts.isEmpty()) {
            System.out.println("Contact not found.");
            return;
        }

        System.out.println("Select the contact you want to delete:");
        for (int i = 0; i < matchingContacts.size(); i++) {
            System.out.println((i + 1) + ". " + matchingContacts.get(i));
        }

        System.out.print("Enter the index of the contact: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (index < 1 || index > matchingContacts.size()) {
            System.out.println("Invalid index.");
            return;
        }

        Contact selectedContact = matchingContacts.get(index - 1);
        addressBook.deleteContact(selectedContact);

        System.out.println("Contact deleted successfully.");
    }

    private static void searchContacts(Scanner scanner, AddressBook addressBook) {
        System.out.println("Search Contacts");
        System.out.println("1. Search by First Name");
        System.out.println("2. Search by Last Name");
        System.out.println("3. Search by Phone Number");
        System.out.println("4. Search by Email");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        List<Contact> matchingContacts;

        switch (choice) {
            case 1:
                System.out.print("Enter first name: ");
                String firstName = scanner.nextLine();
                matchingContacts = addressBook.searchByFirstName(firstName);
                break;
            case 2:
                System.out.print("Enter last name: ");
                String lastName = scanner.nextLine();
                matchingContacts = addressBook.searchByLastName(lastName);
                break;
            case 3:
                System.out.print("Enter phone number: ");
                String phoneNumber = scanner.nextLine();
                matchingContacts = addressBook.searchByPhoneNumber(phoneNumber);
                break;
            case 4:
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                matchingContacts = addressBook.searchByEmail(email);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        if (matchingContacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            System.out.println("Matching Contacts:");
            for (Contact contact : matchingContacts) {
                System.out.println(contact);
            }
        }
    }

    private static void viewAllContacts(AddressBook addressBook) {
        List<Contact> contacts = addressBook.getAllContacts();
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            System.out.println("All Contacts:");
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    private static void saveAndExit(AddressBook addressBook, FileIO fileIO) {
        List<Contact> contacts = addressBook.getAllContacts();
        fileIO.writeContactsToFile(contacts, FILE_PATH);
        System.out.println("Address book saved to file. Exiting...");
    }

}

