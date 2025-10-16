package Kryptograficke_hashovaci_funkce_16_10;

import java.util.Scanner;

public class HashovaciFunkce {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String vstup = input.nextLine();


        System.out.println("Vysledek: " + getHash(vstup));
    }

    public static long getHash(String vstup){
        long sum = 0;

        for(char ch : vstup.toCharArray()){
            sum += (long) ch;
            sum = (sum*sum+3) % 1013;
        }

        long vysledek = (sum*sum + 3) % 1013;

        return vysledek;
    }

    public static String vygenerujSlovo(String slovo) {
        int vysledek = 982;
        String abeceda = "A, Á, B, C, Č, D, Ď, E, É, Ě, F, G, H, Ch, I, Í, J, K, L, M, N, Ň, O, Ó, P, Q, R, Ř, S, Š, T, Ť, U, Ú, Ů, V, W, X, Y, Ý, Z, Ž.";

        return slovo;
    }
}