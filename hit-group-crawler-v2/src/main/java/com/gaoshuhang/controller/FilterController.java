package com.gaoshuhang.controller;

import com.gaoshuhang.dao.FetchLogDAO;
import com.gaoshuhang.domain.News;
import com.gaoshuhang.globals.LogVars;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据过滤控制器
 * @author gaoshuhang
 */
public class FilterController
{
	private List<News> newsList = null;
	private FetchLogDAO fetchLogDAO = new FetchLogDAO();

	public FilterController(List<News> newsList)
	{
		this.newsList = newsList;
	}

	/**
	 * 根据数据库过滤哪些新闻应该发送
	 */
	public void doFilter()
	{
		LogVars.GLOBAL_LOGGER.info("开始数据库操作");
		List<News> filteredList = new ArrayList<>();

		for(News news : this.newsList)
		{
			if(!this.fetchLogDAO.isNewsExists(news))
			{
				this.fetchLogDAO.saveNewsLog(news);
				filteredList.add(news);
			}
		}

		this.newsList = filteredList;
		LogVars.GLOBAL_LOGGER.info("结束数据库操作");
	}

	/**
	 * 获得新闻列表,应该在过滤后调用
	 * @return 新闻列表
	 */
	public List<News> getNewsList()
	{
		return this.newsList;
	}
}
