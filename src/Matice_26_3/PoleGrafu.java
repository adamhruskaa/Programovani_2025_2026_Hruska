package Matice_26_3;

public class PoleGrafu {
    public static void main(String[] args) {
        int[][] arr = {
            {7, 6, 3, 0},
            {13, 10, 5, 0},
            {1, 1, 1, 0}
        };

        System.out.println("Původní pole:");
        vypisPole(arr);
        System.out.println("aaaaa");

        int maxSoucet = 0;
        for (int i = 0; i < arr.length; i++) {
            int radekSum = soucetPrvkuNaRadku(arr, i);
            if (radekSum > maxSoucet) {
                maxSoucet = radekSum;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            int aktualniSoucet = soucetPrvkuNaRadku(arr, i);
            int chybi = maxSoucet - aktualniSoucet;
            arr[i][arr[i].length - 1] = chybi;
        }

        System.out.println("\nUpravené pole:");
        vypisPole(arr);
        
        System.out.println("\nNový součet každého řádku: " + maxSoucet);
    }

    public static void vypisPole(int[][] pole) {
        for (int i = 0; i < pole.length; i++) {
            for (int j = 0; j < pole[i].length; j++) {
                System.out.print(pole[i][j] + "\t");
            }
            System.out.println("| Součet: " + soucetPrvkuNaRadku(pole, i));
        }
    }

    public static int soucetPrvkuNaRadku(int[][] pole, int radek) {
        int soucet = 0;
        for (int j = 0; j < pole[radek].length; j++) {
            soucet += pole[radek][j];
        }
        return soucet;
    }
}