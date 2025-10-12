package Lesson_1;
import java.util.Arrays;

public class Lesson1 {

    public static void main(String[] args) {
        // Тестирование всех методов
        System.out.println("Задание 1");
        printThreeWords();

        System.out.println("\nЗадание 2");
        checkSumSign(5, -3);
        checkSumSign(-10, -5);

        System.out.println("\nЗадание 3");
        printColor(-5);
        printColor(50);
        printColor(150);

        System.out.println("\nЗадание 4");
        compareNumbers(10, 20);
        compareNumbers(20, 10);

        System.out.println("\nЗадание 5");
        System.out.println("Сумма в пределах 10-20: " + checkSumRange(5, 10));
        System.out.println("Сумма в пределах 10-20: " + checkSumRange(15, 5));

        System.out.println("\nЗадание 6");
        checkNumberSign(-5);
        checkNumberSign(0);
        checkNumberSign(10);

        System.out.println("\nЗадание 7");
        System.out.println("Число отрицательное: " + isNegative(-5));
        System.out.println("Число отрицательное: " + isNegative(0));
        System.out.println("Число отрицательное: " + isNegative(10));

        System.out.println("\nЗадание 8");
        printStringMultipleTimes("Hello Java!", 3);

        System.out.println("\nЗадание 9");
        System.out.println("2024 високосный: " + isLeapYear(2024));
        System.out.println("2023 високосный: " + isLeapYear(2023));
        System.out.println("2000 високосный: " + isLeapYear(2000));
        System.out.println("1900 високосный: " + isLeapYear(1900));

        System.out.println("\nЗадание 10");
        int[] binaryArray = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.print("Исходный массив: ");
        printArray(binaryArray);
        invertArray(binaryArray);
        System.out.print("Инвертированный массив: ");
        printArray(binaryArray);

        System.out.println("\nЗадание 11");
        int[] hundredArray = createSequenceArray(100);
        System.out.print("Массив 1..100: ");
        printArray(hundredArray);

        System.out.println("\nЗадание 12");
        int[] numbers = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.print("Исходный массив: ");
        printArray(numbers);
        multiplyLessThanSix(numbers);
        System.out.print("После умножения: ");
        printArray(numbers);

        System.out.println("\nЗадание 13");
        int[][] squareArray = createSquareArray(5);
        System.out.println("Квадратный массив с диагоналями:");
        print2DArray(squareArray);

        System.out.println("\nЗадание 14");
        int[] customArray = createArray(5, 42);
        System.out.print("Массив с одинаковыми значениями: ");
        printArray(customArray);
    }

    // 1. Метод для печати трех слов
    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    // 2. Метод для проверки суммы
    public static void checkSumSign(int a, int b) {
        int sum = a + b;

        if (sum >= 0) {
            System.out.println("Сумма " + a + " + " + b + " = " + sum + " положительная");
        } else {
            System.out.println("Сумма " + a + " + " + b + " = " + sum + " отрицательная");
        }
    }

    // 3. Метод для определения цвета
    public static void printColor(int value) {
        if (value <= 0) {
            System.out.println("Значение " + value + " - Красный");
        } else if (value <= 100) {
            System.out.println("Значение " + value + " - Желтый");
        } else {
            System.out.println("Значение " + value + " - Зеленый");
        }
    }

    // 4. Метод для сравнения чисел
    public static void compareNumbers(int a, int b) {
        if (a >= b) {
            System.out.println(a + " >= " + b);
        } else {
            System.out.println(a + " < " + b);
        }
    }

    // 5. Метод проверки суммы в диапазоне
    public static boolean checkSumRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    // 6. Метод проверки знака числа
    public static void checkNumberSign(int number) {
        if (number >= 0) {
            System.out.println(number + " - положительное число");
        } else {
            System.out.println(number + " - отрицательное число");
        }
    }

    // 7. Метод проверки отрицательности числа
    public static boolean isNegative(int number) {
        return number < 0;
    }

    // 8. Метод печати строки несколько раз
    public static void printStringMultipleTimes(String text, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(text);
        }
    }

    // 9. Метод проверки високосного года
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // 10. Метод инверсии массива
    public static void invertArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (array[i] == 0) ? 1 : 0;
        }
    }

    // 11. Метод создания массива последовательных чисел
    public static int[] createSequenceArray(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    // 12. Метод умножения чисел меньше 6
    public static void multiplyLessThanSix(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }
    }

    // 13. Метод создания квадратного массива с диагоналями
    public static int[][] createSquareArray(int size) {
        int[][] array = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j || i == size - 1 - j) {
                    array[i][j] = 1;
                }
            }
        }
        return array;
    }

    // 14. Метод создания массива с одинаковыми значениями
    public static int[] createArray(int len, int initialValue) {
        int[] array = new int[len];
        Arrays.fill(array, initialValue);
        return array;
    }

    // Вспомогательные методы для вывода массивов
    public static void printArray(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void print2DArray(int[][] array) {
        for (int[] row : array) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}