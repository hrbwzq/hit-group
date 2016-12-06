package com.gaoshuhang.application;

import com.gaoshuhang.controller.CrawlerController;
import com.gaoshuhang.controller.FilterController;
import com.gaoshuhang.controller.GlobalController;
import com.gaoshuhang.controller.PostController;
import com.gaoshuhang.domain.News;
import com.gaoshuhang.globals.LogVars;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author gaoshuhang
 */
public class Main
{
	public static void main(String[] args)
	{
		//初始化日志系统
		Logger logger = Logger.getLogger("crawler");
		logger.setLevel(Level.INFO);
		LogVars.GLOBAL_LOGGER = logger;

		//初始化全局属性
		GlobalController globalController = new GlobalController();
		globalController.init();

		//抓取数据
		CrawlerController crawlerController = new CrawlerController();
		crawlerController.fetchData();

		//过滤数据
		List<News> newsList = new ArrayList<>();
		newsList.addAll(crawlerController.getHotNewsList());
		newsList.addAll(crawlerController.getHotPeopleList());
		newsList.addAll(crawlerController.getHotTopicList());

		FilterController filterController = new FilterController(newsList);
		filterController.doFilter();
		newsList = filterController.getNewsList();

		//发送数据
		PostController postController = new PostController(newsList);
		postController.doPost();
	}

}
