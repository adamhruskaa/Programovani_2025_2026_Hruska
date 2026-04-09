package Olympiada_8_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Palindrom {
    public static void main(String[] args) {
        File input = new File("uloha1_vstup1.txt");
        try (Scanner myReader = new Scanner(input)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (isPalindrome(data)) {
                    System.out.println("ano");
                } else {
                    System.out.println("ne");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
}

        public static boolean isPalindrome(String str) {
            String cleanStr = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
            String reversedStr = new StringBuilder(cleanStr).reverse().toString();
            return cleanStr.equals(reversedStr);
        }

    public void pozicePalindromuVTextu() {
    File myObj = new File("uloha1_vstup1.txt");
    try (Scanner myReader = new Scanner(myObj)) {
        int lineNumber = 0;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            lineNumber++;
            if (isPalindrome(data)) {
                System.out.println("Line " + lineNumber);
            } else {
                System.out.println("Line " + lineNumber);
            }
        }
    } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
    }

    public void pocetPalindromuVTextu() {
    File myObj = new File("uloha1_vstup1.txt");
    int palindromeCount = 0;
    try (Scanner myReader = new Scanner(myObj)) {
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            if (isPalindrome(data)) {
                palindromeCount++;
            }
        }
        System.out.println("Total palindromes: " + palindromeCount);
    } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
    }
}