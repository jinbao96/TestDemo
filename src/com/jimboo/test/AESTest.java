package com.jimboo.test;

import com.jimboo.util.AESUtil;

public class AESTest {
	
	private static final String KEY = "123456";
	
	public static void main(String[] args) {
		
		String content = "{'repairPhone':'18547854787','customPhone':'12365478965','captchav':'58m7'}";
		System.out.println("加密前：" + content);
		System.out.println("加密密钥和解密密钥：" + KEY);
		String encrypt = AESUtil.encrypt(content, KEY);
		System.out.println("加密后：" + encrypt);
		String decrypt = AESUtil.decrypt(encrypt, KEY);
		System.out.println("解密后：" + decrypt);
	}
}
