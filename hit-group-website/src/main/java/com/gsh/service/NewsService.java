package com.gsh.service;

import com.gsh.domain.News;
import com.gsh.domain.NewsCategory;
import com.gsh.web.news.beans.NewsPageBean;

import java.util.List;

public interface NewsService
{
	/**
	 * 查询所有新闻分类
	 * @return 包含新闻分类的列表，可能为空列表
	 */
	public List<NewsCategory> getAllCategories();

	/**
	 * 根据时间排序,根据分类查询指定条数新闻
	 * @param categoryName 分类类名
	 * @param size 试图查询条数
	 * @return 包含新闻条目的列表，可能为空列表
	 */
	public List<News> getLimitNewsByCategoryName(String categoryName, int size);

	/**
	 * 根据时间排序,根据分类分页查询所有新闻
	 * @param categoryName 分类类名
	 * @param startPage 起始页码
	 * @param pageSize 分页大小
	 * @return 包含查询信息的PageBean
	 */
	public NewsPageBean getNewsByPage(String categoryName, int startPage, int pageSize);
}
