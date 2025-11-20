package Opakovani_SQL_13_11;

public class HashFunkce {

    static final int MODULO = 9973;
    static final char[] pismena;

    static {
        StringBuilder sb = new StringBuilder();
        for (char c = 'a'; c <= 'z'; c++)
            sb.append(c);
        for (char c = 'A'; c <= 'Z'; c++)
            sb.append(c);
        pismena = sb.toString().toCharArray();
    }

    public static int spocitejHash(String retezec) {
        int vysledek = 1;
        for (char znak : retezec.toCharArray()) {
            vysledek = (vysledek * (int) znak) % MODULO;
        }
        return vysledek;
    }

    static int nalezenePocet = 0;

    public static void najdiKolidavatelne(String aktualni, int delka, int cilovyHash, int pocetPozadovanych) {
        if (nalezenePocet >= pocetPozadovanych)
            return;
        if (aktualni.length() == delka) {
            if (spocitejHash(aktualni) == cilovyHash) {
                System.out.println(aktualni);
                nalezenePocet++;
            }
            return;
        }
        for (char znak : pismena) {
            najdiKolidavatelne(aktualni + znak, delka, cilovyHash, pocetPozadovanych);
            if (nalezenePocet >= pocetPozadovanych)
                return;
        }
    }

    public static void main(String[] args) {
        String input = "ProgramovaT";
        int template = spocitejHash(input);
        int pozadovanyPocet = 5;

        for (int delka = 1; ; delka++) {
            nalezenePocet = 0;
            najdiKolidavatelne("", delka, template, pozadovanyPocet);
            if (nalezenePocet >= pozadovanyPocet) {
                break;
            }
        }
    }
}