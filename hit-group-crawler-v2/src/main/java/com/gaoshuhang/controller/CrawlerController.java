package com.gaoshuhang.controller;

import com.gaoshuhang.domain.News;
import com.gaoshuhang.globals.LogVars;
import com.gaoshuhang.globals.URLVars;
import com.gaoshuhang.service.Crawler;

import java.io.IOException;
import java.util.List;

/**
 * 爬取执行控制器
 * @author gaoshuhang
 */
public class CrawlerController
{

	private List<News> hotNewsList = null;
	private List<News> hotTopicList = null;
	private List<News> hotPeopleList = null;

	/**
	 * 获得数据
	 */
	public void fetchData()
	{
		//初始化爬虫
		Crawler hotNewsCrawler = new Crawler(URLVars.HOT_NEWS_URL, "热点新闻");
		Crawler hotTopicCrawler = new Crawler(URLVars.HOT_TOPIC_URL, "热点话题");
		Crawler hotPeopleCrawler = new Crawler(URLVars.HOT_PEOPLE_URL, "热点人物");

		//执行抓取任务
		try
		{
			LogVars.GLOBAL_LOGGER.info("开始热点新闻抓取任务");
			hotNewsCrawler.runFetch();
			LogVars.GLOBAL_LOGGER.info("开始热点话题抓取任务");
			hotTopicCrawler.runFetch();
			LogVars.GLOBAL_LOGGER.info("开始热点人物抓取任务");
			hotPeopleCrawler.runFetch();
		}
		catch (IOException e)
		{
			LogVars.GLOBAL_LOGGER.info("抓取失败,IO错误");
		}
		finally
		{
			LogVars.GLOBAL_LOGGER.info("抓取任务结束");
		}

		//获得数据
		this.hotNewsList = hotNewsCrawler.getNewsList();
		this.hotTopicList = hotTopicCrawler.getNewsList();
		this.hotPeopleList = hotPeopleCrawler.getNewsList();
	}

	public List<News> getHotNewsList()
	{
		return hotNewsList;
	}

	public List<News> getHotTopicList()
	{
		return hotTopicList;
	}

	public List<News> getHotPeopleList()
	{
		return hotPeopleList;
	}
}
