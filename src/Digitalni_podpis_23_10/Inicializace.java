package Digitalni_podpis_23_10;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class Inicializace {

    static class Uzel {
        long id;
        double x, y;
        List<Hrana> sousedi = new ArrayList<>();

        public Uzel(long id, double x, double y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }

    static class Hrana {
        Uzel cil;
        double delka;
        String jmeno;

        public Hrana(Uzel cil, double delka, String jmeno) {
            this.cil = cil;
            this.delka = delka;
            this.jmeno = jmeno;
        }
    }

    private Map<Long, Uzel> uzly = new HashMap<>();

    public void nactiData() {
        try (Scanner sc = new Scanner(Paths.get("praha_uzly.csv"))) {
            while (sc.hasNextLine()) {
                String[] radek = sc.nextLine().split(",");
                long id = Long.parseLong(radek[0]);
                double x = Double.parseDouble(radek[1]);
                double y = Double.parseDouble(radek[2]);
                
                uzly.put(id, new Uzel(id, x, y));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Scanner sc = new Scanner(Paths.get("praha_hrany.csv"))) {
            while (sc.hasNextLine()) {
                String[] radek = sc.nextLine().split(",");
                long odId = Long.parseLong(radek[0]);
                long doId = Long.parseLong(radek[1]);
                double delka = Double.parseDouble(radek[2]);
                String jmeno = radek.length > 3 ? radek[3] : "";

                Uzel uzelOd = uzly.get(odId);
                Uzel uzelDo = uzly.get(doId);

                if (uzelOd != null && uzelDo != null) {
                    uzelOd.sousedi.add(new Hrana(uzelDo, delka, jmeno));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Inicializace init = new Inicializace();
        init.nactiData();
        System.out.println("Načteno uzlů: " + init.uzly.size());
    }
}