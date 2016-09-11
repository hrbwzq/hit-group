package com.gsh.service.impl;

import com.gsh.domain.Topic;
import com.gsh.domain.User;
import com.gsh.service.TopicService;
import com.gsh.service.util.PageUtil;
import com.gsh.web.forum.beans.TopicFormBean;
import com.gsh.web.forum.beans.TopicPageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service(value = "topicService")
@Transactional
public class TopicServiceImpl extends CommonService implements TopicService
{
	@Override
	public TopicPageBean getLimitTopic(int startPage, int pageSize)
	{
		//查询分页总页数
		int queryCount = getTopicDAO().queryTopicCount();
		int pageCount = PageUtil.getPageCount(queryCount, pageSize);

		//分页查询封装到PageBean
		List<Topic> topics = getTopicDAO().queryTopicByPage(startPage, pageSize);
		TopicPageBean topicPageBean = new TopicPageBean();
		topicPageBean.getTopics().addAll(topics);
		topicPageBean.setTotalPage(pageCount);
		topicPageBean.setCurrentPage(startPage);
		return topicPageBean;
	}

	@Override
	public void publishTopic(TopicFormBean topicFormBean, Long userId)
	{
		//根据用户ID查询用户
		User user = getUserDAO().getUserById(userId);

		//封装新的主题帖对象
		Topic topic = new Topic();
		topic.setTitle(topicFormBean.getTitle());
		topic.setContent(topicFormBean.getContent());
		topic.setUser(user);
		topic.setCreateTime(new Date());
		topic.setLastModifiedTime(new Date());
		topic.setDeleted(0);

		//保存新的主题帖对象
		getTopicDAO().addTopic(topic);
	}

	@Override
	public void deleteTopicById(Long topicId)
	{
		Topic topic = getTopicDAO().queryTopicById(topicId);
		topic.setDeleted(1);
	}

}
