package com.jimboo.test;

import java.util.Map;

import com.jimboo.util.RSAUtils;

public class RSATest2 {
	public static void main(String[] args) {
		try {
			Map<String, String> keyMap = RSAUtils.createKeys(1024);
			String privateKey = keyMap.get("privateKey");
			String publicKey = keyMap.get("publicKey");
			System.out.println("公钥: \n\r" + publicKey);
			System.out.println("私钥: \n\r" + privateKey);
			
			System.out.println("私钥加密-公钥解密");
			String str = "这是一串数据,加密,加密,在加密";
			System.out.println("\r明文：\r\n" + str);
			System.out.println("\r明文大小：\r\n" + str.getBytes().length);
			
			String encodedData = RSAUtils.privateEncrypt(str, RSAUtils.getPrivateKey(privateKey));
			System.out.println("密文：\r\n" + encodedData);
			String decodedData = RSAUtils.publicDecrypt(encodedData, RSAUtils.getPublicKey(publicKey));
			System.out.println("解密后文字: \r\n" + decodedData);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
