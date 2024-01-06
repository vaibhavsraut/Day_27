package com.day27;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void addContacts(List<Contact> newContacts) {
        contacts.addAll(newContacts);
    }

    public void editContact(Contact selectedContact, Contact newContact) {
        int index = contacts.indexOf(selectedContact);
        if (index != -1) {
            contacts.set(index, newContact);
        }
    }

    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts);
    }

    public void deleteContact(Contact selectedContact) {
        contacts.remove(selectedContact);
    }

    public List<Contact> searchByFirstName(String firstName) {
        return contacts.stream()
                .filter(contact -> contact.getFirstName().equals(firstName))
                .collect(Collectors.toList());
    }

    public List<Contact> searchByLastName(String lastName) {
        return contacts.stream()
                .filter(contact -> contact.getLastName().equals(lastName))
                .collect(Collectors.toList());
    }

    public List<Contact> searchByPhoneNumber(String phoneNumber) {
        return contacts.stream()
                .filter(contact -> contact.getPhoneNumber().equals(phoneNumber))
                .collect(Collectors.toList());
    }

    public List<Contact> searchByEmail(String email) {
        return contacts.stream()
                .filter(contact -> contact.getEmail().equals(email))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Contact contact : contacts) {
            sb.append(contact.toString()).append("\n");
        }
        return sb.toString();
    }
}