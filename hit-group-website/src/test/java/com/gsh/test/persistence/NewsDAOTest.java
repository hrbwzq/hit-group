package com.gsh.test.persistence;

import com.gsh.domain.News;
import com.gsh.domain.NewsCategory;
import com.gsh.persistence.NewsDAO;
import com.gsh.test.util.SpringContextUtil;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/*
运行每一个测试用例前请先重建数据表
 */
public class NewsDAOTest
{

	@Test
	public void testInsertNews()
	{
		News news1 = new News();
		news1.setTitle("测试标题");
		news1.setContent("测试内容");
		news1.setCreateTime(new Date());

		News news2 = new News();
		news2.setTitle("测试标题");
		news2.setContent("测试内容");
		news2.setCreateTime(new Date());

		News news3 = new News();
		news3.setTitle("测试标题");
		news3.setContent("测试内容");
		news3.setCreateTime(new Date());

		NewsDAO newsDAO = (NewsDAO)SpringContextUtil.getContext().getBean("newsDAOImpl");
		newsDAO.addNewsCategory("测试分类1");
		newsDAO.addNewsCategory("测试分类2");
		newsDAO.insertNewsByCategoryName(news1, "测试分类1");
		newsDAO.insertNewsByCategoryName(news2, "测试分类1");
		newsDAO.insertNewsByCategoryId(news3, 2L);
	}

	@Test
	public void testQueryCategory()
	{
		NewsDAO newsDAO = (NewsDAO)SpringContextUtil.getContext().getBean("newsDAOImpl");
		newsDAO.addNewsCategory("测试分类1");
		newsDAO.addNewsCategory("测试分类2");
		List<NewsCategory> newsCategories = newsDAO.getAllNewsCategory();
		Assert.assertEquals(2, newsCategories.size());
		NewsCategory newsCategory = newsDAO.getNewsCategoryByCategoryName("测试分类2");
		Assert.assertEquals("测试分类2", newsCategory.getCategory());
	}

}
