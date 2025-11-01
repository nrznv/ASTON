package Lesson_3.exercise_1;

public class Animal {
    private static int animalCount = 0;
    private String name;

    public Animal(String name) {
        this.name = name;
        animalCount++;
    }

    public static int getAnimalCount() {
        return animalCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void run(int distance) {
        System.out.println(name + " бежит " + distance + " м.");
    }

    public void swim(int distance) {
        System.out.println(name + " плывет " + distance + " м.");
    }
}