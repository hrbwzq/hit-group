package com.gaoshuhang.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author gaoshuhang
 */
public class URLEncodeUtil
{
	public static String encodeURL(String value)
	{
		try
		{
			return URLEncoder.encode(value, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			throw new RuntimeException("编码格式错误");
		}
	}
}
