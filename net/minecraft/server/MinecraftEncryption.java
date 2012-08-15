/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.InputStream;
/*		 */ import java.io.OutputStream;
/*		 */ import java.io.PrintStream;
/*		 */ import java.io.UnsupportedEncodingException;
/*		 */ import java.nio.charset.Charset;
/*		 */ import java.security.InvalidKeyException;
/*		 */ import java.security.Key;
/*		 */ import java.security.KeyFactory;
/*		 */ import java.security.KeyPair;
/*		 */ import java.security.KeyPairGenerator;
/*		 */ import java.security.MessageDigest;
/*		 */ import java.security.NoSuchAlgorithmException;
/*		 */ import java.security.PrivateKey;
/*		 */ import java.security.PublicKey;
/*		 */ import java.security.Security;
/*		 */ import java.security.spec.InvalidKeySpecException;
/*		 */ import java.security.spec.X509EncodedKeySpec;
/*		 */ import javax.crypto.BadPaddingException;
/*		 */ import javax.crypto.Cipher;
/*		 */ import javax.crypto.IllegalBlockSizeException;
/*		 */ import javax.crypto.NoSuchPaddingException;
/*		 */ import javax.crypto.SecretKey;
/*		 */ import javax.crypto.spec.SecretKeySpec;
/*		 */ import org.bouncycastle.crypto.BufferedBlockCipher;
/*		 */ import org.bouncycastle.crypto.engines.AESFastEngine;
/*		 */ import org.bouncycastle.crypto.io.CipherInputStream;
/*		 */ import org.bouncycastle.crypto.io.CipherOutputStream;
/*		 */ import org.bouncycastle.crypto.modes.CFBBlockCipher;
/*		 */ import org.bouncycastle.crypto.params.KeyParameter;
/*		 */ import org.bouncycastle.crypto.params.ParametersWithIV;
/*		 */ import org.bouncycastle.jce.provider.BouncyCastleProvider;
/*		 */ 
/*		 */ public class MinecraftEncryption
/*		 */ {
/*	36 */	 public static final Charset a = Charset.forName("ISO_8859_1");
/*		 */ 
/*		 */	 public static KeyPair b()
/*		 */	 {
/*		 */		 try
/*		 */		 {
/*	52 */			 KeyPairGenerator localKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
/*	53 */			 localKeyPairGenerator.initialize(1024);
/*		 */ 
/*	55 */			 return localKeyPairGenerator.generateKeyPair();
/*		 */		 } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
/*	57 */			 localNoSuchAlgorithmException.printStackTrace();
/*		 */ 
/*	59 */			 System.err.println("Key pair generation failed!");
/*	60 */		 }return null;
/*		 */	 }
/*		 */ 
/*		 */	 public static byte[] a(String paramString, PublicKey paramPublicKey, SecretKey paramSecretKey) {
/*		 */		 try {
/*	65 */			 return a("SHA-1", new byte[][] { paramString.getBytes("ISO_8859_1"), paramSecretKey.getEncoded(), paramPublicKey.getEncoded() });
/*		 */		 }
/*		 */		 catch (UnsupportedEncodingException localUnsupportedEncodingException)
/*		 */		 {
/*	72 */			 localUnsupportedEncodingException.printStackTrace();
/*		 */		 }
/*		 */ 
/*	75 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 private static byte[] a(String paramString, byte[][] paramArrayOfByte) {
/*		 */		 try {
/*	80 */			 MessageDigest localMessageDigest = MessageDigest.getInstance(paramString);
/*	81 */			 for (byte[] arrayOfByte1 : paramArrayOfByte) {
/*	82 */				 localMessageDigest.update(arrayOfByte1);
/*		 */			 }
/*	84 */			 return localMessageDigest.digest();
/*		 */		 } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
/*	86 */			 localNoSuchAlgorithmException.printStackTrace();
/*		 */		 }
/*		 */ 
/*	89 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public static PublicKey a(byte[] paramArrayOfByte) {
/*		 */		 try {
/*	94 */			 X509EncodedKeySpec localX509EncodedKeySpec = new X509EncodedKeySpec(paramArrayOfByte);
/*	95 */			 KeyFactory localKeyFactory = KeyFactory.getInstance("RSA");
/*	96 */			 return localKeyFactory.generatePublic(localX509EncodedKeySpec);
/*		 */		 } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
/*	98 */			 localNoSuchAlgorithmException.printStackTrace();
/*		 */		 } catch (InvalidKeySpecException localInvalidKeySpecException) {
/* 100 */			 localInvalidKeySpecException.printStackTrace();
/*		 */		 }
/* 102 */		 System.err.println("Public key reconstitute failed!");
/* 103 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public static SecretKey a(PrivateKey paramPrivateKey, byte[] paramArrayOfByte) {
/* 107 */		 return new SecretKeySpec(b(paramPrivateKey, paramArrayOfByte), "AES");
/*		 */	 }
/*		 */ 
/*		 */	 public static byte[] b(Key paramKey, byte[] paramArrayOfByte)
/*		 */	 {
/* 115 */		 return a(2, paramKey, paramArrayOfByte);
/*		 */	 }
/*		 */ 
/*		 */	 private static byte[] a(int paramInt, Key paramKey, byte[] paramArrayOfByte) {
/*		 */		 try {
/* 120 */			 return a(paramInt, paramKey.getAlgorithm(), paramKey).doFinal(paramArrayOfByte);
/*		 */		 } catch (IllegalBlockSizeException localIllegalBlockSizeException) {
/* 122 */			 localIllegalBlockSizeException.printStackTrace();
/*		 */		 } catch (BadPaddingException localBadPaddingException) {
/* 124 */			 localBadPaddingException.printStackTrace();
/*		 */		 }
/* 126 */		 System.err.println("Cipher data failed!");
/* 127 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 private static Cipher a(int paramInt, String paramString, Key paramKey) {
/*		 */		 try {
/* 132 */			 Cipher localCipher = Cipher.getInstance(paramString);
/* 133 */			 localCipher.init(paramInt, paramKey);
/* 134 */			 return localCipher;
/*		 */		 } catch (InvalidKeyException localInvalidKeyException) {
/* 136 */			 localInvalidKeyException.printStackTrace();
/*		 */		 } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
/* 138 */			 localNoSuchAlgorithmException.printStackTrace();
/*		 */		 } catch (NoSuchPaddingException localNoSuchPaddingException) {
/* 140 */			 localNoSuchPaddingException.printStackTrace();
/*		 */		 }
/* 142 */		 System.err.println("Cipher creation failed!");
/* 143 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 private static BufferedBlockCipher a(boolean paramBoolean, Key paramKey) {
/* 147 */		 BufferedBlockCipher localBufferedBlockCipher = new BufferedBlockCipher(new CFBBlockCipher(new AESFastEngine(), 8));
/* 148 */		 localBufferedBlockCipher.a(paramBoolean, new ParametersWithIV(new KeyParameter(paramKey.getEncoded()), paramKey.getEncoded(), 0, 16));
/* 149 */		 return localBufferedBlockCipher;
/*		 */	 }
/*		 */ 
/*		 */	 public static OutputStream a(SecretKey paramSecretKey, OutputStream paramOutputStream) {
/* 153 */		 return new CipherOutputStream(paramOutputStream, a(true, paramSecretKey));
/*		 */	 }
/*		 */ 
/*		 */	 public static InputStream a(SecretKey paramSecretKey, InputStream paramInputStream) {
/* 157 */		 return new CipherInputStream(paramInputStream, a(false, paramSecretKey));
/*		 */	 }
/*		 */ 
/*		 */	 static
/*		 */	 {
/*	40 */		 Security.addProvider(new BouncyCastleProvider());
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.MinecraftEncryption
 * JD-Core Version:		0.6.0
 */