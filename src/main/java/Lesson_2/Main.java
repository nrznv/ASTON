package Lesson_2;
public class Main {
    public static void main(String[] args) {

        System.out.println("Демонстрация работы класса Product");
        Product[] productsArray = new Product[5];

        productsArray[0] = new Product("Samsung S25 Ultra", "01.02.2025",
                "Samsung Corp.", "Korea", 5599, true);
        productsArray[1] = new Product("iPhone 16 Pro", "15.01.2025",
                "Apple Inc.", "USA", 6499, false);
        productsArray[2] = new Product("Xiaomi Mi 14", "10.12.2024",
                "Xiaomi Corporation", "China", 3899, true);
        productsArray[3] = new Product("MacBook Air M3", "20.03.2025",
                "Apple Inc.", "USA", 12999, false);
        productsArray[4] = new Product("Sony WH-1000XM5", "05.11.2024",
                "Sony Corporation", "Japan", 2999, true);

        for (int i = 0; i < productsArray.length; i++) {
            System.out.println("Товар #" + (i + 1) + ":");
            productsArray[i].printProductInfo();
        }

        System.out.println("Демонстрация работы класса Park");

        // Создаем аттракционы напрямую через статический внутренний класс
        Park.Attraction attraction1 = new Park.Attraction("Американские горки", "10:00-20:00", 350);
        Park.Attraction attraction2 = new Park.Attraction("Колесо обозрения", "09:00-22:00", 200);
        Park.Attraction attraction3 = new Park.Attraction("Комната страха", "12:00-23:00", 150);

        System.out.println("Информация об аттракционах парка:");
        attraction1.displayAttractionInfo();
        System.out.println();
        attraction2.displayAttractionInfo();
        System.out.println();
        attraction3.displayAttractionInfo();

        System.out.println("Задание выполнено");
    }
}