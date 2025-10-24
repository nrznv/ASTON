package Lesson_3.exercise_1;

public class Bowl {
    private int foodAmount;

    public Bowl(int foodAmount) {
        this.foodAmount = Math.max(foodAmount, 0);
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(int foodAmount) {
        this.foodAmount = Math.max(foodAmount, 0);
    }

    public boolean decreaseFood(int amount) {
        if (foodAmount >= amount) {
            foodAmount -= amount;
            return true;
        }
        return false;
    }

    public void addFood(int amount) {
        foodAmount += amount;
        System.out.println("В миску добавлено " + amount + " еды. Теперь в миске: " + foodAmount);
    }
}