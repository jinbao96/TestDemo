package com.jimboo.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Jimboo
 *
 *         2018年4月2日下午3:33:12
 */
public class MD5Utils {
	/**
	 * 生成32位小写md5码
	 * 
	 * @param str
	 * @return
	 */
	public static String md5Encrypt32Lower(String str) {

		try {
			// 得到一个信息摘要器
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] result = digest.digest(str.getBytes());
			StringBuffer buffer = new StringBuffer();
			// 把每一个byte 做一个与运算 0xff;
			for (byte b : result) {
				// 与运算
				int number = b & 0xff;// 加盐
				String str1 = Integer.toHexString(number);
				if (str1.length() == 1) {
					buffer.append("0");
				}
				buffer.append(str1);
			}

			// 标准的md5加密后的结果
			return buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 生成32位大写md5码
	 * 
	 * @param str
	 * @return
	 */
	public static String md5Encrypt32Upper(String str) {
		String password_secret = md5Encrypt32Lower(str);
		return password_secret.toUpperCase();
	}

	/**
	 * 生成16位小写md5码
	 * 
	 * @param str
	 */
	public static String md5Encrypt16Lower(String str) {
		return md5Encrypt32Lower(str).substring(8, 24);
	}

	/**
	 * 生成16位大写md5码
	 * 
	 * @param str
	 */
	public static String md5Encrypt16Upper(String str) {
		return md5Encrypt16Lower(str).toUpperCase();
	}
}
