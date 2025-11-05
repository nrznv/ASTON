package Lesson_5.exercise_1;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("ЗАДАНИЕ 1: УПРАВЛЕНИЕ СТУДЕНТАМИ");

        Set<Student> students = new HashSet<>();
        students.add(new Student("Иван Иванов", "ГР-1", 1, Arrays.asList(4, 5, 3, 4)));
        students.add(new Student("Петр Петров", "ГР-1", 1, Arrays.asList(3, 3, 2, 3)));
        students.add(new Student("Мария Сидорова", "ГР-2", 2, Arrays.asList(5, 5, 5, 5)));
        students.add(new Student("Анна Козлова", "ГР-2", 2, Arrays.asList(2, 2, 3, 2)));
        students.add(new Student("Сергей Смирнов", "ГР-3", 3, Arrays.asList(4, 4, 4, 3)));

        System.out.println("Все студенты:");
        for (Student student : students) {
            System.out.println(student);
        }

        StudentManager.removeFailedStudents(students);
        System.out.println("\nПосле удаления отчисленных:");
        for (Student student : students) {
            System.out.println(student);
        }

        StudentManager.promoteStudents(students);
        System.out.println("\nПосле перевода на следующий курс:");
        for (Student student : students) {
            System.out.println(student);
        }

        StudentManager.printStudents(students, 2);
        StudentManager.printStudents(students, 3);
    }
}