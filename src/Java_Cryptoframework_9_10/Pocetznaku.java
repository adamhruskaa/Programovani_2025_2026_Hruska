package Java_Cryptoframework_9_10;


public class Pocetznaku {
    public static void main(String[] args) {
        int pocetZnaku;
        for(int i = 0; i < 26; i++) {
            pocetZnaku = i;
            System.out.println(pocetZnaku);
        }
        int pocet = 25;
        System.out.println("Factorial of number is: " + factorial(pocet));
        System.out.println("--------------------");
        System.out.println(12^12);
    }
    public static int factorial(int n) {
        int res = 1, i;
        for (i = 2; i <= n; i++)
            res *= i;
        return res;
    }
}