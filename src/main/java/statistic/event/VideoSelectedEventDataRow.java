package main.java.statistic.event;


import main.java.ad.Advertisement;

import java.time.LocalDate;
import java.util.List;

public class VideoSelectedEventDataRow implements EventDataRow {
    private List<Advertisement> optimalVideoSet;
    private final long amount;
    private final int totalDuration;

    private final LocalDate currentDate;
    public VideoSelectedEventDataRow(List<Advertisement> optimalVideoSet, long amount, int totalDuration) {
        this.optimalVideoSet =optimalVideoSet;
        this.amount =amount;
        this.totalDuration = totalDuration;
        currentDate = LocalDate.now();
    }

    @Override
    public EventType getType() {
        return EventType.SELECTED_VIDEOS;
    }

    @Override
    public LocalDate getDate() {
        return currentDate;
    }

    @Override
    public int getTime() {
        return totalDuration;
    }

    public long getAmount() {
        return amount;
    }
}
