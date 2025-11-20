package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MojeDatabaze {
    public static void main(String[] args) {
        List<Clovek> lidi = new ArrayList<>();
            lidi.add(new Clovek("Jan", "Lana", 3));
            lidi.add(new Clovek("Adam", "Hruska", 4));
            lidi.add(new Clovek("Pepa", "Zdepa", 5));

        System.out.println(lidi);

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/adam",null,null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Statement statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO clovek(jmeno, prijmeni, rocnik) VALUES ('"
                + lidi.get(0).getJmeno() + "', '"
                + lidi.get(0).getPrijmeni() + "',"
                + lidi.get(0).getRocnik() + ")";

        System.out.println(sql);

    }
}