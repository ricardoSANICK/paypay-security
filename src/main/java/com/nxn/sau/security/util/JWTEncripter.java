package com.nxn.sau.security.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.text.ParseException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;

public class JWTEncripter
{
	private final Properties props = new Properties();
	private final String file = "/security-policies.properties";
	private final String prop_jwt_password_encripted;

	private final String characterEncoding       = "UTF-8";
	private final String aesEncryptionAlgorithem = "AES";

	static Logger log = LoggerFactory.getLogger(JWTEncripter.class);

	private Gson gson;

	public JWTEncripter()
	{
		prop_jwt_password_encripted = props.getProperties(file).getProperty("jwt.password.encripted");
	}

	public JWTEncripter(Gson gson)
	{
		this.gson = gson;
		prop_jwt_password_encripted = props.getProperties(file).getProperty("jwt.password.encripted");
	}


	private SecretKey getPassFromProperties() throws UnsupportedEncodingException
	{
		DESEncryption encryptor = new DESEncryption();
		String encryptionKey    = encryptor.decrypt(prop_jwt_password_encripted, encryptor.getKey());
		byte[] key              = encryptionKey.getBytes(characterEncoding);
		SecretKey secretKey     = new SecretKeySpec(key, aesEncryptionAlgorithem);
		return secretKey;
	}

	public String encriptJsonJWT(String jsonString) throws UnsupportedEncodingException, KeyLengthException, JOSEException
	{
		// Generate key
		SecretKey key = getPassFromProperties();
		// Set the plain text
		Payload payload = new Payload(jsonString);
		// Create the header
		JWEHeader header = new JWEHeader(JWEAlgorithm.DIR, EncryptionMethod.A128GCM);
		// Create the JWE object and encrypt it
		JWEObject jweObject = new JWEObject(header, payload);
		jweObject.encrypt(new DirectEncrypter(key));
		// Serialise to compact JOSE form...
		String jweString = jweObject.serialize();

		return jweString;
	}

	public String decriptJsonJWT(String jweString) throws UnsupportedEncodingException, ParseException, JOSEException
	{
		// Generate key
		SecretKey key = getPassFromProperties();
		// Parse into JWE object again...
		JWEObject jweObject = JWEObject.parse(jweString);
		// Decrypt
		jweObject.decrypt(new DirectDecrypter(key));
		// Get the plain text
		Payload payload = jweObject.getPayload();

		return payload.toString();
	}


	public String toJWT(Object obj, Gson gson)
	{
		String jwt = "", json = "";
		try
		{
			json = gson.toJson(obj);
			jwt  = encriptJsonJWT(json); // Encriptacion por medio de JWT con clave compartida
		}
		catch (UnsupportedEncodingException | JOSEException e1)
		{
			if( e1 instanceof  UnsupportedEncodingException)
				log.error("UnsupportedEncodingException error",e1);
			if( e1 instanceof JOSEException)
				log.error("JOSEException error",e1);
		}

		return jwt;
	}


	public String toJWT(Object obj)
	{
		return this.toJWT(obj, this.gson);
	}

	public <T> T fromJWT(String jwt, Gson gson, Class<T> classOfT)
	{
		return this.fromJWT_(jwt, gson, null, classOfT);
	}

	public <T> T fromJWT(String jwt, Class<T> classOfT)
	{
		return this.fromJWT(jwt, gson, classOfT);
	}

	public <T> T fromJWT(String jwt, Gson gson, Type classOfT)
	{
		return this.fromJWT_(jwt, gson, classOfT, null);
	}

	public <T> T fromJWT(String jwt, Type classOfT)
	{
		return this.fromJWT(jwt, gson, classOfT);
	}


	private void logException(Exception e1){
		if( e1 instanceof  UnsupportedEncodingException)
			log.error("UnsupportedEncodingException error",e1);
		if( e1 instanceof JOSEException)
			log.error("JOSEException error",e1);
		if( e1 instanceof ParseException)
			log.error("ParseException error",e1);
	}

	private <T> T fromJWT_(String jwt, Gson gson, Type classOfT, Class<T> classOfTT){
		String json;
		T      dto  = null;
		try
		{
			json  = decriptJsonJWT(jwt); // Encriptacion por medio de JWT con clave compartida
			dto   = gson.fromJson(json, classOfT != null ? classOfT : classOfTT);
		}
		catch (UnsupportedEncodingException | JOSEException | ParseException e1)
		{
			this.logException(e1);
		}
		return dto;
	}

}

