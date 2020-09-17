package com.nxn.sau.security.util;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class DESEncryption
{
	
	Key key = null;
	
	public DESEncryption () 
	{
		// Clave de 64 bits, dado que DES utiliza 56 bits
		byte[] secret = { 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08 };
		
		try
		{
			SecretKeyFactory skf = SecretKeyFactory.getInstance ( "DES" );
			key = skf.generateSecret ( new DESKeySpec ( secret ) );
		}
		catch ( NoSuchAlgorithmException nsae )
		{
			nsae.printStackTrace ();
		}
		catch ( InvalidKeyException ike )
		{
			ike.printStackTrace ();
		}
		catch ( InvalidKeySpecException ikse )
		{
			ikse.printStackTrace ();
		}
	}
	
	public Key getKey () 
	{
		return key;
	}
	
	public void setKey ( Key key ) 
	{
		this.key = key;
	}
	
	/**
	 * Encripta un String utilizando el algoritmo DES
	 * 
	 * @param clearText
	 *            Texto en ckaro a encriptar
	 * @return texto encriptado en base 64
	 */
	public String encrypt ( String clearText, Key key )
	{

		String cipherTextB64 = "";

		try
		{

			// Necesitamos un cifrador
			Cipher cipher = Cipher.getInstance ( "DES" );

			// Ciframos el texto en claro
			cipher.init ( Cipher.ENCRYPT_MODE, key );
			byte cipherText[] = cipher.doFinal ( clearText.getBytes () );

			// Codificamos el texto cifrado en base 64
			BASE64Encoder base64encoder = new BASE64Encoder ();
			cipherTextB64 = base64encoder.encode ( cipherText );

		}
		catch ( NoSuchAlgorithmException nsae )
		{
			nsae.printStackTrace ();
		}
		catch ( InvalidKeyException ike )
		{
			ike.printStackTrace ();
		}
		catch ( NoSuchPaddingException nspe )
		{
			nspe.printStackTrace ();
		}
		catch ( IllegalBlockSizeException ibse )
		{
			ibse.printStackTrace ();
		}
		catch ( BadPaddingException bpe )
		{
			bpe.printStackTrace ();
		}

		// Retornamos el texto cifrado en BASE64
		return cipherTextB64;
	}

	/**
	 * Desencripta un texto cifrado en DES i codificado en base 64
	 * 
	 * @param String
	 *            cipherTextB64 Testo cifrado en DES y codificado en B64
	 * @return String Texto en claro
	 */
	public String decrypt ( String cipherTextB64, Key key )
	{

		String clearText = "";

		try
		{

			// Necesitamos un cifrador
			Cipher cipher = Cipher.getInstance ( "DES" );

			// La clave est� codificada en base 64
			BASE64Decoder base64decoder = new BASE64Decoder ();
			byte cipherText[] = base64decoder.decodeBuffer ( cipherTextB64 );

			// Ciframos el texto en claro
			cipher.init ( Cipher.DECRYPT_MODE, key );
			byte bclearText[] = cipher.doFinal ( cipherText );
			clearText = new String ( bclearText );

		}
		catch ( NoSuchAlgorithmException nsae )
		{
			nsae.printStackTrace ();
		}
		catch ( NoSuchPaddingException nspe )
		{
			nspe.printStackTrace ();
		}
		catch ( InvalidKeyException ike )
		{
			ike.printStackTrace ();
		}
		catch ( IllegalBlockSizeException ibse )
		{
			ibse.printStackTrace ();
		}
		catch ( BadPaddingException bpe )
		{
			bpe.printStackTrace ();
		}
		catch ( IOException ioe )
		{
			ioe.printStackTrace ();
		}

		return clearText;
	}
}
