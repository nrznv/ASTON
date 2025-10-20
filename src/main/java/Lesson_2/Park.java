package Lesson_2;
public class Park {
    // Убираем все конструкторы и поля, оставляем только внутренний класс
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