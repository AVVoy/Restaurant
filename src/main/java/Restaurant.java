package main.java;

import main.java.kitchen.Cook;
import main.java.kitchen.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;

    private static final LinkedBlockingQueue<Order> ORDER_QUEUE = new LinkedBlockingQueue<>(200);

    public static void main(String[] ignoredArgs) {

        List<Tablet> tablets = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tablets.add(new Tablet(i + 1));
            tablets.get(i).setQueue(ORDER_QUEUE);
        }

        Cook cook1 = new Cook("Amigo");
        cook1.setQueue(ORDER_QUEUE);
        Cook cook2 = new Cook("Diego");
        cook2.setQueue(ORDER_QUEUE);

        Thread cookThread1 = new Thread(cook1);
        Thread cookThread2 = new Thread(cook2);
        cookThread1.start();
        cookThread2.start();


        Thread thread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        thread.start();

        try {
            Thread.sleep(1000);
            thread.interrupt();
            thread.join();
            Thread.sleep(1000);
        } catch (InterruptedException ignore) {
        }

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkLoading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
