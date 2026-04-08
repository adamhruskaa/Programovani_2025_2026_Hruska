package Matice_26_3;

public class Vrchol {
    private final String name;
    private final int hodnota;

    public Vrchol(String name, int hodnota) {
        this.name = name;
        this.hodnota = hodnota;
    }

    public String getName() {
        return name;
    }
    
    public int getHodnota() {
        return hodnota;
    }
}