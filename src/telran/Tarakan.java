package telran;

import java.util.Random;

public class Tarakan implements Runnable {
    private String name;
    private int distance;
    private int position = 0;
    private long finishTime;
    private static final Random random = new Random();

    public Tarakan(String name, int distance) {
        this.name = name;
        this.distance = distance;
        this.finishTime = Long.MAX_VALUE;
    }

    public String getName() {
        return name;
    }

    public long getFinishTime() {
        return finishTime;
    }

    @Override
    public void run() {
        try {
            while (position < distance) {
                position++;
                printTrack();
                System.out.println(name + ": " + position);
                Thread.sleep(200 + random.nextInt(301));
            }
            finishTime = System.currentTimeMillis();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void printTrack() {
        StringBuilder track = new StringBuilder();
        for (int i = 0; i < position; i++) {
            track.append("-");
        }
        track.append(">");
        System.out.println(name + ": " + track);
    }
}
