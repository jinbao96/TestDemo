package com.jimboo.test;

import java.util.Map;

import com.jimboo.util.RSAUtils;

public class RSATest {
	public static void main(String[] args) {
		try {
			Map<String, String> keyMap = RSAUtils.createKeys(1024);
			String publicKey = keyMap.get("publicKey");
			String privateKey = keyMap.get("privateKey");
			System.out.println("公钥: \n\r" + publicKey);
			System.out.println("私钥： \n\r" + privateKey);

			System.out.println("公钥加密——私钥解密");
			String str = "这是一串数据,加密,加密,在加密";
			System.out.println("\r明文：\r\n" + str);
			System.out.println("\r明文大小：\r\n" + str.getBytes().length);

			String encodedData = RSAUtils.publicEncrypt(str, RSAUtils.getPublicKey(publicKey));
			System.out.println("密文：\r\n" + encodedData);
			String decodedData = RSAUtils.privateDecrypt(encodedData, RSAUtils.getPrivateKey(privateKey));
			System.out.println("解密后文字: \r\n" + decodedData);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
