package Lesson_6;

public class Calculator {

    public long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Факториал не определён для отрицательных чисел");
        if (n == 0 || n == 1) return 1;
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public int add(int a, int b) { return a + b; }
    public int subtract(int a, int b) { return a - b; }
    public int multiply(int a, int b) { return a * b; }
    public int divide(int a, int b) {
        if (b == 0) throw new ArithmeticException("Деление на ноль");
        return a / b;
    }
}