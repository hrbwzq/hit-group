package com.gaoshuhang.controller;

import com.gaoshuhang.globals.POSTVars;
import com.gaoshuhang.globals.URLVars;

import java.io.IOException;
import java.util.Properties;

/**
 * 全局初始化控制器
 */
public class GlobalController
{

	Properties crawlerProperties = null;
	Properties postProperties = null;

	public GlobalController()
	{
		this.getConfig();
	}

	/**
	 * 读取配置文件,出错立即终止程序
	 */
	private void getConfig()
	{
		Properties crawlerProperties = new Properties();
		Properties postProperties = new Properties();
		try
		{
			crawlerProperties.load(GlobalController.class.getClassLoader().getResourceAsStream("baseUrl.properties"));
			postProperties.load(GlobalController.class.getClassLoader().getResourceAsStream("post.properties"));
		}
		catch (IOException e)//处理异常
		{
			throw new RuntimeException("读取URL配置文件出错");
		}
		this.crawlerProperties = crawlerProperties;
		this.postProperties = postProperties;
	}

	/**
	 * 初始化程序全局变量
	 */
	public void init()
	{
		URLVars.HOT_NEWS_URL = crawlerProperties.getProperty("hotNewsURL");
		URLVars.HOT_PEOPLE_URL = crawlerProperties.getProperty("hotPeopleURL");
		URLVars.HOT_TOPIC_URL = crawlerProperties.getProperty("hotTopicURL");

		POSTVars.POST_URL = postProperties.getProperty("POSTURL");
		POSTVars.AUID = postProperties.getProperty("auid");
		POSTVars.PWD = postProperties.getProperty("pwd");
	}
}
