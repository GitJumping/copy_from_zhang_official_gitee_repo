package geektime.jdk.secret;

import java.security.Provider;
import java.security.Security;

public class ProviderDemo {

	public static void main(String[] args) {
        System.out.println("JDK加密服务提供者");  
        Provider[] pro = Security.getProviders();  
        for (Provider p : pro) {  
            System.out.println("Provider:" + p.getName() + " - version:" + p.getVersion());  
            System.out.println(p.getInfo());  
        }  
        System.out.println("");
        System.out.println("DK支持的消息摘要算法：");  
        for (String s : Security.getAlgorithms("MessageDigest")) {  
            System.out.println(s);  
        }
        System.out.println("");
        System.out.println("JDK支持的签名算法：");  
        for (String s : Security.getAlgorithms("Signature")) {  
            System.out.println(s);  
        }      
	}

}
