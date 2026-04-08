package Matice_26_3;

public class Hrana {
    private final Vrchol v1;
    private final Vrchol v2;

    public Hrana(Vrchol v1, Vrchol v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Vrchol soused(Vrchol v) {
        return v1.equals(v) ? v2 : (v2.equals(v) ? v1 : null);
    }

    public Vrchol getV1() {
        return v1;
    }

    public Vrchol getV2() {
        return v2;
    }
}