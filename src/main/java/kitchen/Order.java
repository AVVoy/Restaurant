package main.java.kitchen;

import main.java.ConsoleHelper;
import main.java.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;

    public List<Dish> getDishes() {
        return dishes;
    }

    public Tablet getTablet() {
        return tablet;
    }

    protected List<Dish> dishes;

    protected void initDishes() throws IOException {
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (dishes.isEmpty()) return result.toString();
        result.append("Your order: [").append(dishes.getFirst());

        for (int i = 1; i < dishes.size(); i++) {
            result.append(", ").append(dishes.get(i).name());
        }
        result.append("] of ").append(tablet);
        result.append(", cooking time ").append(getTotalCookingTime()).append("min");
        return result.toString();
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    public int getTotalCookingTime() {
        int cookingTime = 0;
        for (Dish dish : dishes) {
            cookingTime += dish.getDuration();
        }
        return cookingTime;
    }
}
