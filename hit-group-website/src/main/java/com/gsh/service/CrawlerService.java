package com.gsh.service;

import com.gsh.domain.NewsCategory;
import com.gsh.web.crawler.beans.NewsCrawlerBean;

import java.util.List;

public interface CrawlerService
{
	/**
	 * 添加一条新闻
	 * @param newsCrawlerBean 爬虫模块得到的新闻内容数据模型
	 */
	public void addNews(NewsCrawlerBean newsCrawlerBean);

	/**
	 * 得到所有新闻分类
	 * @return 新闻分类对象
	 */
	public List<NewsCategory> getAllNewsCategory();
}
