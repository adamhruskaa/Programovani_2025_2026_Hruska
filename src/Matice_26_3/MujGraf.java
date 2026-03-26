package Matice_26_3;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MujGraf{
    private final List<Vrchol> vrcholy = Arrays.asList(
        new Vrchol("A", 1),
        new Vrchol("B", 2),
        new Vrchol("C", 3),
        new Vrchol("D", 4));

    private final List<Hrana> hrany = Arrays.asList(
        new Hrana(vrcholy.get(0), vrcholy.get(1)),
        new Hrana(vrcholy.get(1), vrcholy.get(2)));

    public List<Vrchol> sousedi(Vrchol v) {
        return hrany.stream().map(x -> x.soused(v)).filter(Objects::nonNull).toList();
    }

    public static void main(String[] args) {
        MujGraf g = new MujGraf();

        int size = g.getVrcholy().size();
        int[][] matice = vytvorPole(size);
    }

    private List<Vrchol> getVrcholy() {
        return vrcholy;
    }

    private static int[][] vytvorPole(int size) {
        int[][] pole = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                pole[i][j] = 0;
            }
        }
        return pole;
    }
}