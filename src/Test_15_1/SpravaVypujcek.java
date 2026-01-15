package Test_15_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SpravaVypujcek {

    static void createTable(Connection conn, String sql) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + sql);
        }
    }

    public static void main(String[] args) {
        String url = "jdbc:postgrsql:test.db";

        try (var conn = DriverManager.getConnection("jdbc:postgresql:")) {
            createTable(conn, "kniha (id INT NOT NULL UNIQUE, jmeno VARCHAR NOT NULL, autor VARCHAR NOT NULL)");
            createTable(conn, "uzivatel (id INT NOT NULL UNIQUE, prijmeni VARCHAR NOT NULL)");
            createTable(conn, "vypujcka (id_uzivatel INT NOT NULL, id_kniha INT NOT NULL, vypujceno TIMESTAMP NOT NULL, vraceno TIMESTAMP)");


            Scanner sc = new Scanner(System.in);
            if (!sc.hasNextLine()) {
                return;
            }
            String input = sc.nextLine();

            if (!input.contains(" ")) {
                System.out.println("nevalidni vstup");
                return;
            }

            int firstSpace = input.indexOf(" ");
            String prijmeni = input.substring(0, firstSpace);
            String kniha = input.substring(firstSpace + 1);

            Integer userID = najdiUzivatele(conn, prijmeni);
            Integer knihaID = najdiIDKnihy(conn, kniha);

            if (userID != null && knihaID != null) {
                String sqlInsert = "INSERT INTO vypujcka (id_uzivatel, id_kniha) VALUES (?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
                    pstmt.setInt(1, userID);
                    pstmt.setInt(2, knihaID);
                    pstmt.executeUpdate();
                }
            } else {
                if (knihaID == null) {
                    System.out.println("neznama kniha");
                }
                if (userID == null) {
                    System.out.println("neznamy uzivatel");
                }
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static Integer najdiIDKnihy(Connection conn, String jmenoKnihy) throws SQLException {
        String sql = "SELECT id FROM kniha WHERE jmeno = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, jmenoKnihy);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        return null;
    }

    public static Integer najdiUzivatele(Connection conn, String prijmeni) throws SQLException {
        String sql = "SELECT id FROM uzivatel WHERE prijmeni = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, prijmeni);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        return null;
    }
}