package geektime.jdk.gm;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;

import javax.crypto.Cipher;

import com.tencent.crypto.provider.SMCSProvider;

public class Sm3Demo {
    public static void main(String[] args) throws Exception {
    	
    	String libpath = System.getProperty("java.library.path");
    	System.setProperty("java.library.path", libpath + ":" + System.getProperty("user.dir"));
    	System.out.println(System.getProperty("java.library.path"));

    	Security.addProvider(new SMCSProvider());
    	printProvider();
        Sm3();

    	KeyPairGenerator kpg = KeyPairGenerator.getInstance("SM2");
    	KeyPair kp = kpg.generateKeyPair();
    	Cipher cipher = Cipher.getInstance("SM2");
    	cipher.init(Cipher.ENCRYPT_MODE, kp.getPublic());
    	byte[] cipherText = cipher.doFinal("New world".getBytes());
    	
    	System.out.println("cipher " + cipher + " encode size: " + cipherText.length);
    }

	public static void Sm3() throws NoSuchAlgorithmException, UnsupportedEncodingException {


        MessageDigest md = MessageDigest.getInstance("SM3");
        md.update("HelloWorld".getBytes("UTF-8"));
        byte[] result = md.digest();
        System.out.println(new BigInteger(1, result).toString(16));
	}
    
    public static void printProvider() {
        String providerName = "SunJCE";
        Provider provider = Security.getProvider(providerName);
        if (provider == null) {
            System.out.println(providerName + " provider not installed");
            return;
        }
 
        System.out.println("Provider Name :"+ provider.getName());
        System.out.println("Provider Version :"+ provider.getVersionStr());
        System.out.println("Provider Info:" + provider.getInfo());
    }
}
