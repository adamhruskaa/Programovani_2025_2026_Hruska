package Asymetricke_Sifry;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Klice {
    public static void main(String[] args) {
        var publickey = new SecretKeySpec(Base64.getDecoder().decode(args[1]), "AES");
        var privatekey = new SecretKeySpec(Base64.getDecoder().decode(args[2]), "AES");

        try {
            Cipher cipher = Cipher.getInstance("AES");
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        String str = Base64.getEncoder().encodeToString(publickey.getEncoded());
        str = Base64.getEncoder().encodeToString(privatekey.getEncoded());

        System.out.println("Verejny klic: " + publickey.getEncoded().length);
        System.out.println("Privatni klic: " + privatekey.getEncoded().length);
    }
}