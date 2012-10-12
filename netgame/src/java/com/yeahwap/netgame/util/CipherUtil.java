package com.yeahwap.netgame.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class CipherUtil {

	static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7',
		'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	static final MessageDigest md5DigestFunction;
	static {
		MessageDigest tmp;
		try {
			tmp = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			tmp = null;
		}
		md5DigestFunction = tmp;
	}

	/**
	 * MessageDigest.digest in NOT threadsafe, we must synchronized here
	 * 
	 * @param str
	 * @return
	 */
	synchronized public static String getMD5Str(String str) {
		byte[] tmp = md5DigestFunction.digest(str.getBytes());
		char[] md5 = new char[tmp.length * 2];
		for (int i = 0; i < tmp.length; i++) {
			md5[i * 2] = hexDigits[tmp[i] >> 4 & 0xf];
			md5[i * 2 + 1] = hexDigits[tmp[i] & 0xf];
		}
		return new String(md5);
	}

	public static void main(String[] args) {
		System.out.println(CipherUtil.getMD5Str(new Date().getTime()
				+ "127.0.0.1" + "123456"));
	}
}
