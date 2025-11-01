package Lesson_3.exercise_1;

public class Dog extends Animal {
    private static int dogCount = 0;
    private int maxRunDistance = 500;
    private int maxSwimDistance = 10;

    public Dog(String name) {
        super(name);
        dogCount++;
    }

    public static int getDogCount() {
        return dogCount;
    }

    public int getMaxRunDistance() {
        return maxRunDistance;
    }

    public void setMaxRunDistance(int maxRunDistance) {
        this.maxRunDistance = maxRunDistance;
    }

    public int getMaxSwimDistance() {
        return maxSwimDistance;
    }

    public void setMaxSwimDistance(int maxSwimDistance) {
        this.maxSwimDistance = maxSwimDistance;
    }

    @Override
    public void run(int distance) {
        if (distance <= maxRunDistance) {
            System.out.println(getName() + " пробежал " + distance + " м.");
        } else {
            System.out.println(getName() + " не может пробежать " + distance + " м.");
        }
    }

    @Override
    public void swim(int distance) {
        if (distance <= maxSwimDistance) {
            System.out.println(getName() + " проплыл " + distance + " м.");
        } else {
            System.out.println(getName() + " не может проплыть " + distance + " м.");
        }
    }
}