package com.gsh.web.news.beans;

import com.gsh.domain.News;
import com.gsh.web.util.PageBeanSupport;

import java.util.ArrayList;
import java.util.List;

public class NewsPageBean extends PageBeanSupport
{
	private List<News> newses = new ArrayList<>();

	public List<News> getNewses()
	{
		return newses;
	}

}
