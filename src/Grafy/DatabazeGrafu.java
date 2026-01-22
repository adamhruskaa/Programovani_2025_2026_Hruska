import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabazeGrafu {
    
    public static void main(String[] args) {
        DatabazeGrafu databaze = new DatabazeGrafu();
        databaze.vyrobDomecek();
        databaze.pocetHranUVrcholu();
    }

    private Map<String, List<String>> graf;

    public DatabazeGrafu() {
        graf = new HashMap<>();
    }

    public void pridatHranici(String uzel1, String uzel2) {
        graf.putIfAbsent(uzel1, new ArrayList<>());
        graf.putIfAbsent(uzel2, new ArrayList<>());
        graf.get(uzel1).add(uzel2);
        graf.get(uzel2).add(uzel1);
    }

    public List<String> getHranice(String uzel) {
        return graf.getOrDefault(uzel, new ArrayList<>());
    }

    public void vyrobDomecek() {
    String strecha = "A";
    String horniLeva = "B";
    String horniPrava = "C";
    String dolniLeva = "D";
    String dolniPrava = "E";

    pridatHranici(horniLeva, horniPrava); 
    pridatHranici(horniPrava, dolniPrava);
    pridatHranici(dolniPrava, dolniLeva); 
    pridatHranici(dolniLeva, horniLeva); 

    pridatHranici(strecha, horniLeva);   
    pridatHranici(strecha, horniPrava);   

    System.out.println("Geometricky správný domeček byl vyroben.");
    System.out.println("Struktura sousedství: " + graf);
}

    public void pocetHranUVrcholu() {
        for (String uzel : graf.keySet()) {
            System.out.println("Uzel " + uzel + " má " + graf.get(uzel).size() + " hran.");
        }
    }
}