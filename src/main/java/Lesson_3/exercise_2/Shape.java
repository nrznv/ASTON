package Lesson_3.exercise_2;

public interface Shape {
    String getFillColor();
    void setFillColor(String fillColor);
    String getBorderColor();
    void setBorderColor(String borderColor);

    default double calculatePerimeter() {
        return 0;
    }

    default double calculateArea() {
        return 0;
    }

    default void printInfo() {
        System.out.println("Периметр: " + calculatePerimeter());
        System.out.println("Площадь: " + calculateArea());
        System.out.println("Цвет фона: " + getFillColor());
        System.out.println("Цвет границ: " + getBorderColor());
        System.out.println();
    }
}