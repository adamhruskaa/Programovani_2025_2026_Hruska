package Test_15_1;

import java.sql.*;

public class Vypujcky {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql:")) {
            createTable(conn, "kniha (id INT NOT NULL UNIQUE, jmeno VARCHAR NOT NULL, autor VARCHAR NOT NULL)");
            createTable(conn, "uzivatel (id INT NOT NULL UNIQUE, prijmeni VARCHAR NOT NULL)");
            createTable(conn, "vypujcka (id_uzivatel INT NOT NULL, id_kniha INT NOT NULL, vypujceno TIMESTAMP NOT NULL, vraceno TIMESTAMP)");

            vypisVypujcky(conn);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void createTable(Connection conn, String sql) throws SQLException {
        try (var stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + sql);
        }
    }

    public static void vypisVypujcky(Connection conn) throws SQLException {
        String sql = "SELECT uzivatel.prijmeni, kniha.jmeno FROM vypujcka " +
                "JOIN uzivatel ON vypujcka.id_uzivatel = uzivatel.id " +
                "JOIN kniha ON vypujcka.id_kniha = kniha.id";

        try (var stmt = conn.createStatement();
             var rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getString("prijmeni") + ": " + rs.getString("jmeno"));
            }
        }
    }
}