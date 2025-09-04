package Uvod_4_9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class NactiZeVstupu {
    public static void main(String[] args) {
        File file = new File("Uvod_4_9/vstup.txt");
        ArrayList<Integer> numbers = new ArrayList<>();

        try (Scanner input = new Scanner(file)) {
            input.useDelimiter(",");
            while (input.hasNextInt()) {
                numbers.add(input.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file vstup.txt was not found. Please make sure it exists in the project directory.");
            return;
        }

        if (numbers.isEmpty()) {
            System.out.println("There are no numbers in the file or the file doesn't exist.");
            return;
        }

        Collections.sort(numbers);

        double median;
        int size = numbers.size();

        if (size % 2 == 0) {
            median = (numbers.get(size / 2 - 1) + numbers.get(size / 2)) / 2.0;
        } else {
            median = numbers.get(size / 2);
        }

        System.out.println("Median: " + median);
    }
}