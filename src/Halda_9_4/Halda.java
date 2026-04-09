package Halda_9_4;

import java.util.Scanner;

public class Halda {
    int[] halda;
    int zaplnenost;

    public Halda(int velikost) {
        halda = new int[velikost];
        zaplnenost = 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Halda halda = new Halda(sc.nextInt());
        while(sc.hasNextInt()) {
            int input = sc.nextInt();
            if (input == 0 || input < 0) {
                break;
            }

            halda.pridej(input);
        }
        for(int i = 0; i < halda.zaplnenost; i++) {
            System.out.println(halda.halda[i]);
        }
        pocetPaterHaldy(halda);
        halda.pridej(15);
        for(int i = 0; i < halda.zaplnenost; i++) {
            System.out.println(halda.halda[i]);
        }
    }

    public static void pocetPaterHaldy(Halda halda) {
    int n = halda.zaplnenost;
    if (n == 0) {
        System.out.println("Pocet pater haldy: 0");
        return;
    }

    int pocetPater = (int)(Math.log(n) / Math.log(2)) + 1;

    System.out.println("Pocet uzlu: " + n);
    System.out.println("Pocet pater haldy: " + pocetPater);
    }

    public void pridej(int hodnota) {
        halda[zaplnenost] = hodnota;
        zaplnenost++;
        upravHaldu();
    }

    private void upravHaldu() {
        int index = zaplnenost - 1;
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (halda[index] > halda[parent]) {
                int temp = halda[index];
                halda[index] = halda[parent];
                halda[parent] = temp;
                index = parent;
            } else {
                break;
            }
        }
    }
}