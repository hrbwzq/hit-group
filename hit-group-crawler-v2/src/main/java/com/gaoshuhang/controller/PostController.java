package com.gaoshuhang.controller;

import com.gaoshuhang.domain.News;
import com.gaoshuhang.globals.LogVars;
import com.gaoshuhang.service.Poster;

import java.io.IOException;
import java.util.List;

/**
 * 数据发送控制器
 * @author gaoshuhang
 */
public class PostController
{
	List<News> newsList = null;

	public PostController(List<News> newsList)
	{
		this.newsList = newsList;
	}

	public void doPost()
	{
		LogVars.GLOBAL_LOGGER.info("开始发送数据");
		try
		{
			Poster poster = new Poster(this.newsList);
			poster.doPost();
		}
		catch (IOException e)
		{
			LogVars.GLOBAL_LOGGER.info("发送数据错误");
		}
		finally
		{
			LogVars.GLOBAL_LOGGER.info("发送数据结束");
		}
	}
}
