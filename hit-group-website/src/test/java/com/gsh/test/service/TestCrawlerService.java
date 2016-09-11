package com.gsh.test.service;

import com.gsh.service.CrawlerService;
import com.gsh.test.util.SpringContextUtil;
import com.gsh.web.crawler.beans.NewsCrawlerBean;
import org.junit.Assert;
import org.junit.Test;

public class TestCrawlerService
{
	@Test
	public void testInsertNews()
	{
		//创建新的新闻填充模型
		NewsCrawlerBean newsCrawlerBean1 = new NewsCrawlerBean("测试新闻标题1","测试新闻内容1", "热点新闻");
		NewsCrawlerBean newsCrawlerBean2 = new NewsCrawlerBean("测试新闻标题2","测试新闻内容2", "热点新闻");
		NewsCrawlerBean newsCrawlerBean3 = new NewsCrawlerBean("测试新闻标题3","测试新闻内容3", "热点人物");

		//保存新闻
		CrawlerService crawlerService = (CrawlerService)SpringContextUtil.getContext().getBean("crawlerService");
		crawlerService.addNews(newsCrawlerBean1);
		crawlerService.addNews(newsCrawlerBean2);
		crawlerService.addNews(newsCrawlerBean3);
	}

	@Test
	public void testGetAllCategories()
	{
		CrawlerService crawlerService = (CrawlerService)SpringContextUtil.getContext().getBean("crawlerService");
		Assert.assertEquals(crawlerService.getAllNewsCategory().size(), 3);
	}

}
