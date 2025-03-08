package main.java.statistic.event;

import java.time.LocalDate;

public class NoAvailableVideoEventDataRow implements EventDataRow {

    private final int totalDuration;
    private final LocalDate currentDate;

    public NoAvailableVideoEventDataRow(int totalDuration){
        this.totalDuration = totalDuration;
        currentDate = LocalDate.now();
    }

    @Override
    public EventType getType() {
        return EventType.NO_AVAILABLE_VIDEO;
    }

    @Override
    public LocalDate getDate() {
        return currentDate;
    }

    @Override
    public int getTime() {
        return totalDuration;
    }
}
