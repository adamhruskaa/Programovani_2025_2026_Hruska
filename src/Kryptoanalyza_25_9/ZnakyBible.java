package Kryptoanalyza_25_9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ZnakyBible {
    public static void main(String[] args) {
            File file = new File("Bible21.txt");
            Map<Character, Integer> frekvence = new HashMap<>();
            int pocetZnaku = 0;

            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    for (char c : line.toCharArray()) {
                        pocetZnaku++;
                        frekvence.put(c, frekvence.getOrDefault(c, 0) + 1);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            System.out.println("Celkový počet znaků: " + pocetZnaku);

        char maxZnak = 0, minZnak = 0;
        int maxPocet = Integer.MIN_VALUE;
        int minPocet = Integer.MAX_VALUE;

        for (Map.Entry<Character, Integer> entry : frekvence.entrySet()) {
            char znak = entry.getKey();
            int pocet = entry.getValue();

            if (pocet > maxPocet) {
                maxPocet = pocet;
                maxZnak = znak;
            }
            if (pocet < minPocet) {
                minPocet = pocet;
                minZnak = znak;
            }
        }

        System.out.println("Nejčastější znak: '" + maxZnak + "' (" + maxPocet + "x)");
        System.out.println("Nejméně častý znak: '" + minZnak + "' (" + minPocet + "x)");
    }
}