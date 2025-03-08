package main.java.kitchen;

import main.java.ConsoleHelper;
import main.java.statistic.StatisticManager;
import main.java.statistic.event.CookedOrderEventDataRow;

import java.util.concurrent.LinkedBlockingQueue;

public class Cook implements Runnable {
    private final String name;

    private boolean busy = false;

    public boolean isBusy() {
        return busy;
    }

    private LinkedBlockingQueue<Order> queue;

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public Cook(String name) {
        this.name = name;
    }

    public void startCookingOrder(Order order) {
        busy = true;
        ConsoleHelper.writeMessage("Start cooking - " + order);
        CookedOrderEventDataRow row = new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime() * 60, order.getDishes());
        StatisticManager.getInstance().register(row);
        try {
            Thread.sleep(order.getTotalCookingTime() * 10L);
        } catch (InterruptedException ignored) {
        }
        busy = false;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(10);
                if (!queue.isEmpty()) {
                        if (!isBusy()) {
                            startCookingOrder(queue.take());
                        }

                }
            }
        } catch (InterruptedException ignored) {
        }
    }
}
