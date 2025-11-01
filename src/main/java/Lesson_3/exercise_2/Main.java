package Lesson_3.exercise_2;

public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle(5, "Красный", "Черный");
        Shape rectangle = new Rectangle(4, 6, "Синий", "Белый");
        Shape triangle = new Triangle(3, 4, 5, "Зеленый", "Желтый");

        System.out.println("КРУГ");
        circle.printInfo();

        System.out.println("ПРЯМОУГОЛЬНИК");
        rectangle.printInfo();

        System.out.println("ТРЕУГОЛЬНИК");
        triangle.printInfo();
    }
}