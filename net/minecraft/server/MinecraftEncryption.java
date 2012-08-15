package net.minecraft.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.io.CipherInputStream;
import org.bouncycastle.crypto.io.CipherOutputStream;
import org.bouncycastle.crypto.modes.CFBBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class MinecraftEncryption
{
	public static final Charset a = Charset.forName("ISO_8859_1");

	public static KeyPair b()
	{
		try
		{
			KeyPairGenerator localKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
			localKeyPairGenerator.initialize(1024);

			return localKeyPairGenerator.generateKeyPair();
		} catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
			localNoSuchAlgorithmException.printStackTrace();

			System.err.println("Key pair generation failed!");
		}return null;
	}

	public static byte[] a(String paramString, PublicKey paramPublicKey, SecretKey paramSecretKey) {
		try {
			return a("SHA-1", new byte[][] { paramString.getBytes("ISO_8859_1"), paramSecretKey.getEncoded(), paramPublicKey.getEncoded() });
		}
		catch (UnsupportedEncodingException localUnsupportedEncodingException)
		{
			localUnsupportedEncodingException.printStackTrace();
		}

		return null;
	}

	private static byte[] a(String paramString, byte[][] paramArrayOfByte) {
		try {
			MessageDigest localMessageDigest = MessageDigest.getInstance(paramString);
			for (byte[] arrayOfByte1 : paramArrayOfByte) {
				localMessageDigest.update(arrayOfByte1);
			}
			return localMessageDigest.digest();
		} catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
			localNoSuchAlgorithmException.printStackTrace();
		}

		return null;
	}

	public static PublicKey a(byte[] paramArrayOfByte) {
		try {
			X509EncodedKeySpec localX509EncodedKeySpec = new X509EncodedKeySpec(paramArrayOfByte);
			KeyFactory localKeyFactory = KeyFactory.getInstance("RSA");
			return localKeyFactory.generatePublic(localX509EncodedKeySpec);
		} catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
			localNoSuchAlgorithmException.printStackTrace();
		} catch (InvalidKeySpecException localInvalidKeySpecException) {
			localInvalidKeySpecException.printStackTrace();
		}
		System.err.println("Public key reconstitute failed!");
		return null;
	}

	public static SecretKey a(PrivateKey paramPrivateKey, byte[] paramArrayOfByte) {
		return new SecretKeySpec(b(paramPrivateKey, paramArrayOfByte), "AES");
	}

	public static byte[] b(Key paramKey, byte[] paramArrayOfByte)
	{
		return a(2, paramKey, paramArrayOfByte);
	}

	private static byte[] a(int paramInt, Key paramKey, byte[] paramArrayOfByte) {
		try {
			return a(paramInt, paramKey.getAlgorithm(), paramKey).doFinal(paramArrayOfByte);
		} catch (IllegalBlockSizeException localIllegalBlockSizeException) {
			localIllegalBlockSizeException.printStackTrace();
		} catch (BadPaddingException localBadPaddingException) {
			localBadPaddingException.printStackTrace();
		}
		System.err.println("Cipher data failed!");
		return null;
	}

	private static Cipher a(int paramInt, String paramString, Key paramKey) {
		try {
			Cipher localCipher = Cipher.getInstance(paramString);
			localCipher.init(paramInt, paramKey);
			return localCipher;
		} catch (InvalidKeyException localInvalidKeyException) {
			localInvalidKeyException.printStackTrace();
		} catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
			localNoSuchAlgorithmException.printStackTrace();
		} catch (NoSuchPaddingException localNoSuchPaddingException) {
			localNoSuchPaddingException.printStackTrace();
		}
		System.err.println("Cipher creation failed!");
		return null;
	}

	private static BufferedBlockCipher a(boolean paramBoolean, Key paramKey) {
		BufferedBlockCipher localBufferedBlockCipher = new BufferedBlockCipher(new CFBBlockCipher(new AESFastEngine(), 8));
		localBufferedBlockCipher.a(paramBoolean, new ParametersWithIV(new KeyParameter(paramKey.getEncoded()), paramKey.getEncoded(), 0, 16));
		return localBufferedBlockCipher;
	}

	public static OutputStream a(SecretKey paramSecretKey, OutputStream paramOutputStream) {
		return new CipherOutputStream(paramOutputStream, a(true, paramSecretKey));
	}

	public static InputStream a(SecretKey paramSecretKey, InputStream paramInputStream) {
		return new CipherInputStream(paramInputStream, a(false, paramSecretKey));
	}

	static
	{
		Security.addProvider(new BouncyCastleProvider());
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.MinecraftEncryption
 * JD-Core Version:		0.6.0
 */