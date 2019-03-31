package Util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class Test {
    public static void main(String[] args) {
/*        try {
            RSAUtil.genKeyFile("/", 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
/*
        File file_pub = new File("/id_rsa.pub");
        File file_pri = new File("/id_rsa.pri");


        try {
            RSAUtil rsaUtil = new RSAUtil(file_pub, file_pri);

            String str = "hello word";

            byte[] cipher = rsaUtil.encrypt_pub(str.getBytes());


            byte[] content = rsaUtil.decrypt_pri(cipher);

            System.out.println(new String(content));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        try {
            KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("rsa");

            keyGenerator.initialize(2048, new SecureRandom());
            KeyPair keyPair = keyGenerator.generateKeyPair();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            String str = "HelloWorddsfdsfsdfsdfsdfsdfsdfsdfsdfsfsf";
            RSAUtil rsaUtil = new RSAUtil(rsaPublicKey, rsaPrivateKey);
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {

                byte[] cipher = rsaUtil.encrypt_pub(str.getBytes());
                byte[] content = rsaUtil.decrypt_pri(cipher);
                //boolean res = rsaUtil.check_sign(str.getBytes(), cipher);

            }
            long endTime = System.currentTimeMillis();
            System.out.println(endTime - startTime);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
