package com.gsh.persistence.impl;

import com.gsh.domain.NewsReply;
import com.gsh.persistence.NewsReplyDAO;
import org.springframework.stereotype.Repository;

@Repository(value = "newsReplyDAO")
public class NewsReplyDAOImpl extends CommonDAO implements NewsReplyDAO
{

	@Override
	public void addNewsReply(NewsReply newsReply)
	{
		this.getSession().save(newsReply);
	}
}
