package com.gsh.persistence.impl;

import com.gsh.domain.Topic;
import com.gsh.persistence.TopicDAO;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "topicDAO")
public class TopicDAOImpl extends CommonDAO implements TopicDAO
{
	@Override
	public void addTopic(Topic topic)
	{
		this.getSession().save(topic);
	}

	@Override
	public Topic queryTopicById(Long topicId)
	{
		return (Topic)this.getSession().get(Topic.class, topicId);
	}

	@Override
	public List<Topic> queryTopicByPage(int startPage, int pageSize)
	{
		String hql = "from Topic topic where deleted!=1 order by lastModifiedTime desc";
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult((startPage - 1) * pageSize);
		query.setMaxResults(pageSize);
		@SuppressWarnings("unchecked")
		List<Topic> topicList = query.list();
		return topicList;
	}

	@Override
	public int queryTopicCount()
	{
		String hql = "select count(topicId) from Topic where deleted!=1";
		Query query = this.getSession().createQuery(hql);
		Long result = (Long)query.uniqueResult();
		return result.intValue();
	}

	@Override
	public List<Topic> queryTopicByPageAndGroupName(int startPage, int pageSize, String groupName)
	{
		String hql = "from Topic topic where deleted!=1 and topic.group.name=:groupName order by lastModifiedTime desc";
		Query query = this.getSession().createQuery(hql);
		query.setString("groupName", groupName);
		query.setFirstResult((startPage - 1) * pageSize);
		query.setMaxResults(pageSize);
		@SuppressWarnings("unchecked")
		List<Topic> topicList = query.list();
		return topicList;
	}

	@Override
	public List<Topic> queryTopicByPageAndGroupId(int startPage, int pageSize, Long groupId)
	{
		String hql = "from Topic topic where deleted!=1 and topic.group.groupId=:groupId order by lastModifiedTime desc";
		Query query = this.getSession().createQuery(hql);
		query.setLong("groupId", groupId);
		query.setFirstResult((startPage - 1) * pageSize);
		query.setMaxResults(pageSize);
		@SuppressWarnings("unchecked")
		List<Topic> topicList = query.list();
		return topicList;
	}
}
