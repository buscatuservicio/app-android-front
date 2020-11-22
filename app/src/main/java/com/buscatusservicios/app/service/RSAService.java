package com.buscatusservicios.app.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAService {


    public static KeyPair genKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(515);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPairGenerator.generateKeyPair();//new String(Base64.getEncoder().encode(keyPair.getPublic().getEncoded()));
    }

    public static PublicKey getKey(String key) {
        PublicKey publicKey = null;
        try {
            byte[] byteKey = Base64.getDecoder().decode(key.getBytes());
            X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(byteKey);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            publicKey = kf.generatePublic(X509publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    public static String decryptionMessage(PrivateKey privateKey, String text) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPPadding"); //or try with "RSA"
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(Base64.getDecoder().decode(text.getBytes("UTF-8"))));
    }

    public static String encryptionMessage(PublicKey publicKey, String text) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPPadding"); //or try with "RSA"
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes("UTF-8")));
    }
}
