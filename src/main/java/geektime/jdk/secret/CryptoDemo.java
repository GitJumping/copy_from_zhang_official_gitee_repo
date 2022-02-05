package geektime.jdk.secret;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class CryptoDemo {

	public static void main(String[] args) throws Exception {
		String content = "Hello world";
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        KeyPair pair = generator.generateKeyPair();
        //私钥
        PrivateKey prikey = pair.getPrivate();
        //公钥
        PublicKey pubKey = pair.getPublic();

        //非对称加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.PUBLIC_KEY,pubKey);
        byte[] m = cipher.doFinal(content.getBytes());

        Cipher cipher1 = Cipher.getInstance("RSA");
        //非对称解密
        cipher1.init(Cipher.PRIVATE_KEY,prikey);
        cipher1.update(m);
        byte[] s = cipher1.doFinal();
        System.out.println("String: " + new String(s));

	}

}
