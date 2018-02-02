package br.com.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {

	public String encrypt(String password) {
		String encryptedPassword = null;
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(password.getBytes("UTF-8"));
			
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
			  hexString.append(String.format("%02X", 0xFF & b));
			}
			encryptedPassword = hexString.toString();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			System.out.println("Erro ao criptografar a senha:\n" + e.getMessage());
		}
		return encryptedPassword;
	}
}
