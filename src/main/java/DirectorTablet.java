package main.java;

import main.java.ad.Advertisement;
import main.java.ad.StatisticAdvertisementManager;
import main.java.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


public class DirectorTablet {
    private final StatisticManager statisticManager = StatisticManager.getInstance();

    void printAdvertisementProfit(){
        Set<LocalDate> dates = statisticManager.advertisementProfitMap().keySet();
        List<LocalDate> dateList = new ArrayList<>(dates);
        Collections.sort(dateList);
        Collections.reverse(dateList);
        long total = 0L;
        for (LocalDate date: dateList) {
            System.out.print(date);
            System.out.print(" - ");
            Long profit = statisticManager.advertisementProfitMap().get(date);
            System.out.printf("%.2f", profit/100.00);
            total += profit;
            System.out.println();
        }
        System.out.printf("Total - %.2f", total/100.0);
        System.out.println();


    }
    void printCookWorkLoading(){
        Set<LocalDate> dates = statisticManager.CookWorkLoading().keySet();
        List<LocalDate> dateList = new ArrayList<>(dates);
        Collections.sort(dateList);
        Collections.reverse(dateList);

        for (LocalDate date : dateList) {
            System.out.println(date);
            Map<String, Long> cookTimeWorkMap = statisticManager.CookWorkLoading().get(date);
            List<String> nameList = new ArrayList<>(cookTimeWorkMap.keySet());
            Collections.sort(nameList);

            for (String cookName : nameList) {
                long timeWorkInSeconds = cookTimeWorkMap.get(cookName);
                int timeWorkInMinutes = (int) Math.ceilDiv(timeWorkInSeconds, 60);
                System.out.println(cookName + " - " + timeWorkInMinutes + " min");
            }
            System.out.println();
        }
    }

    public void printActiveVideoSet() {
        List<Advertisement> videoSet = StatisticAdvertisementManager.getInstance().getVideoSet(true);
        videoSet.sort(Comparator.comparing(o -> o.getName().toLowerCase()));

        for (Advertisement advertisement : videoSet) {
            System.out.println(advertisement.getName() + " - " + advertisement.getHits());
        }
    }

    public void printArchivedVideoSet() {
        List<Advertisement> videoSet = StatisticAdvertisementManager.getInstance().getVideoSet(false);
        videoSet.sort(Comparator.comparing(o -> o.getName().toLowerCase()));

        for (Advertisement advertisement : videoSet) {
            System.out.println(advertisement.getName());
        }
    }

}
