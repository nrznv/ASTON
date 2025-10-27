package Lesson_4;

public class ArrayProcessor {

    public static int processArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4) {
            throw new MyArraySizeException("Массив должен быть 4x4, но количество строк: " + array.length);
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException("Массив должен быть 4x4, но в строке " + i + " количество столбцов: " + array[i].length);
            }
        }

        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j, array[i][j]);
                }
            }
        }

        return sum;
    }

    public static void demonstrateIndexOutOfBounds() {
        int[] array = {1, 2, 3};

        try {
            System.out.println("Пытаемся получить элемент с индексом 5: " + array[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймано ArrayIndexOutOfBoundsException: " + e.getMessage());
        }
    }
}