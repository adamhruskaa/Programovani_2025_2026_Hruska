package JDBC;

public class Clovek {
    private final String jmeno;
    private final String prijmeni;
    private final int rocnik;

    public Clovek(String jmeno, String prijmeni, int rocnik) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.rocnik = rocnik;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public int getRocnik() {
        return rocnik;
    }
}
