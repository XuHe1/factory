package com.kyx.factory.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {

	private static final Logger logger = LoggerFactory.getLogger(EncryptUtil.class);

	private static MessageDigest digest;
	private static MessageDigest md5;
	private static final Object DIGEST_LOCK = new Object();
	public static final String SECRET_KEY = "4bbf90_SnRequestAPI_50a7abf";

	static {
		// Create a message digest instance.
		logger.debug("ssdfs{}", "hello");
		logger.debug("ssdfs{}", "hello", "hi");
		try {
			digest = MessageDigest.getInstance("SHA");
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// Log.error(LocaleUtils.getLocalizedString("admin.error"), e);
		}

	}
	
	public static String hmacSHA1Encrypt(String encryptText, String encryptKey) {
		// 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
		SecretKey secretKey = new SecretKeySpec(encryptKey.getBytes(), "HmacSHA1");
		// 生成一个指定 Mac 算法 的 Mac 对象
		Mac mac = null;
		try {
			mac = Mac.getInstance("HmacSHA1");
			// 用给定密钥初始化 Mac 对象
			mac.init(secretKey);
		} catch (NoSuchAlgorithmException e) {
			logger.error("加密算法错误", e);
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			logger.error("加密算法错误", e);
			e.printStackTrace();
		}
		// 完成 Mac 操作
		byte[] bytes = mac.doFinal(encryptText.getBytes());
		//转为16进制
		return encodeHex(bytes);
	}


	/** Java标准包的MD5加密，长度为32位字符串，返回的a-f是小写 */
	public static String getMD5(String inStr) {
		return getMD5(inStr, "UTF-8");
	}

	private static String getMD5(String inStr, String charsetName) {
		if (inStr == null || inStr.length() == 0) {
			return null;
		}
		try {
			byte[] bytes = inStr.getBytes(charsetName);
			return getMD5(bytes);
		} catch (UnsupportedEncodingException e) {
			logger.error("md5加密字符编码出错:", e);
		}
		return null;
	}

	/** Java标准包的MD5加密，长度为32位字符串，返回的a-f是小写 */
	public static String getMD5(byte[] bytes) {
		if (bytes == null || bytes.length <= 0) {
			return null;
		}
		byte[] digest;
		try {
			digest = md5.digest(bytes);
			// 返回的是byet[]，要转化为String存储比较方便
			return encodeHex(digest);
		} catch (Exception e) {
			logger.error("md5加密出错:", e);
		}
		return null;
	}

	/** 根据文件对象，获取MD5字符串，长度为32位，返回的a-f是小写 */
	public static String getMD5FromFile(File file) {
		String md5Str = "";
		if (file == null) {
			return md5Str;
		}
		try {
			FileInputStream inputStream = new FileInputStream(file);
			byte[] bytes = new byte[1024];
			int len = 0;
			MessageDigest messagedigest = MessageDigest.getInstance("MD5");
			while ((len = inputStream.read(bytes)) > 0) {
				messagedigest.update(bytes, 0, len);
			}
			md5Str = ByteConvert.bytes2HexString(messagedigest.digest());
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
		}

		return md5Str.toLowerCase();
	}


	/**
	 * Turns an array of bytes into a String representing each byte as an
	 * unsigned hex number.
	 * <p/>
	 * Method by Santeri Paavolainen, Helsinki Finland 1996<br>
	 * (c) Santeri Paavolainen, Helsinki Finland 1996<br>
	 * Distributed under LGPL.
	 * 
	 * @param bytes
	 *            an array of bytes to convert to a hex-string
	 * @return generated hex string
	 */
	public static String encodeHex(byte[] bytes) {
		StringBuilder buf = new StringBuilder(bytes.length * 2);
		int i;

		for (i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}

	/**
	 * Turns a hex encoded string into a byte array. It is specifically meant to
	 * "reverse" the toHex(byte[]) method.
	 * 
	 * @param hex
	 *            a hex encoded String to transform into a byte array.
	 * @return a byte array representing the hex String[
	 */
	public static byte[] decodeHex(String hex) {
		char[] chars = hex.toCharArray();
		byte[] bytes = new byte[chars.length / 2];
		int byteCount = 0;
		for (int i = 0; i < chars.length; i += 2) {
			int newByte = 0x00;
			newByte |= hexCharToByte(chars[i]);
			newByte <<= 4;
			newByte |= hexCharToByte(chars[i + 1]);
			bytes[byteCount] = (byte) newByte;
			byteCount++;
		}
		return bytes;
	}

	/**
	 * Returns the the byte value of a hexadecmical char (0-f). It's assumed
	 * that the hexidecimal chars are lower case as appropriate.
	 * 
	 * @param ch
	 *            a hexedicmal character (0-f)
	 * @return the byte value of the character (0x00-0x0F)
	 */
	private static byte hexCharToByte(char ch) {
		switch (ch) {
		case '0':
			return 0x00;
		case '1':
			return 0x01;
		case '2':
			return 0x02;
		case '3':
			return 0x03;
		case '4':
			return 0x04;
		case '5':
			return 0x05;
		case '6':
			return 0x06;
		case '7':
			return 0x07;
		case '8':
			return 0x08;
		case '9':
			return 0x09;
		case 'a':
			return 0x0A;
		case 'b':
			return 0x0B;
		case 'c':
			return 0x0C;
		case 'd':
			return 0x0D;
		case 'e':
			return 0x0E;
		case 'f':
			return 0x0F;
		}
		return 0x00;
	}

//	public static void main(String[] args) {
//
//		String encode = getMD5("123456");
//		System.out.println("decode=" + encode);
//
//		try {
//			byte bytes[] = md5.digest("123456".getBytes("UTF-8"));
//			for (byte b: bytes) {
//				System.out.println(b);
//			}
//			//System.out.println(new String(bytes, "UTF-8"));
//			System.out.println(encodeHex(bytes));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//
//
//		// 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
//		SecretKey secretKey = new SecretKeySpec("4bbf90_SmartWeatherAPI_50a7abf".getBytes(), "HmacMD5");
//		// 生成一个指定 Mac 算法 的 Mac 对象
//		Mac mac = null;
//		try {
//			mac = Mac.getInstance("HmacMD5");
//			// 用给定密钥初始化 Mac 对象
//			mac.init(secretKey);
//		} catch (NoSuchAlgorithmException e) {
//			logger.error("加密算法错误", e);
//			e.printStackTrace();
//		} catch (InvalidKeyException e) {
//			logger.error("加密算法错误", e);
//			e.printStackTrace();
//		}
//		// 完成 Mac 操作
//		byte bytes[] =  mac.doFinal("123456".getBytes());
//		try {
//			System.out.println(new String(bytes, "UTF-8"));
//			//System.out.println(new String(hmacSHA1Encrypt("xuhe", "4bbf90_SmartWeatherAPI_50a7abf"), "UTF-8"));
//			//System.out.println(encodeHex(hmacSHA1Encrypt("xuhe", "4bbf90_SmartWeatherAPI_50a7abf")));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//
//	}
}