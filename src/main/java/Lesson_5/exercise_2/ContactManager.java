package Lesson_5.exercise_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactManager {
    private Map<String, List<String>> contacts;

    public ContactManager() {
        contacts = new HashMap<>();
    }

    public void addContact(String name, String phoneNumber) {
        if (contacts.containsKey(name)) {
            List<String> numbers = contacts.get(name);
            if (!numbers.contains(phoneNumber)) {
                numbers.add(phoneNumber);
            }
        } else {
            List<String> numbers = new ArrayList<>();
            numbers.add(phoneNumber);
            contacts.put(name, numbers);
        }
    }

    public List<String> getContactNumbers(String name) {
        return contacts.get(name);
    }

    public void findContact(String name) {
        List<String> numbers = contacts.get(name);
        if (numbers != null && !numbers.isEmpty()) {
            System.out.println("Найден контакт: " + name);
            System.out.println("Номера: " + numbers);
        } else {
            System.out.println("Контакт не найден: " + name);
        }
    }

    public void showAllContacts() {
        System.out.println("Все контакты:");
        for (Map.Entry<String, List<String>> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void removeContact(String name) {
        if (contacts.containsKey(name)) {
            contacts.remove(name);
            System.out.println("Контакт удален: " + name);
        } else {
            System.out.println("Контакт не найден для удаления: " + name);
        }
    }

    public int getContactCount() {
        return contacts.size();
    }
}