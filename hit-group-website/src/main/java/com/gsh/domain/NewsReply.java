package com.gsh.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_news_reply")
public class NewsReply implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "news_reply_id")
	private Long newsReplyId;

	@Column(name = "content", length = 65535, nullable = false)
	private String content;

	@ManyToOne
	private News news;

	@ManyToOne
	private User user;

	public NewsReply()
	{
	}

	public Long getNewsReplyId()
	{
		return newsReplyId;
	}

	public void setNewsReplyId(Long newsReplyId)
	{
		this.newsReplyId = newsReplyId;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public News getNews()
	{
		return news;
	}

	public void setNews(News news)
	{
		this.news = news;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		NewsReply newsReply = (NewsReply) o;

		if (newsReplyId != null ? !newsReplyId.equals(newsReply.newsReplyId) : newsReply.newsReplyId != null)
			return false;
		return !(content != null ? !content.equals(newsReply.content) : newsReply.content != null);

	}

	@Override
	public int hashCode()
	{
		int result = newsReplyId != null ? newsReplyId.hashCode() : 0;
		result = 31 * result + (content != null ? content.hashCode() : 0);
		return result;
	}
}
