package Uvod_4_9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class NactiZeVstupu {
    public static void main(String[] args) {
        File file = new File("vstup.txt");
        ArrayList<Integer> numbers = new ArrayList<>();

        try (Scanner input = new Scanner(file)) {
            while (input.hasNext()) {
                String token = input.next();
                try {
                    numbers.add(Integer.parseInt(token.trim()));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format in file: " + token);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file vstup.txt was not found. Please make sure it exists in the project directory.");
            return;
        }

        if (numbers.isEmpty()) {
            System.out.println("There are no valid numbers in the file.");
            return;
        }

        ArrayList<Integer> copy = new ArrayList<>(numbers);
        copy.sort(null);

        double median;
        int size = copy.size();
        if (size % 2 == 0) {
            median = (copy.get(size / 2 - 1) + copy.get(size / 2)) / 2.0;
        } else {
            median = copy.get(size / 2);
        }

        System.out.println("Median: " + median);
        System.out.println("Size of numbers: " + numbers.size());

        SelectionSort(numbers);
        System.out.println("Numbers sorted with SelectionSort: " + numbers);

        InsertionSort(numbers);
        System.out.println("Number sorted with InsertionSort: " + numbers);
    }

    public static void SelectionSort(ArrayList<Integer> selectednumbers) {
        for (int i = 0; i < selectednumbers.size() - 1; i++) {
            int min = i;
            for (int j = i + 1; j < selectednumbers.size(); j++) {
                if (selectednumbers.get(j) < selectednumbers.get(min)) {
                    min = j;
                }
            }

            int temp = selectednumbers.get(i);
            selectednumbers.set(i, selectednumbers.get(min));
            selectednumbers.set(min, temp);
        }
    }

    public static void InsertionSort(ArrayList<Integer> selectednumbers) {
        for (int i = 1; i < selectednumbers.size(); i++) {
            int first =  selectednumbers.get(i);
            int second = selectednumbers.get(i - 1);
            if (first > second) {
                int temp = selectednumbers.get(i);
                selectednumbers.set(i, selectednumbers.get(second));
                selectednumbers.set(second, temp);
            }
        }
        for  (int i = 0; i < selectednumbers.size() - 1; i++) {
            int temp = selectednumbers.get(i);
            selectednumbers.set(i, selectednumbers.get(i + 1));
            selectednumbers.set(i + 1, temp);
        }
    }
}