package main.java.statistic.event;

import main.java.kitchen.Dish;

import java.time.LocalDate;
import java.util.List;

public class CookedOrderEventDataRow implements EventDataRow{

    private final String cookName;
    private final int cookingTimeSeconds;
    private final LocalDate currentDate;

    public CookedOrderEventDataRow(String tabletName, String cookName, int cookingTimeSeconds, List<Dish> cookingDishes){
        this.cookName = cookName;
        this.cookingTimeSeconds = cookingTimeSeconds;
        currentDate = LocalDate.now();
    }

    @Override
    public EventType getType() {
        return EventType.COOKED_ORDER;
    }

    @Override
    public LocalDate getDate() {
        return currentDate;
    }

    @Override
    public int getTime() {
        return cookingTimeSeconds;
    }

    public String getCookName() {
        return cookName;
    }
}
