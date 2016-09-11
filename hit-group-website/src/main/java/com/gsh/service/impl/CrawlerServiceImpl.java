package com.gsh.service.impl;

import com.gsh.domain.News;
import com.gsh.domain.NewsCategory;
import com.gsh.service.CrawlerService;
import com.gsh.web.crawler.beans.NewsCrawlerBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service(value = "crawlerService")
@Transactional
public class CrawlerServiceImpl extends CommonService implements CrawlerService
{
	@Override
	public void addNews(NewsCrawlerBean newsCrawlerBean)
	{
		News news = new News();
		news.setTitle(newsCrawlerBean.getTitle());
		news.setContent(newsCrawlerBean.getContent());
		news.setCreateTime(new Date());
		this.getNewsDAO().insertNewsByCategoryName(news, newsCrawlerBean.getCategoryName());
	}

	@Override
	public List<NewsCategory> getAllNewsCategory()
	{
		return this.getNewsDAO().getAllNewsCategory();
	}
}
