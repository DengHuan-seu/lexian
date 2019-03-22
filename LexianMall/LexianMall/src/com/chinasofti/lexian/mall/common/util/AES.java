package com.chinasofti.lexian.mall.common.util;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.RandomStringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AES {
	private static String defualtKey = RandomStringUtils.randomAlphabetic(16);

	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	public static String encryptCBC(String str, String key) {

		if (key == null || key.length() != 16) {
			key = defualtKey;
		}
		byte[] raw = key.getBytes();
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			IvParameterSpec iv = new IvParameterSpec(getIV().getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(str.getBytes());
			return new BASE64Encoder().encode(encrypted);
		} catch (Exception e) {
			return null;
		}
	}

	public static String decryptCBC(String sSrc, String sKey) {
		if (sKey == null || sKey.length() != 16) {
			sKey = defualtKey;
		}
		try {
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			IvParameterSpec iv = new IvParameterSpec(getIV().getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original);
			return originalString;
		} catch (Exception ex) {
			return null;
		}
	}

	public static String encryptECB(String str, String key) {

		if (key == null || key.length() != 16) {
			key = defualtKey;
		}
		byte[] raw = key.getBytes();
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(str.getBytes());
			return new BASE64Encoder().encode(encrypted);
		} catch (Exception e) {
			return null;
		}
	}

	public static String decryptECB(String sSrc, String sKey) {
		if (sKey == null || sKey.length() != 16) {
			sKey = defualtKey;
		}
		try {
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original);
			return originalString;
		} catch (Exception ex) {
			return null;
		}
	}

	public static String getIV() {
		return "L+\\~f4,Ir)b$=pkf";
	}
}
