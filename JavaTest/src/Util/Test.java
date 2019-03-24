package Util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class Test {
    public static void main(String[] args) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("rsa");
            keyPairGenerator.initialize(512, new SecureRandom());

            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();

            String content = "Hello World";

            RSAUtil rsaUtil = new RSAUtil(rsaPublicKey, rsaPrivateKey);
            byte[] bytes_content = content.getBytes();
            byte[] cipher = rsaUtil.encrypt_pub(bytes_content);
            for (byte b : cipher)
                System.out.print(b);
            System.out.println();

            byte [] bytes_dec = rsaUtil.decrypt_pri(cipher);

            String new_content = new String(bytes_dec);

            System.out.println(new_content);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
