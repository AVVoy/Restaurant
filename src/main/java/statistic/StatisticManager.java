package main.java.statistic;

import main.java.statistic.event.CookedOrderEventDataRow;
import main.java.statistic.event.EventDataRow;
import main.java.statistic.event.EventType;
import main.java.statistic.event.VideoSelectedEventDataRow;

import java.time.LocalDate;
import java.util.*;

public class StatisticManager {

    private static final StatisticManager ourInstance = new StatisticManager();

    public static StatisticManager getInstance() {
        return ourInstance;
    }

    private final StatisticStorage statisticStorage = new StatisticStorage();

    private StatisticManager() {
    }

    private static class StatisticStorage {
        private final Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        private StatisticStorage() {
            for (EventType type : EventType.values()) {
                this.storage.put(type, new ArrayList<>());
            }
        }

        private void put(EventDataRow data) {
            EventType type = data.getType();
            if (!this.storage.containsKey(type))
                throw new UnsupportedOperationException();

            this.storage.get(type).add(data);
        }

        private List<EventDataRow> get(EventType type) {
            if (!this.storage.containsKey(type))
                throw new UnsupportedOperationException();

            return this.storage.get(type);
        }
    }

    public void register(EventDataRow data) {
        this.statisticStorage.put(data);
    }



    public Map<LocalDate, Long> advertisementProfitMap() {
         Map<LocalDate, Long> profitForDay = new HashMap<>();
        List<EventDataRow> advertisement = statisticStorage.get(EventType.SELECTED_VIDEOS);
        for (EventDataRow edr : advertisement) {
            VideoSelectedEventDataRow videoSelected = (VideoSelectedEventDataRow) edr;
            LocalDate date = videoSelected.getDate();
            if (!profitForDay.containsKey(date)) {
                profitForDay.put(date, videoSelected.getAmount());
            } else {
                long total = profitForDay.get(date)+ videoSelected.getAmount();
                profitForDay.replace(date, total);
            }
        }
        return profitForDay;
    }

    public Map<LocalDate, Map<String, Long>> CookWorkLoading(){
        Map<LocalDate, Map<String, Long>> cookWorkLoading = new HashMap<>();
        Map<String, Long> workLoading;
        List<EventDataRow> cookWork = statisticStorage.get(EventType.COOKED_ORDER);
        for (EventDataRow edr : cookWork) {
            CookedOrderEventDataRow cookedOrder = (CookedOrderEventDataRow) edr;
            LocalDate date = cookedOrder.getDate();
            if (!cookWorkLoading.containsKey(date)) {
                cookWorkLoading.put(date, new HashMap<>());
                cookWorkLoading.get(date).put(cookedOrder.getCookName(), (long) cookedOrder.getTime());
            } else {
                workLoading = cookWorkLoading.get(date);
                if (!workLoading.containsKey(cookedOrder.getCookName())){
                    workLoading.put(cookedOrder.getCookName(), (long) cookedOrder.getTime());
                } else {
                    long totalWorkingTime = workLoading.get(cookedOrder.getCookName()) + cookedOrder.getTime();
                    workLoading.put(cookedOrder.getCookName(), totalWorkingTime);
                }
            }
        }
        return cookWorkLoading;
    }
}
