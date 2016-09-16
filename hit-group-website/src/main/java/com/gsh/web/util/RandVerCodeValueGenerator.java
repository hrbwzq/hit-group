package com.gsh.web.util;

import java.util.Random;

public class RandVerCodeValueGenerator
{
	public static String getRandString(int size)
	{
		char[] option = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < size; i++)
		{
			Random random = new Random();
			int randInt = random.nextInt(26);
			sb.append(option[randInt]);
		}
		return sb.toString();
	}
}
