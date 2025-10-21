package Lesson_2;

public class Product {
    private final String name;
    private final String productionDate;
    private final String manufacturer;
    private final String countryOfOrigin;
    private final double price;
    private final boolean isReserved;

    public Product(String name, String productionDate, String manufacturer,
                   String countryOfOrigin, double price, boolean isReserved) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.countryOfOrigin = countryOfOrigin;
        this.price = price;
        this.isReserved = isReserved;
    }

    public void printProductInfo() {
        System.out.println("Информация о товаре:");
        System.out.println("  Название: " + name);
        System.out.println("  Дата производства: " + productionDate);
        System.out.println("  Производитель: " + manufacturer);
        System.out.println("  Страна происхождения: " + countryOfOrigin);
        System.out.println("  Цена: " + price + " руб.");
        System.out.println("  Состояние бронирования: " +
                (isReserved ? "забронирован покупателем" : "свободен"));
        System.out.println();
    }
}