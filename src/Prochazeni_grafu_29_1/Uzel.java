import java.util.ArrayList;
import java.util.List;

public class Uzel {
    private int id;
    private static int nextId = 1;
    private static int pocitadlo;
    boolean navstiven = false;

    public Uzel() {
        id = getNextId();
        pocitadlo = id;
    }

    public int getId() {
        return id;
    }

    public static int getNextId() {
        return nextId++;
    }
}