import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MujGraf {
    static int i = 0;
    Set<Uzel> uzly = new HashSet<>();
    Set<Hrana> hrany = new HashSet<>();

    public static int uzelNextId() {
        i = i + 1;
        return i;
    }

    public void vyrobDomecek() {
        var u1 = new Uzel();
        var u2 = new Uzel();
        var u3 = new Uzel();
        var u4 = new Uzel();
        var u5 = new Uzel();

        var h1 = new Hrana(u1, u2);
        var h2 = new Hrana(u1, u3);
        var h3 = new Hrana(u2, u3);
        var h4 = new Hrana(u3, u4);
        var h5 = new Hrana(u4, u5);

        uzly.add(u1);
        uzly.add(u2);
        uzly.add(u3);
        uzly.add(u4);
        uzly.add(u5);
        hrany.add(h1);
        hrany.add(h2);
        hrany.add(h3);
        hrany.add(h4);
        hrany.add(h5);
    }

    void projdiGraf(Uzel zacatek) {
        zacatek.navstiven = true;
        for (Uzel dalsiUzel : kamMuzuJit(zacatek)) {
            if (!dalsiUzel.navstiven) {
                projdiGraf(dalsiUzel);
            }
        }
    }

    private List<Uzel> kamMuzuJit(Uzel odkud) {
        List<Uzel> sousedniUzly = new ArrayList<>();
        for (Hrana hrana : hrany) {
            if (hrana.getZacatek() == odkud && !hrana.getKonec().navstiven) {
                sousedniUzly.add(hrana.getKonec());
            }
        }
        return sousedniUzly;
    }

    public static void main(String[] args) {
        Uzel u1 = new Uzel();
        Uzel u2 = new Uzel();
        Uzel u3 = new Uzel();

        System.out.println("ID uzlu 1: " + u1.getId());
        System.out.println("ID uzlu 2: " + u2.getId());
        System.out.println("ID uzlu 3: " + u3.getId());

        MujGraf graf = new MujGraf();
        graf.vyrobDomecek();

        graf.projdiGraf(u1);
        System.out.println("Uzel 1 navstiven: " + u1.navstiven);
        System.out.println("Uzel 2 navstiven: " + u2.navstiven);
        System.out.println("Uzel 3 navstiven: " + u3.navstiven);
    }
}
