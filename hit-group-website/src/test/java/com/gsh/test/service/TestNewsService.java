package com.gsh.test.service;

import com.gsh.domain.News;
import com.gsh.service.NewsService;
import com.gsh.test.util.SpringContextUtil;
import com.gsh.web.news.beans.NewsPageBean;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestNewsService
{
	private void insertTestNews()
	{
		TestCrawlerService testCrawlerService = new TestCrawlerService();
		testCrawlerService.testInsertNews();
	}

	@Test
	public void testGetNewsByCategory()
	{
		this.insertTestNews();
		NewsService newsService = (NewsService)SpringContextUtil.getContext().getBean("newsService");
		List<News> newses1 = newsService.getLimitNewsByCategoryName("热点新闻", 5);
		Assert.assertEquals(2, newses1.size());
		NewsPageBean newsPageBean = newsService.getNewsByPage("热点人物", 1, 5);
		Assert.assertEquals(1, newsPageBean.getNewses().size());
	}
}
