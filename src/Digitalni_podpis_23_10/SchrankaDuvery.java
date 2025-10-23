package Digitalni_podpis_23_10;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class SchrankaDuvery {
    public static void main(String[] args) {
        String pkString = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJ0aq4p+aR2+t/RLy1ZoE/pPswffo2JsbvtDFhE5RnH/TJXCZgSHMxhB39bIMOn2ElXeEHPcyV9m4LR/Mu35+8MCAwEAAQ==";

        try {
            byte[] pkEncoded = Base64.getDecoder().decode(pkString);

            KeyFactory kf = KeyFactory.getInstance("RSA");
            PublicKey publicKey = kf.generatePublic(new X509EncodedKeySpec(pkEncoded));

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

            cipher.init(Cipher.DECRYPT_MODE, publicKey);

            Scanner sc = new Scanner(System.in);
            System.out.println("Zadejte vstup (Base64-encoded šifrovaný/podepsaný text):");
            String vstup = sc.nextLine();
            sc.close();

            byte[] encrypted = Base64.getDecoder().decode(vstup);

            byte[] decryptedBytes = cipher.doFinal(encrypted);

            String decryptedMessage = new String(decryptedBytes);

            System.out.println("Výstup:");
            System.out.println(decryptedMessage);

        } catch (Exception e) {
            System.err.println("Chyba při provádění kryptografické operace: " + e.getMessage());
            e.printStackTrace();
        }
    }
}