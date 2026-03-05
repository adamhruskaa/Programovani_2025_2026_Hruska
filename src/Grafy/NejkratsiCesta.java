import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Digitalni_podpis_23_10.Inicializace;

public class NejkratsiCesta extends Inicializace {
    private Map<Long, Uzel> graf = new HashMap<>();

    public void nactiData() {
    try (Scanner sc = new Scanner(Paths.get("praha_uzly.csv"))) {
        while (sc.hasNextLine()) {
            String radek = sc.nextLine();
            if (radek.isEmpty()) continue; 
            
            String[] casti = radek.split(",");
            try {
                long id = Long.parseLong(casti[0].trim());
                double x = Double.parseDouble(casti[1].trim());
                double y = Double.parseDouble(casti[2].trim());
                graf.put(id, new Uzel(id, x, y));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                continue; 
            }
        }
    } catch (IOException e) {
        System.err.println("Chyba při čtení uzlů: " + e.getMessage());
    }

    try (Scanner sc = new Scanner(Paths.get("praha_hrany.csv"))) {
        while (sc.hasNextLine()) {
            String radek = sc.nextLine();
            if (radek.isEmpty()) continue;

            String[] casti = radek.split(",");
            if (casti.length < 3) continue;

            long idOdkud = Long.parseLong(casti[0].trim());
            long idKam = Long.parseLong(casti[1].trim());
            double delka = Double.parseDouble(casti[2].trim());
            String jmeno = casti.length > 3 ? casti[3].trim() : "bezejmenná";

            Uzel uzelOdkud = graf.get(idOdkud);
            Uzel uzelKam = graf.get(idKam);

            if (uzelOdkud != null && uzelKam != null) {
                uzelOdkud.sousedi.add(new Hrana(uzelKam, delka, jmeno));
            }
        }
    } catch (IOException e) {
        System.err.println("Chyba při čtení hran: " + e.getMessage());
    }
}

    void projdiGraf(Uzel uzel) {
        if (uzel == null || uzel.navstiven) return;
        
        uzel.navstiven = true;
        System.out.println("Navštíven uzel: " + uzel.id);
        
        for (Hrana hrana : uzel.sousedi) {
            projdiGraf(hrana.cil);
        }
    }

    public static void main(String[] args) {
        NejkratsiCesta nc = new NejkratsiCesta();
        nc.nactiData();
        
        if (!nc.graf.isEmpty()) {
            Uzel start = nc.graf.values().iterator().next();
            nc.projdiGraf(start);
        }
    }
}

class Uzel {
    long id;
    double x;
    double y;
    boolean navstiven = false;
    java.util.List<Hrana> sousedi = new java.util.ArrayList<>();

    Uzel(long id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
}

class Hrana {
    Uzel cil;
    double delka;
    String jmeno;

    Hrana(Uzel cil, double delka, String jmeno) {
        this.cil = cil;
        this.delka = delka;
        this.jmeno = jmeno;
    }
}