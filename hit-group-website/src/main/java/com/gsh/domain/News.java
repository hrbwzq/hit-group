package com.gsh.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_news")
public class News implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "news_id")
	private Long newsId;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content", length = 65535, nullable = false)
	private String content;

	@Column(name = "create_time", nullable = false)
	private Date createTime;

	@ManyToOne
	private NewsCategory newsCategory;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "news")
	private Set<NewsReply> newsReplies = new HashSet<>();

	public News()
	{
	}

	public Long getNewsId()
	{
		return newsId;
	}

	public void setNewsId(Long newsId)
	{
		this.newsId = newsId;
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

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public NewsCategory getNewsCategory()
	{
		return newsCategory;
	}

	public void setNewsCategory(NewsCategory newsCategory)
	{
		this.newsCategory = newsCategory;
	}

	public Set<NewsReply> getNewsReplies()
	{
		return newsReplies;
	}

	public void setNewsReplies(Set<NewsReply> newsReplies)
	{
		this.newsReplies = newsReplies;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		News news = (News) o;

		if (newsId != null ? !newsId.equals(news.newsId) : news.newsId != null) return false;
		if (title != null ? !title.equals(news.title) : news.title != null) return false;
		if (content != null ? !content.equals(news.content) : news.content != null) return false;
		return !(createTime != null ? !createTime.equals(news.createTime) : news.createTime != null);

	}

	@Override
	public int hashCode()
	{
		int result = newsId != null ? newsId.hashCode() : 0;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (content != null ? content.hashCode() : 0);
		result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
		return result;
	}
}
