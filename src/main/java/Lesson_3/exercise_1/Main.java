package Lesson_3.exercise_1;

public class Main {
    public static void main(String[] args) {
        Dog dog1 = new Dog("Цербер");
        Dog dog2 = new Dog("Фенрир");
        Cat cat1 = new Cat("Локи");
        Cat cat2 = new Cat("Фрейя");
        Cat cat3 = new Cat("Бастет");

        dog1.run(150);
        dog1.run(600);
        dog1.swim(5);
        dog1.swim(15);

        cat1.run(100);
        cat1.run(250);
        cat1.swim(10);

        Bowl bowl = new Bowl(25);
        Cat[] cats = {cat1, cat2, cat3};

        System.out.println("КОТЫ ЕДЯТ ИЗ МИСКИ");
        for (Cat cat : cats) {
            cat.eatFromBowl(bowl, 10);
        }

        System.out.println("ПРОВЕРКА СЫТОСТИ");
        for (Cat cat : cats) {
            System.out.println(cat.getName() + " сытость: " + cat.getIsFull());
        }

        System.out.println("ДОБАВЛЯЕМ ЕДУ");
        bowl.addFood(20);
        cat1.eatFromBowl(bowl, 10);

        System.out.println("СТАТИСТИКА");
        System.out.println("Всего животных: " + Animal.getAnimalCount());
        System.out.println("Собак: " + Dog.getDogCount());
        System.out.println("Котов: " + Cat.getCatCount());
    }
}