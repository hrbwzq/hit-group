package com.gaoshuhang.domain;

import java.io.Serializable;

/**
 * 新闻领域对象
 * @author gaoshuhang
 */
public class News implements Serializable
{
	private String title;
	private String content;
	private String category;

	public News(String title, String content, String category)
	{
		this.title = title;
		this.content = content;
		this.category = category;
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

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		News news = (News) o;

		if (title != null ? !title.equals(news.title) : news.title != null) return false;
		if (content != null ? !content.equals(news.content) : news.content != null) return false;
		return !(category != null ? !category.equals(news.category) : news.category != null);

	}

	@Override
	public int hashCode()
	{
		int result = title != null ? title.hashCode() : 0;
		result = 31 * result + (content != null ? content.hashCode() : 0);
		result = 31 * result + (category != null ? category.hashCode() : 0);
		return result;
	}
}
