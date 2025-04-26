package telran.main;

import telran.Tarakan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class TarakanRace {
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter the number of tarakans: ");
        int numTarakans = Integer.parseInt(reader.readLine());
        System.out.print("Enter distance: ");
        int distance = Integer.parseInt(reader.readLine());

        Tarakan[] tarakans = new Tarakan[numTarakans];
        Thread[] threads = new Thread[numTarakans];

        for (int i = 0; i < numTarakans; i++) {
            tarakans[i] = new Tarakan("Tarakan #" + (i + 1), distance);
            threads[i] = new Thread(tarakans[i]);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        Tarakan winner = Arrays.stream(tarakans)
                .min(Comparator.comparingLong(Tarakan::getFinishTime))
                .orElse(null);

        if (winner != null) {
            System.out.println("And the winner is..." + winner.getName());
        }
    }
}
