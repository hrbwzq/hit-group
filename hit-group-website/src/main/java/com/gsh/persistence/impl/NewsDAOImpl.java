package com.gsh.persistence.impl;

import com.gsh.domain.News;
import com.gsh.domain.NewsCategory;
import com.gsh.persistence.NewsDAO;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "newsDAO")
public class NewsDAOImpl extends CommonDAO implements NewsDAO
{

	@Override
	public void insertNewsByCategoryName(News news, String newsCategoryName)
	{
		String hql = "from NewsCategory newsCategory where category=:category";
		Query query = this.getSession().createQuery(hql);
		query.setString("category", newsCategoryName);
		@SuppressWarnings("unchecked")
		List<NewsCategory> newsCategories = query.list();
		if(newsCategories.size() == 0)
		{
			throw new RuntimeException("指定新闻目录不存在");
		}
		else
		{
			NewsCategory newsCategory = newsCategories.get(0);
			newsCategory.getNewses().add(news);
			news.setNewsCategory(newsCategory);
			this.getSession().save(news);
		}
	}

	@Override
	public void insertNewsByCategoryId(News news, Long newsCategoryId)
	{
		String hql = "from NewsCategory newsCategory where newsCategoryId=:newsCategoryId";
		Query query = this.getSession().createQuery(hql);
		query.setLong("newsCategoryId", newsCategoryId);
		@SuppressWarnings("unchecked")
		List<NewsCategory> newsCategories = query.list();
		if(newsCategories.size() == 0)
		{
			throw new RuntimeException("指定新闻目录不存在");
		}
		else
		{
			NewsCategory newsCategory = newsCategories.get(0);
			newsCategory.getNewses().add(news);
			news.setNewsCategory(newsCategory);
			this.getSession().save(news);
		}
	}

	@Override
	public List<News> queryLimitNewsByCategoryName(String categoryName, int size)
	{
		String hql = "from News news where news.newsCategory.category=:categoryName order by createTime desc";
		Query query = this.getSession().createQuery(hql);
		query.setString("categoryName", categoryName);
		query.setFirstResult(0);
		query.setMaxResults(size);
		@SuppressWarnings("unchecked")
		List<News> newsList = query.list();
		return newsList;
	}

	@Override
	public List<News> queryNewsByPage(String categoryName, int startPage, int pageSize)
	{
		String hql = "from News news where news.newsCategory.category=:categoryName order by createTime desc";
		Query query = this.getSession().createQuery(hql);
		query.setString("categoryName", categoryName);
		query.setFirstResult((startPage - 1) * pageSize);
		query.setMaxResults(pageSize);
		@SuppressWarnings("unchecked")
		List<News> newsList = query.list();
		return newsList;
	}

	@Override
	public News queryNewsById(Long newsId)
	{
		return (News)this.getSession().get(News.class, newsId);
	}

	@Override
	public int queryNewsCountByCategoryName(String categoryName)
	{
		String hql = "select count(newsId) from News news where news.newsCategory.category=:categoryName";
		Query query = this.getSession().createQuery(hql);
		query.setString("categoryName", categoryName);
		Long result = (Long)query.uniqueResult();
		return result.intValue();
	}

	@Override
	public void addNewsCategory(String newsCategoryName)
	{
		NewsCategory newsCategory = new NewsCategory();
		newsCategory.setCategory(newsCategoryName);
		this.getSession().save(newsCategory);
	}

	@Override
	public List<NewsCategory> getAllNewsCategory()
	{
		String hql = "from NewsCategory t_news_category";
		@SuppressWarnings("unchecked")
		List<NewsCategory> newsCategories = this.getSession().createQuery(hql).list();
		return newsCategories;
	}

	@Override
	public NewsCategory getNewsCategoryByCategoryName(String newsCategoryName)
	{
		String hql = "from NewsCategory t_news_category where category=:category";
		Query query = this.getSession().createQuery(hql);
		query.setString("category", newsCategoryName);
		@SuppressWarnings("unchecked")
		List<NewsCategory> newsCategories = query.list();
		if(newsCategories.size() == 0)
		{
			return null;
		}
		else
		{
			return newsCategories.get(0);
		}
	}
}
