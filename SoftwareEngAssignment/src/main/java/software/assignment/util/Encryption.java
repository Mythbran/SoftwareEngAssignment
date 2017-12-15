package software.assignment.util;

import java.security.SecureRandom;
import org.apache.catalina.realm.MessageDigestCredentialHandler;

public class Encryption{
    
    final private static String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    
    final private static int SALTLENGTH = 32; 
    final private static int NUMOFIT = 100; 
    final private static String HASH = "sha-256";
    
    //randomize characters for a salt
    public static String random(int length){
        StringBuilder buffer = new StringBuilder(length);
        SecureRandom random = new SecureRandom();
        
        for (int i = 0; i < length; i++){
            buffer.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return buffer.toString();
    }
    
    
    //COMPARE TWO PASSWORDS 
    public static boolean equals(String str1, String str2){
        if(str1 != null && str2 != null){
            return str1.equals(str2);
        }else{
            return false; 
        }
    }
    
    
    //HASHING THE PASSWORD 
    public static String encode(String pass){
        MessageDigestCredentialHandler handler = new MessageDigestCredentialHandler();
        
        try{
            handler.setAlgorithm(HASH);
        }catch(Exception e){
            throw new RuntimeException(e.getCause());
        }
        
        handler.setIterations(NUMOFIT);
        handler.setSaltLength(SALTLENGTH);
        handler.setEncoding("utf-8");
        
        String encoded = handler.mutate(pass);
        return encoded;
    }
    
    //CHECK THE ENCODED PASSWORD WITH EACHOTHER
    public static boolean match(String pass, String code){
        MessageDigestCredentialHandler handler = new MessageDigestCredentialHandler();
        try{
            handler.setAlgorithm(HASH);
        }catch(Exception e){
            throw new RuntimeException(e.getCause());
        }
        
        handler.setIterations(NUMOFIT);
        handler.setSaltLength(SALTLENGTH);
        handler.setEncoding("UTF-8");
        
        return handler.matches(pass, code);
    }
    
}