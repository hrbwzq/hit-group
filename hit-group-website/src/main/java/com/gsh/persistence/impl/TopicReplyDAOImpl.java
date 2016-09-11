package com.gsh.persistence.impl;

import com.gsh.domain.TopicReply;
import com.gsh.persistence.TopicReplyDAO;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "topicReplyDAO")
public class TopicReplyDAOImpl extends CommonDAO implements TopicReplyDAO
{
	@Override
	public void addTopicReply(TopicReply topicReply)
	{
		this.getSession().save(topicReply);
	}

	@Override
	public TopicReply queryTopicReplyById(Long topicReplyId)
	{
		return (TopicReply)this.getSession().get(TopicReply.class, topicReplyId);
	}

	@Override
	public List<TopicReply> queryTopicReplyByPage(Long topicId, int startPage, int pageSize)
	{
		String hql = "from TopicReply t where deleted!=1 order by floor asc";
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult((startPage - 1) * pageSize);
		query.setMaxResults(pageSize);
		@SuppressWarnings("unchecked")
		List<TopicReply> topicReplyList = query.list();
		return topicReplyList;
	}

	@Override
	public int queryTopicReplyCount(Long topicId)
	{
		String hql = "select count(topicReplyId) from TopicReply t where deleted!=1 and t.topic.topicId=:topicId";
		Query query = this.getSession().createQuery(hql);
		query.setLong("topicId", topicId);
		Long result = (Long)query.uniqueResult();
		return result.intValue();
	}

	@Override
	public int getCurrentMaxFloor(Long topicId)
	{
		String hql = "select max(floor) from TopicReply t where t.topic.topicId=:topicId";
		Query query = this.getSession().createQuery(hql);
		query.setLong("topicId", topicId);
		Long result = (Long)query.uniqueResult();
		if(result == null)
		{
			return 0;
		}
		else
		{
			return result.intValue();
		}
	}
}
