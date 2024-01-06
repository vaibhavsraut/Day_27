package com.day27;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileIO {
    public static List<Contact> readContactsFromFile(String filePath) {
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String firstName = parts[0].trim();
                    String lastName = parts[1].trim();
                    String phoneNumber = parts[2].trim();
                    String email = parts[3].trim();
                    Contact contact = new Contact(firstName, lastName, phoneNumber, email);
                    contacts.add(contact);
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to read contacts from file: " + e.getMessage());
        }
        return contacts;
    }

    public static void writeContactsToFile(List<Contact> contacts, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Contact contact : contacts) {
                String line = contact.getFirstName() + "," +
                        contact.getLastName() + "," +
                        contact.getPhoneNumber() + "," +
                        contact.getEmail();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to write contacts to file: " + e.getMessage());
        }
    }
}