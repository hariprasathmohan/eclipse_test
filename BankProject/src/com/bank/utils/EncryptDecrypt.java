//$Id$
package com.bank.utils;

import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class EncryptDecrypt {

	private KeySpec ks;
	private SecretKeyFactory skf;
	private Cipher cipher;
	private String myEncryptionKey;
	SecretKey key;
	
	public EncryptDecrypt() {
		try {
			myEncryptionKey = "secretkeysecretkeysecret";
	        ks = new DESedeKeySpec(myEncryptionKey.getBytes("UTF8"));
	        skf = SecretKeyFactory.getInstance("DESede");
	        cipher = Cipher.getInstance("DESede");
	        key = skf.generateSecret(ks);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String encrypt(String password) {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = password.getBytes("UTF8");
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.getEncoder().encode(encryptedText));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }
	
	public String decrypt(String password) {
        String decryptedText=null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.getDecoder().decode(password);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText= new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }
}
