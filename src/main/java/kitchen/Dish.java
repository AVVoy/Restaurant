package main.java.kitchen;

public enum Dish {
    FISH(25),
    STEAK(30),
    SOUP(15),
    JUICE(5),
    WATER(3);

    private final int duration;

    public int getDuration() {
        return duration;
    }

    Dish(int duration) {
        this.duration = duration;
    }

    public static String allDishesToString() {
        StringBuilder result = new StringBuilder();

        for (Dish dish : Dish.values()) {
            if (result.isEmpty()) {
                result.append(dish.name());
            } else {
                result.append(", ").append(dish.name());
            }
        }
        return result.toString();
    }
}
