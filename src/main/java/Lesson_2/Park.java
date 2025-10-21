package Lesson_2;

import java.util.ArrayList;
import java.util.List;

public class Park {
    private final String parkName;
    private final List<Attraction> attractions;

    public Park(String parkName) {
        this.parkName = parkName;
        this.attractions = new ArrayList<>();
    }

    public void addAttraction(String name, String workingHours, double cost) {
        attractions.add(new Attraction(name, workingHours, cost));
    }

    public void displayAllAttractions() {
        System.out.println("Парк: " + parkName);
        System.out.println("Список аттракционов:");
        for (Attraction attraction : attractions) {
            attraction.displayAttractionInfo();
            System.out.println("---");
        }
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

        public void displayAttractionInfo() {
            System.out.println("Аттракцион: " + attractionName);
            System.out.println("Время работы: " + workingHours);
            System.out.println("Стоимость: " + cost + " руб.");
        }
    }
}