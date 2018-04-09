package com.jimboo.test;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import com.jimboo.util.MD5withRSA;

public class MD5withRSATest {
	public static void main(String[] args) throws NoSuchAlgorithmException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeyException, SignatureException {
		String str = "这是一串数据,加密,加密,在加密";
		System.out.println("待加密的明文：" + str);
		byte[] encryptMD5 = MD5withRSA.encryptMD5(str);
		System.out.println("\nMD5加密后：" + encryptMD5);
		// 获取钥匙对
		KeyPair keyPair = MD5withRSA.getKeyPair();
		// 获取公钥
		PublicKey publicKey = MD5withRSA.getPublicKey(keyPair);
		// 获取私钥
		PrivateKey privateKey = MD5withRSA.getPrivateKey(keyPair);

		byte[] sign = MD5withRSA.sign(str, privateKey);
		boolean verify = MD5withRSA.verify(str, sign, publicKey);
		System.out.println("\n是否一致" + verify);

		byte[] encrtpyByPrivateKey = MD5withRSA.encrtpyByPrivateKey(encryptMD5, privateKey);
		System.out.println("\n使用私钥加密过后：" + encrtpyByPrivateKey);
		byte[] decryptPublicKey = MD5withRSA.decryptPublicKey(encrtpyByPrivateKey, publicKey);
		System.out.println("\n使用公钥解密过后：" + decryptPublicKey);
		System.out.print("\n判断解密过后，是否一致");
		System.out.println(new String(decryptPublicKey).equals(new String(encryptMD5)));

		/******************** 基于SignatureAPI签名 *************************************/
		System.out.println("\n/******************** 基于SignatureAPI签名 *************************************/");
		String signStr = "基于SignatureAPI签名";
		byte[] signMethod = MD5withRSA.signMethod(signStr, privateKey);

		boolean verifyMethod = MD5withRSA.verifyMethod(signStr, signMethod, publicKey);
		System.out.println("\n使用SignatureAPI 数字签名是否一致：" + verifyMethod);
	}
}
