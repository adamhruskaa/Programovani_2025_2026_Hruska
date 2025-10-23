package Digitalni_podpis_23_10;

import java.security.*;
import java.util.Scanner;

public class Podepis {
    public static void main(String[] args) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);

            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            signature.update(input.getBytes());
            byte[] sign = signature.sign();

            System.out.println("Public Key:" + publicKey.getEncoded());
            System.out.println("Private Key:" + privateKey.getEncoded());

            System.out.println("----------------------");
            System.out.println(sign);

            sc.close();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        }
    }
}
