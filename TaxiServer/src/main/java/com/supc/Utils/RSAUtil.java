package com.supc.Utils;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintWriter;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtil {
    private RSAPrivateKey key_pri;
    private RSAPublicKey key_pub;
    private int keySize;
    private static int D = 11;
    private static final String SIGN_ALGORITHMS = "SHA256WithRSA";

    private void check() throws Exception {
        if ("none".equals(type())) {
            throw new Exception("密钥为空");
        }

        if (key_pub != null) {
            keySize = key_pub.getModulus().bitLength();
        } else {
            keySize = key_pri.getModulus().bitLength();
        }

        keySize /= Byte.SIZE;

    }


    public String type() {
        String res = null;
        if (key_pub == null && key_pri == null) {
            res = "none";
        } else if (key_pub != null && key_pri == null) {
            res = "pub";
        } else if (key_pub == null && key_pri != null) {
            res = "pri";
        } else {
            res = "all";
        }

        return res;

    }

    /**
     * 直接使用公钥和私钥初始化对象
     *
     * @param key_pub 公钥
     * @param key_pri 私钥
     * @throws Exception 异常
     */
    public RSAUtil(RSAPublicKey key_pub, RSAPrivateKey key_pri) throws Exception {
        this.key_pub = key_pub;
        this.key_pri = key_pri;
        check();
    }

    /**
     * 使用公钥和私钥的byte[] 对象初始化对象
     *
     * @param bytes_pub 公钥
     * @param bytes_pri 私钥
     * @throws Exception
     */
    public RSAUtil(byte[] bytes_pub, byte[] bytes_pri) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("rsa");
        if (bytes_pub != null && bytes_pub.length != 0) {
            X509EncodedKeySpec keySpec_pub = new X509EncodedKeySpec(bytes_pub);
            this.key_pub = (RSAPublicKey) keyFactory.generatePublic(keySpec_pub);
        }

        if (bytes_pri != null && bytes_pri.length != 0) {
            PKCS8EncodedKeySpec keySpec_pri = new PKCS8EncodedKeySpec(bytes_pri);
            this.key_pri = (RSAPrivateKey) keyFactory.generatePrivate(keySpec_pri);
        }

        check();
    }

    /**
     * 使用字符串对象初始化对象
     *
     * @param str_pub 公钥字符串
     * @param str_pri 私钥字符串
     * @throws Exception
     */
    public RSAUtil(String str_pub, String str_pri) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("rsa");

        if (str_pub != null) {
            byte[] bytes_pub = Base64.decode(str_pub);
            X509EncodedKeySpec keySpec_pub = new X509EncodedKeySpec(bytes_pub);
            this.key_pub = (RSAPublicKey) keyFactory.generatePublic(keySpec_pub);
        }
        if (str_pri != null) {
            byte[] bytes_pri = Base64.decode(str_pri);
            PKCS8EncodedKeySpec keySpec_pri = new PKCS8EncodedKeySpec(bytes_pri);
            this.key_pri = (RSAPrivateKey) keyFactory.generatePrivate(keySpec_pri);
        }

        check();
    }

    /**
     * 使用文件对象初始化对象
     *
     * @param file_pub 公钥文件
     * @param file_pri 私钥文件
     * @throws Exception
     */
    public RSAUtil(File file_pub, File file_pri) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("rsa");
        if (file_pub != null) {
            String str_pub = FileUtil.readStringFile(file_pub);
            byte[] bytes_pub = Base64.decode(str_pub);
            X509EncodedKeySpec keySpec_pub = new X509EncodedKeySpec(bytes_pub);
            this.key_pub = (RSAPublicKey) keyFactory.generatePublic(keySpec_pub);
        }

        if (file_pub != null) {
            String str_pri = FileUtil.readStringFile(file_pri);
            byte[] bytes_pri = Base64.decode(str_pri);
            PKCS8EncodedKeySpec keySpec_pri = new PKCS8EncodedKeySpec(bytes_pri);
            this.key_pri = (RSAPrivateKey) keyFactory.generatePrivate(keySpec_pri);
        }
        check();
    }

    public static void genKeyFile(String dir_path, int keySize) throws Exception {
        File file = new File(dir_path);
        genKeyFile(file, keySize);
    }

    public static void genKeyFile(File dir, int keySize) throws Exception {
        if (!dir.isDirectory()) {
            throw new Exception("接收参数不为目录！");
        }

        String path = dir.getAbsolutePath() + File.separatorChar;

        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("rsa");
        keyGenerator.initialize(keySize, new SecureRandom());

        KeyPair keyPair = keyGenerator.generateKeyPair();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();

        String rsaPrivateKeyString = Base64.encode(rsaPrivateKey.getEncoded());
        String rsaPublicKeyString = Base64.encode(rsaPublicKey.getEncoded());

        PrintWriter out1;
        PrintWriter out2;

        out1 = new PrintWriter(path + "id_rsa.pub");
        out2 = new PrintWriter(path + "id_rsa.pri");

        out1.write(rsaPublicKeyString);
        out2.write(rsaPrivateKeyString);

        out1.close();
        out2.close();
    }

    public byte[] encrypt(Cipher cipher, byte[] content) throws Exception {
        final int groupSize = keySize - D;
        int groupNum = content.length % groupSize == 0 ? content.length / groupSize
                : content.length / groupSize + 1;
        byte[] outBuffer = new byte[keySize * groupNum];

        for (int i = 0; i * groupSize < content.length; i++) {
            if (content.length - i * groupSize > groupSize) {
                cipher.doFinal(content, i * groupSize, groupSize, outBuffer, keySize * i);
            } else {
                cipher.doFinal(content, i * groupSize, content.length - i * groupSize, outBuffer, keySize * i);
            }
        }

        return outBuffer;
    }

    public byte[] decrypt(Cipher cipher, byte[] secret) throws Exception {
        int count = secret.length / keySize;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (int i = 0; i < count; i++) {
            out.write(cipher.doFinal(secret, keySize * i, keySize));
        }
        byte[] res = out.toByteArray();
        out.reset();
        out.close();
        return res;
    }

    /**
     * 公钥加密
     *
     * @param content 要加密的数据
     * @return 加密后的数据
     * @throws Exception
     */
    public byte[] encrypt_pub(byte[] content) throws Exception {
        if (key_pub == null) {
            throw new Exception("公钥为null！");
        }
        Cipher cipher = Cipher.getInstance("rsa");
        cipher.init(Cipher.ENCRYPT_MODE, key_pub);
        return encrypt(cipher, content);
    }

    /**
     * 公钥解密
     *
     * @param secret 要解密的数据
     * @return 解密后的数据
     * @throws Exception
     */
    public byte[] decrypt_pub(byte[] secret) throws Exception {
        if (key_pub == null) {
            throw new Exception("公钥为null！");
        }
        Cipher cipher = Cipher.getInstance("rsa");
        cipher.init(Cipher.DECRYPT_MODE, key_pub);
        return decrypt(cipher, secret);
    }

    /**
     * 私钥加密
     *
     * @param content 要加密的数据
     * @return 加密后的数据
     * @throws Exception
     */
    public byte[] encrypt_pri(byte[] content) throws Exception {
        if (key_pri == null) {
            throw new Exception("私钥为null！");
        }
        Cipher cipher = Cipher.getInstance("rsa");
        cipher.init(Cipher.ENCRYPT_MODE, key_pri);
        return encrypt(cipher, content);
    }

    /**
     * 私钥解密
     *
     * @param secret 要解密的数据
     * @return 解密后的数据
     * @throws Exception
     */
    public byte[] decrypt_pri(byte[] secret) throws Exception {
        if (key_pri == null) {
            throw new Exception("私钥为null！");
        }
        Cipher cipher = Cipher.getInstance("rsa");
        cipher.init(Cipher.DECRYPT_MODE, key_pri);
        return decrypt(cipher, secret);
    }

    /**
     * 生成数字签名，签名算法SHA256WithRSA
     *
     * @param content 数据内容
     * @return 签名
     * @throws Exception
     */
    public byte[] sign(byte[] content) throws Exception {
        if (key_pri == null) {
            throw new Exception("私钥为null！");
        }
        java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
        signature.initSign(key_pri);
        signature.update(content);
        return signature.sign();
    }

    /**
     * 校验签名，签名算法SHA256WithRSA
     *
     * @param content 数据内容
     * @param sign    签名
     * @return 是否通过校验
     * @throws Exception
     */
    public boolean check_sign(byte[] content, byte[] sign) throws Exception {
        if (key_pub == null) {
            throw new Exception("公钥为null！");
        }
        java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
        signature.initVerify(key_pub);
        signature.update(content);
        return signature.verify(sign);
    }

    /**
     * 对二进制数据进行Base64编码
     *
     * @param binaryData 要编码的原始二进制数据
     * @return com.sun.org.apache.xml.internal.security.utils.Base64.encode(binaryData);
     */
    public static String base64Encode(byte[] binaryData) {
        return Base64.encode(binaryData);
    }

    /**
     * 对Base64编码字符串解码为二进制数，此方法能自动忽略原始字符串中的换行符。
     *
     * @param encoded 表示Base64编码的字符串
     * @return com.sun.org.apache.xml.internal.security.utils.Base64.decode(encoded);
     * @throws Base64DecodingException
     */
    public static byte[] base64Decode(String encoded) throws Base64DecodingException {
        return Base64.decode(encoded);
    }


}

