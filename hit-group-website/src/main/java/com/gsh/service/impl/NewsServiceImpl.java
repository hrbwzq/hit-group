package com.gsh.service.impl;

import com.gsh.domain.News;
import com.gsh.domain.NewsCategory;
import com.gsh.service.NewsService;
import com.gsh.service.util.PageUtil;
import com.gsh.web.news.beans.NewsPageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "newsService")
@Transactional
public class NewsServiceImpl extends CommonService implements NewsService
{

	@Override
	public List<NewsCategory> getAllCategories()
	{
		return getNewsDAO().getAllNewsCategory();
	}

	@Override
	public List<News> getLimitNewsByCategoryName(String categoryName, int size)
	{
		return getNewsDAO().queryLimitNewsByCategoryName(categoryName, size);
	}

	@Override
	public NewsPageBean getNewsByPage(String categoryName, int startPage, int pageSize)
	{
		//查询分页总页数
		int queryCount = getNewsDAO().queryNewsCountByCategoryName(categoryName);
		int pageCount = PageUtil.getPageCount(queryCount, pageSize);

		//根据查询结果封装PageBean
		List<News> newses = getNewsDAO().queryNewsByPage(categoryName, startPage, pageSize);
		NewsPageBean newsPageBean = new NewsPageBean();
		newsPageBean.setCurrentPage(startPage);
		newsPageBean.getNewses().addAll(newses);
		newsPageBean.setTotalPage(pageCount);
		return newsPageBean;
	}
}
