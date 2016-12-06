package com.gaoshuhang.utils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * hash计算
 * @author gaoshuhang
 */
public class HashUtil
{
	public static String strHash(String value, String method)//hash
	{
		try
		{
			MessageDigest messageDiges = MessageDigest.getInstance(method);
			messageDiges.update(value.getBytes());
			byte[] buffer = messageDiges.digest();
			return DatatypeConverter.printHexBinary(buffer);
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new RuntimeException("Internal Error: No such algorithm");
		}
	}
}
