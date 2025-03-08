import ad.Advertisement;
import ad.StatisticAdvertisementManager;
import statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {

    void printAdvertisementProfit(){
        List<Date> dateList =new ArrayList<>();
        for (Date date : statisticManager.advertisementProfit().keySet()) {
            dateList.add(date);
        }
        Collections.sort(dateList);
        Collections.reverse(dateList);
        Long total = 0L;
        for (Date date: dateList) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-YYYY", Locale.ENGLISH);
            String data = dateFormat.format(date);
            System.out.print(data);
            System.out.print(" - ");
            Long profit = statisticManager.advertisementProfit().get(date);
            System.out.printf("%.2f", profit/100.00);
            total += profit;
            System.out.println();
        }
        System.out.printf("Total - %.2f", total/100.0);
        System.out.println();


    }
    void printCookWorkloading(){
        List<Date> dateList =new ArrayList<>();
        for (Date date : statisticManager.CookWorkloading().keySet()) {
            dateList.add(date);
        }
        Collections.sort(dateList);
        Collections.reverse(dateList);

        for (Date date : dateList) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-YYYY", Locale.ENGLISH);
            String data = dateFormat.format(date);
            System.out.println(data);
            Map<String, Long> cookTimeWork = statisticManager.CookWorkloading().get(date);
            List<String> nameList = new ArrayList<>();
            for (String name : cookTimeWork.keySet()) {
                nameList.add(name);
            }
            Collections.sort(nameList);

            for (String cookName : nameList) {
                Long timeWorkInSeconds = cookTimeWork.get(cookName);
                int timeWorkInMinutes = (int) Math.ceil(timeWorkInSeconds/60);
                System.out.println(cookName + " - " + timeWorkInMinutes + " min");
            }
            System.out.println();
        }
    }
    public void printActiveVideoSet() {
        List<Advertisement> videoSet = StatisticAdvertisementManager.getInstance().getVideoSet(true);
        Collections.sort(videoSet, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
            }
        });

        for (Advertisement advertisement : videoSet) {
            System.out.println(advertisement.getName() + " - " + advertisement.getHits());
        }
    }

    public void printArchivedVideoSet() {
        List<Advertisement> videoSet = StatisticAdvertisementManager.getInstance().getVideoSet(false);
        Collections.sort(videoSet, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
            }
        });

        for (Advertisement advertisement : videoSet) {
            System.out.println(advertisement.getName());
        }
    }

    private StatisticManager statisticManager = StatisticManager.getInstance();

}
