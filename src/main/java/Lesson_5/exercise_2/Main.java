package Lesson_5.exercise_2;

public class Main {
    public static void main(String[] args) {
        ContactManager contactManager = new ContactManager();

        contactManager.addContact("Алексей Петров", "8-900-123-45-67");
        contactManager.addContact("Мария Иванова", "8-901-234-56-78");
        contactManager.addContact("Иван Сидоров", "8-902-345-67-89");
        contactManager.addContact("Алексей Петров", "8-903-456-78-90");
        contactManager.addContact("Ольга Козлова", "8-904-567-89-01");
        contactManager.addContact("Мария Иванова", "8-905-678-90-12");

        contactManager.showAllContacts();

        System.out.println();
        contactManager.findContact("Алексей Петров");
        contactManager.findContact("Несуществующий");

        System.out.println();
        System.out.println("Номера Марии: " + contactManager.getContactNumbers("Мария Иванова"));
        System.out.println("Всего контактов: " + contactManager.getContactCount());

        System.out.println();
        contactManager.removeContact("Иван Сидоров");

        System.out.println();
        contactManager.showAllContacts();
    }
}