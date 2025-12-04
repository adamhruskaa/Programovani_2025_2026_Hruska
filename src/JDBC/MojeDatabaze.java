package JDBC;

import java.sql.*;
import java.util.ArrayList;

    public class MojeDatabaze {
    public static void main(String[] args) {
        ArrayList<Clovek> lidi = new ArrayList<>();
        lidi.add(new Clovek("Jana", "Nova", 3));
        lidi.add(new Clovek("Maria", "Lanova", 4));
        lidi.add(new Clovek("Pepa", "Zdepa", 5));

        System.out.println(lidi);

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db")) {

            try (Statement stmt = conn.createStatement()) {
                stmt.execute(" CREATE TABLE IF NOT EXISTS clovek ( jmeno VARCHAR, primeni VARCHAR, rocnik INTEGER)");
            }

            String sql = "INSERT INTO clovek (jmeno, primeni, rocnik) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (Clovek c : lidi) {
                    ps.setString(1, c.getJmeno());
                    ps.setString(2, c.getPrijmeni());
                    ps.setInt(3, c.getRocnik());
                    ps.executeUpdate();
                }
            }

            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM clovek");
                while (rs.next()) {
                    System.out.println(
                            rs.getString("jmeno") + " "
                                    + rs.getString("primeni") + " "
                                    + rs.getInt("rocnik"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}