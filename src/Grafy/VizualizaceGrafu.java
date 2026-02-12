import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class VizualizaceGrafu extends JPanel {
    private Map<String, List<String>> graf;
    private Map<String, Point> souradnice;

    public VizualizaceGrafu() {
        graf = new HashMap<>();
        souradnice = new HashMap<>();
        vyrobDomecek();
        definujPozice();
    }

    private void pridatHranici(String uzel1, String uzel2) {
        graf.putIfAbsent(uzel1, new ArrayList<>());
        graf.putIfAbsent(uzel2, new ArrayList<>());
        graf.get(uzel1).add(uzel2);
        graf.get(uzel2).add(uzel1);
    }

    private void vyrobDomecek() {
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
    }

    private void definujPozice() {
        souradnice.put("A", new Point(200, 50)); 
        souradnice.put("B", new Point(100, 150));
        souradnice.put("C", new Point(300, 150));
        souradnice.put("D", new Point(100, 300)); 
        souradnice.put("E", new Point(300, 300)); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        for (String uzel : graf.keySet()) {
            Point p1 = souradnice.get(uzel);
            for (String soused : graf.get(uzel)) {
                Point p2 = souradnice.get(soused);
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }

        for (Map.Entry<String, Point> entry : souradnice.entrySet()) {
            Point p = entry.getValue();
            String nazev = entry.getKey();

            g2d.setColor(Color.WHITE);
            g2d.fillOval(p.x - 15, p.y - 15, 30, 30);
            g2d.setColor(Color.BLUE);
            g2d.drawOval(p.x - 15, p.y - 15, 30, 30);
            
            g2d.setColor(Color.BLACK);
            g2d.drawString(nazev, p.x - 5, p.y + 5);
        }
    }

    public static void main(String[] args) {
        JFrame okno = new JFrame("Vizualizace Grafu - Domeček");
        VizualizaceGrafu panel = new VizualizaceGrafu();
        
        okno.add(panel);
        okno.setSize(450, 450);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setLocationRelativeTo(null);
        okno.setVisible(true);
    }
}