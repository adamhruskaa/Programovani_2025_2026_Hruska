import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class NejkratsiCesta {

    HashMap<Long, Uzel> mapaUzlu = new HashMap<>();
    ArrayList<Uzel> seznamUzlu = new ArrayList<>();

    public void nactiData() {

        // nacteni uzlu
        try {
            Scanner sc1 = new Scanner(new File("praha_uzly.csv"));

            while (sc1.hasNextLine()) {
                String radek = sc1.nextLine();
                if (radek.isEmpty()) continue;

                // nacitani po radcich
                String[] casti = radek.trim().split("\\s+");
                if (casti.length < 3) continue;

                long id = Long.parseLong(casti[0]);
                double x = Double.parseDouble(casti[1]);
                double y = Double.parseDouble(casti[2]);

                Uzel novy = new Uzel(id, x, y);
                mapaUzlu.put(id, novy);
                seznamUzlu.add(novy);
            }

            sc1.close();

        } catch (Exception e) {
            System.err.println("Chyba: " + e.getMessage());
        }

        // nacteni hran
        try {
            Scanner sc = new Scanner(new File("praha_hrany.csv"));

            while (sc.hasNextLine()) {
                String radek = sc.nextLine();
                if (radek.isEmpty()) 
                    continue;

                // rozdeleni na radky
                String[] casti = radek.trim().split("\\s+");
                if (casti.length < 3) 
                    continue;


                long id1 = Long.parseLong(casti[0]);
                long id2 = Long.parseLong(casti[1]);
                double d = Double.parseDouble(casti[2]);
                String jmeno;

                if (casti.length > 3) {
                    jmeno = casti[3];
                } else {
                    jmeno = "";
                }

                Uzel u1 = mapaUzlu.get(id1);
                Uzel u2 = mapaUzlu.get(id2);

                if (u1 != null && u2 != null) {
                    u1.sousedi.add(new Hrana(u2, d, jmeno));
                    u2.sousedi.add(new Hrana(u1, d, jmeno));
                }
            }

            sc.close();

        } catch (Exception e) {
            System.err.println("Chyba: " + e.getMessage());
        }
    }

    public void vypocitej(long idStart, long idCil) {

        Uzel start = mapaUzlu.get(idStart);
        Uzel cil = mapaUzlu.get(idCil);

        if (start == null || cil == null) {
            System.out.println("Chyba u ID");
            return;
        }

        for (Uzel u : seznamUzlu) {
            u.vzdalenost = Double.MAX_VALUE;
            u.prosel = false;
            u.zpet = null;
            u.ulice = null;
        }

        start.vzdalenost = 0;

        while (true) {

            Uzel akt = null;
            double nejmensi = Double.MAX_VALUE;

            for (Uzel u : seznamUzlu) {
                if (!u.prosel && u.vzdalenost < nejmensi) {
                    nejmensi = u.vzdalenost;
                    akt = u;
                }
            }

            if (akt == null || akt == cil) 
                break;

            akt.prosel = true;

            for (Hrana h : akt.sousedi) {
                double nova = akt.vzdalenost + h.metry;

                if (nova < h.kam.vzdalenost) {
                    h.kam.vzdalenost = nova;
                    h.kam.zpet = akt;
                    h.kam.ulice = h.nazev;
                }
            }
        }

        if (cil.vzdalenost < Double.MAX_VALUE) {
            System.out.println(cil.vzdalenost);

            ArrayList<String> cesta = new ArrayList<>();
            Uzel p = cil;

            while (p.zpet != null) {
                cesta.add(0, p.ulice);
                p = p.zpet;
            }

            for (String ulice : cesta) {
                System.out.println(ulice);
            }
        }
    }

    public static void main(String[] args) {
        NejkratsiCesta nc = new NejkratsiCesta();
        nc.nactiData();

        Scanner sc2 = new Scanner(System.in);

        if (sc2.hasNextLong()) {
            long id1 = sc2.nextLong();
            long id2 = sc2.nextLong();
            nc.vypocitej(id1, id2);
        }

        sc2.close();
    }
}

class Uzel {
    long id;
    double x, y;

    double vzdalenost;
    boolean prosel;
    Uzel zpet;
    String ulice;

    ArrayList<Hrana> sousedi = new ArrayList<>();

    Uzel(long id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
}

class Hrana {
    Uzel kam;
    double metry;
    String nazev;

    Hrana(Uzel kam, double metry, String nazev) {
        this.kam = kam;
        this.metry = metry;
        this.nazev = nazev;
    }
}