package com.gsh.test.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextUtil
{
	public static ApplicationContext getContext()
	{
		return new ClassPathXmlApplicationContext("applicationContext.xml");
	}
}
