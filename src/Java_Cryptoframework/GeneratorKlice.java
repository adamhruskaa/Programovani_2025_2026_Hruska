package Java_Cryptoframework;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class GeneratorKlice {
    public static void main(String[] args) {
        try {
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(128);

            SecretKey key = generator.generateKey();

            File file = new File("generator.txt");
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(key.getEncoded());
                System.out.println("Klíč byl úspěšně vygenerován a uložen do " + file.getAbsolutePath());
            }

        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }
}