package com.fileserve.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileServeUtils {

	public static String sha256Hash(String text) {
		return hash("SHA-256", text);
	}

	public static String hash(String algorithm, String text) {
		byte[] bytes = digestEncodeQuietly(algorithm, text);
		BigInteger bigInt = new BigInteger(bytes);
		return bigInt.toString(16);
	}

	public static byte[] digestEncodeQuietly(String algorithm, String text) {
		try {
			return digestEncode(algorithm, text);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return new byte[0];
	}

	public static byte[] digestEncode(String algorithm, String text) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(algorithm);
		md.update(text.getBytes("UTF-8"));
		byte[] digest = md.digest();
		return digest;
	}
}
