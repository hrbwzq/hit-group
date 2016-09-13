package com.gsh.persistence.impl;

import com.gsh.domain.NewsReply;
import com.gsh.persistence.NewsReplyDAO;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "newsReplyDAO")
public class NewsReplyDAOImpl extends CommonDAO implements NewsReplyDAO
{

	@Override
	public void addNewsReply(NewsReply newsReply)
	{
		this.getSession().save(newsReply);
	}

	@Override
	public List<NewsReply> getNewsReplyByNewsId(Long newsId)
	{
		String hql = "from NewsReply newsReply where newsReply.news.newsId=:newsId order by newsReplyId asc";
		Query query = this.getSession().createQuery(hql);
		query.setLong("newsId", newsId);
		@SuppressWarnings("unchecked")
		List<NewsReply> newsReplyList = query.list();
		return newsReplyList;
	}
}
