package statistic;

import statistic.event.CookedOrderEventDataRow;
import statistic.event.EventDataRow;
import statistic.event.EventType;
import statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class StatisticManager {

    private static StatisticManager ourInstance = new StatisticManager();

    public static StatisticManager getInstance() {
        return ourInstance;
    }

    private StatisticStorage statisticStorage = new StatisticStorage();

    private StatisticManager() {
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        private StatisticStorage() {
            for (EventType type : EventType.values()) {
                this.storage.put(type, new ArrayList<EventDataRow>());
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



    public Map<Date, Long> advertisementProfit() {
         Map<Date, Long> profitForDay = new HashMap<>();
        List<EventDataRow> advertisement = statisticStorage.get(EventType.SELECTED_VIDEOS);
        for (EventDataRow edr : advertisement) {
            VideoSelectedEventDataRow videoSelected = (VideoSelectedEventDataRow) edr;
            Date date = videoSelected.getDate();
            date = new Date(date.getYear(), date.getMonth(), date.getDate());

            if (!profitForDay.containsKey(date)) {
                profitForDay.put(date, videoSelected.getAmount());
            } else {
                long total = profitForDay.get(date)+ videoSelected.getAmount();
                profitForDay.replace(date, total);
            }
        }
        return profitForDay;
    }

    public Map<Date, Map<String, Long>> CookWorkloading(){
        Map<Date, Map<String, Long>> cookWorkLoading = new HashMap<>();
        Map<String, Long> workLoading = new HashMap<>();
        List<EventDataRow> cookWork = statisticStorage.get(EventType.COOKED_ORDER);
        for (EventDataRow edr : cookWork) {
            //statistic allTime
            CookedOrderEventDataRow cookedOrder = (CookedOrderEventDataRow) edr;
            //Date from order
            Date date = cookedOrder.getDate();
            date = new Date(date.getYear(), date.getMonth(), date.getDate());
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
