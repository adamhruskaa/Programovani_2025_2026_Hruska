package Kryptografie_18_9;

import java.util.Scanner;

public class CaesarovaSifra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String vysledek1 = Zasifruj("ahoj", 5);
        String vysledek2 = Desifruj("surjudprydql", 3);
        System.out.println("Zašifrováno: " + vysledek1);
        System.out.println("Dešifrováno: " + vysledek2);
        sc.close();
    }

    public static String Zasifruj(String zprava, int cislo){
        StringBuilder vysledek_sifrovani = new StringBuilder();
        for (char character : zprava.toCharArray()) {
            if (Character.isLetter(character)) {
                int originalAlphabetPosition = character - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + cislo) % 26;
                char newCharacter = (char) ('a' + newAlphabetPosition);
                vysledek_sifrovani.append(newCharacter);
            } else {
                vysledek_sifrovani.append(character);
            }
        }
        return vysledek_sifrovani.toString();
    }

    public static String Desifruj(String zprava, int cislo) {
        StringBuilder vysledek_desifrovani = new StringBuilder();
        for (char character : zprava.toCharArray()) {
            if (Character.isLetter(character)) {
                int originalAlphabetPosition = character - 'a';
                int newAlphabetPosition = (originalAlphabetPosition - cislo + 26) % 26;
                char newCharacter = (char) ('a' + newAlphabetPosition);
                vysledek_desifrovani.append(newCharacter);
            } else {
                vysledek_desifrovani.append(character);
            }
        }
        return vysledek_desifrovani.toString();
    }
}
