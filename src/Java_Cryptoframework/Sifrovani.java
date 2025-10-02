package Java_Cryptoframework;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public class Sifrovani {
    public static void main(String[] args) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {

        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(128);
        SecretKey key = keyGen.generateKey();

        Scanner sc = new Scanner(System.in);
        System.out.print("Zadejte zprávu k zašifrování: ");
        String message = sc.nextLine();
        sc.close();

        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] encryptedBytes = cipher.doFinal(message.getBytes());

            String encryptedMessage = Base64.getEncoder().encodeToString(encryptedBytes);

            System.out.println("Zašifrovaná zpráva: " + encryptedMessage);

        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
    }
}
