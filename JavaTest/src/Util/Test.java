package Util;

import java.io.File;

public class Test {
    public static void main(String[] args) {
/*        try {
            RSAUtil.genKeyFile("/", 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

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
        }


    }
}
