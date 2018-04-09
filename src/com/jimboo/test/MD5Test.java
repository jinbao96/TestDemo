package com.jimboo.test;

import com.jimboo.util.MD5Utils;

public class MD5Test {
	public static void main(String[] args) {
		String str = "123456";
		System.out.println("要加密的字符串:" + str);
		System.out.println("\n16位MD5加密");
		System.out.println(MD5Utils.md5Encrypt16Upper(str));
		System.out.println("\n32位MD5加密");
		System.out.println(MD5Utils.md5Encrypt32Upper(str));
	}
}
