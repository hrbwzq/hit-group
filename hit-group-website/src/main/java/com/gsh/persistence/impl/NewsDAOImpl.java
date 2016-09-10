package com.gsh.persistence.impl;

import com.gsh.domain.News;
import com.gsh.domain.NewsCategory;
import com.gsh.persistence.NewsDAO;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class NewsDAOImpl extends CommonDAO implements NewsDAO
{

	@Override
	public void insertNewsByCategoryName(News news, String newsCategoryName)
	{
		String hql = "from NewsCategory t_news_category where category=:category";
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
		String hql = "from NewsCategory t_news_category where newsCategoryId=:newsCategoryId";
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
