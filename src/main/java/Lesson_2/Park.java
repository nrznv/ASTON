package Lesson_2;

import java.util.ArrayList;
import java.util.List;

public class Park {
    private String parkName;
    private final List<Attraction> attractions;

    public Park(String parkName) {
        this.parkName = parkName;
        this.attractions = new ArrayList<>();
    }

    // ГЕТТЕР и СЕТТЕР для названия парка
    public String getParkName() { return parkName; }
    public void setParkName(String parkName) { this.parkName = parkName; }

    // МЕТОД ДОБАВЛЕНИЯ АТТРАКЦИОНА (этот метод ищет Main)
    public void addAttraction(String name, String workingHours, double cost) {
        attractions.add(new Attraction(name, workingHours, cost));
    }

    // МЕТОД ПОКАЗА ВСЕХ АТТРАКЦИОНОВ (этот метод ищет Main)
    public void displayAllAttractions() {
        System.out.println("Парк: " + parkName);
        System.out.println("Список аттракционов:");
        for (Attraction attraction : attractions) {
            attraction.displayAttractionInfo();
            System.out.println("---");
        }
    }

    // ГЕТТЕР для списка аттракционов (только для чтения)
    public List<Attraction> getAttractions() {
        return new ArrayList<>(attractions);
    }

    public static class Attraction {
        private final String attractionName;
        private final String workingHours;
        private final double cost;

        public Attraction(String attractionName, String workingHours, double cost) {
            this.attractionName = attractionName;
            this.workingHours = workingHours;
            this.cost = cost;
        }

        // ГЕТТЕРЫ для аттракциона
        public String getAttractionName() { return attractionName; }
        public String getWorkingHours() { return workingHours; }
        public double getCost() { return cost; }

        public void displayAttractionInfo() {
            System.out.println("Аттракцион: " + attractionName);
            System.out.println("Время работы: " + workingHours);
            System.out.println("Стоимость: " + cost + " руб.");
        }
    }
}