package Test_13_11;

import javax.crypto.Cipher;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;


public class CteniZeZpravy {
        public static void main(String[] args) {
            String pkString = "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";
            String encryptedMessage = "PrSkSmOx/JzeOMCyG9D6n1CJPXROEBwJ/2mqtRyf4zLw8jnEDHs9uuqIp63NnCHfBwArS798NAvkZIVCVKrTaVdPnafs1k6wfQTnMPS7jgYIEV7ScnoE28IGiTKFq/TRC5PULiELCjVf9n31gHEq8oi+IqQrz4rPaLQOaPyAK4zGv7NofzYgHzj/APmV5vJ6yZbQjVuXrPJ/r/XpCMVccLVoRZzPT3+1c7ebCc5qdBMxiN7O0nA21Odiem5GYVDkdaq+6wMK/UiCxpH3KIk4rFESkIjx+6pE234QrORMo+UI+S6CeDRCA3uN+6q9GdB4FmfQ1en8TFDpon+LQGg+6jdN+kAYRBj/CL/3IEpi3hHqq56mwBswwFx9Wg9in/THSsYMwNMsK+D79LCkQ2zEKFz7bob9Y8VfYE6fx9BHzsZl7Z82d26hBw+zssbFEzqh0Rqvln1cTeNEOP26x0JDoRAMrnj4Td0jdCZTxPX7Fel9qvW+3Vjng7sPkIFbA2QI";

            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                StringBuilder keyBuilder = new StringBuilder(pkString);
                StringBuilder messageBuilder = new StringBuilder(encryptedMessage);

                String line;

                while ((line = br.readLine()) != null && !line.isEmpty()) {
                    keyBuilder.append(line);
                }

                while ((line = br.readLine()) != null && !line.isEmpty()) {
                    messageBuilder.append(line);
                }

                byte[] keyBytes = Base64.getDecoder().decode(keyBuilder.toString());
                byte[] encryptedBytes = Base64.getDecoder().decode(messageBuilder.toString());

                PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.DECRYPT_MODE, privateKey);

                byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
                String decryptedMessage = new String(decryptedBytes);
                System.out.println("Dešifrovaná zpráva: " + decryptedMessage);

            } catch (Exception e) {
                System.err.println("Chyba při dešifrování: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }