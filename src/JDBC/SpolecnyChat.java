package JDBC;
import java.sql.*;
import java.util.Scanner;

public class SpolecnyChat {
    public static void main(String[] args) {
        String url = "jdbc:postgresql:db3963";
        String user = "db3963";
        String pass = "programovani";

        Scanner sc = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement()) {

            System.out.print("Zadej své jméno: ");
            String autor = sc.nextLine();

            System.out.print("Napiš zprávu: ");
            String textZpravy = sc.nextLine();
            long poradi = System.currentTimeMillis() / 1000;

            String sqlInsert = "INSERT INTO zprava (poradi, autor, msg) VALUES (" + poradi + ", '" + autor + "', '" + textZpravy + "')";
            stmt.executeUpdate(sqlInsert);
            System.out.println("\n> Zpráva odeslána!\n");

            try (ResultSet rs = stmt.executeQuery("SELECT autor, msg FROM zprava")) {
                System.out.println("--- AKTUÁLNÍ CHAT ---");
                while (rs.next()) {
                    System.out.println(rs.getString("autor") + ": " + rs.getString("msg"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Chyba při komunikaci s databází: " + e.getMessage());
        }
    }
}