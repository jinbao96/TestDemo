package com.jimboo.util;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

/**
 * 数字签名  1：MD5withRSA，：将正文通过MD5数字摘要后，将密文 再次通过生成的RSA密钥加密，生成数字签名，
 * 		         将明文与密文以及公钥发送给对方，对方拿到私钥/公钥对数字签名进行解密，然后解密后的，与明文经过MD5加密进行比较 如果一致则通过
 * 		 2：使用Signature的API来实现MD5withRSA
 * 
 * @author Jimboo
 *
 * 2018年4月2日下午3:32:44
 */
public class MD5withRSA {

	/**
	 * 使用RSA生成一对钥匙
	 * 
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static KeyPair getKeyPair() throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(512);
		// 生成返回带有公钥和私钥的对象
		KeyPair generateKeyPair = keyPairGenerator.generateKeyPair();
		return generateKeyPair;
	}

	/**
	 * 生成私钥
	 * 
	 * @param str
	 * @return
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static PrivateKey getPrivateKey(KeyPair key) {
		PrivateKey generatePrivate = null;
		try {
			PrivateKey private1 = key.getPrivate();
			byte[] encoded = private1.getEncoded();
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
			KeyFactory factory = KeyFactory.getInstance("RSA");
			generatePrivate = factory.generatePrivate(keySpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return generatePrivate;
	}

	/**
	 * 私钥加密
	 * 
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static byte[] encrtpyByPrivateKey(byte[] bb, PrivateKey key)
			throws IllegalBlockSizeException, BadPaddingException {
		byte[] doFinal = null;
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			doFinal = cipher.doFinal(bb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doFinal;
	}

	/**
	 * 获取公钥
	 * 
	 * @param str
	 * @return
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static PublicKey getPublicKey(KeyPair keyPair) {
		PublicKey publicKey = null;
		try {
			PublicKey public1 = keyPair.getPublic();
			byte[] encoded = public1.getEncoded();
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
			KeyFactory factory = KeyFactory.getInstance("RSA");
			publicKey = factory.generatePublic(keySpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return publicKey;
	}

	/**
	 * 使用公钥解密
	 * 
	 * @param str
	 * @return
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] decryptPublicKey(byte[] b, PublicKey key) {
		byte[] doFinal = null;
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, key);
			doFinal = cipher.doFinal(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doFinal;
	}

	// 通过MD5加密
	public static byte[] encryptMD5(String str) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		byte[] digest2 = digest.digest(str.getBytes());
		return digest2;
	}

	// sign签名
	public static byte[] sign(String str, PrivateKey key)
			throws NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
		byte[] encryptMD5 = encryptMD5(str);
		byte[] encrtpyByPrivateKey = encrtpyByPrivateKey(encryptMD5, key);
		return encrtpyByPrivateKey;
	}

	// 校验
	public static boolean verify(String str, byte[] sign, PublicKey key) throws NoSuchAlgorithmException {
		byte[] encryptMD5 = encryptMD5(str);
		byte[] decodePublicKey = decryptPublicKey(sign, key);
		String a = new String(encryptMD5);
		String b = new String(decodePublicKey);
		if (a.equals(b)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Signature的用法 数字签名
	 * 
	 * @param args
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static byte[] signMethod(String str, PrivateKey key)
			throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		// 初始化 MD5withRSA
		Signature signature = Signature.getInstance("MD5withRSA");
		// 使用私钥
		signature.initSign(key);
		// 需要签名或校验的数据
		signature.update(str.getBytes());
		return signature.sign();// 进行数字签名
	}

	/**
	 * 数字校验
	 * 
	 * @param args
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static boolean verifyMethod(String str, byte[] sign, PublicKey key)
			throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		Signature signature = Signature.getInstance("MD5withRSA");
		signature.initVerify(key);
		signature.update(str.getBytes());
		return signature.verify(sign);
	}

	
}
