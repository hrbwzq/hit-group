package com.gsh.web.crawler.beans;

public class NewsCrawlerBean
{
	private String title;
	private String content;
	private String categoryName;

	public NewsCrawlerBean()
	{
	}

	public NewsCrawlerBean(String title, String content, String categoryName)
	{
		this.title = title;
		this.content = content;
		this.categoryName = categoryName;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getCategoryName()
	{
		return categoryName;
	}

	public void setCategoryName(String categoryName)
	{
		this.categoryName = categoryName;
	}
}
