public class Hrana {
    private final Uzel vrcholA;
    private final Uzel vrcholB;

    public Hrana(Uzel a, Uzel b) {
        this.vrcholA = a;
        this.vrcholB = b;
    }

    public Uzel getVrcholA() {
        return vrcholA;
    }

    public Uzel getVrcholB() {
        return vrcholB;
    }

    public Uzel getZacatek() {
        return vrcholA;
    }

    public Uzel getKonec() {
        return vrcholB;
    }
}
