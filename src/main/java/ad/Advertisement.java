package main.java.ad;

public class Advertisement {

    private final String name;

    private int hits;

    private final int duration;

    private long amountPerOneDisplaying;

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration){
        this.name = name;
        this.hits = hits;
        this.duration = duration;

        if (hits > 0) {
            amountPerOneDisplaying = initialAmount / hits;
        }
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public void revalidate(){
        if (hits < 1) 
            throw new UnsupportedOperationException();
        hits--;
    }

    public boolean isActive() {
        return hits > 0;
    }

    public int getHits(){
        return hits;
    }
}
