package Lesson_3.exercise_1;

public class Cat extends Animal {
    private static int catCount = 0;
    private boolean isFull;
    private int maxRunDistance = 200;

    public Cat(String name) {
        super(name);
        this.isFull = false;
        catCount++;
    }

    public static int getCatCount() {
        return catCount;
    }

    public boolean getIsFull() {
        return isFull;
    }

    public void setIsFull(boolean isFull) {
        this.isFull = isFull;
    }

    public int getMaxRunDistance() {
        return maxRunDistance;
    }

    public void setMaxRunDistance(int maxRunDistance) {
        this.maxRunDistance = maxRunDistance;
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
        System.out.println(getName() + " не умеет плавать.");
    }

    public void eatFromBowl(Bowl bowl) {
        if (bowl.decreaseFood(10)) {
            isFull = true;
            System.out.println(getName() + " поел и теперь сыт.");
        } else {
            System.out.println(getName() + " не поел, в миске мало еды.");
        }
    }
}