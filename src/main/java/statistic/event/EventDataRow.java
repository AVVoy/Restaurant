package main.java.statistic.event;

import java.time.LocalDate;

public interface EventDataRow {
    EventType getType();
    LocalDate getDate();
    int getTime();

}
