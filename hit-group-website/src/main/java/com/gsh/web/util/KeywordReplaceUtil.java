package com.gsh.web.util;

public class KeywordReplaceUtil
{
	public static String HTMLTageFilter(String originalStr)
	{
		//HTML转义字符表
		String result = originalStr.replaceAll("&", "&amp;");
		result = result.replaceAll("\"", "&quot;");
		result = result.replaceAll("<", "&lt;");
		result = result.replaceAll(">", "&gt;");
		result = result.replaceAll(" ", "&nbsp;");
		result = result.replaceAll("\n", "<br />");

		return result;
	}
}
