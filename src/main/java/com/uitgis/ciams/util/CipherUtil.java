package com.uitgis.ciams.util;

import java.nio.charset.StandardCharsets;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CipherUtil {

	private static String publicKey;

	private static KeyPair keyPair;

	static {
		 KeyPairGenerator gen;
		 SecureRandom secureRandom = new SecureRandom();
		try {
			gen = KeyPairGenerator.getInstance("RSA");
			gen.initialize(1024, secureRandom);

	        keyPair = gen.genKeyPair();
	        publicKey = Base64.encodeBase64String(keyPair.getPublic().getEncoded());

		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage());
		}
	}

	public static String getPublicKey () {
		return publicKey;
	}

	public static String decryptRSA (String target) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("RSA");
		byte[] byteEncrypted = Base64.decodeBase64(target);

        cipher.init (Cipher.DECRYPT_MODE, keyPair.getPrivate());

        byte[] bytePlain = cipher.doFinal(byteEncrypted);

        return  new String(bytePlain, StandardCharsets.UTF_8);

	}
}
