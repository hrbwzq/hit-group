package com.gsh.service.impl;

import com.gsh.domain.Topic;
import com.gsh.domain.TopicReply;
import com.gsh.domain.User;
import com.gsh.service.TopicReplyService;
import com.gsh.web.forum.beans.TopicReplyFormBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class TopicReplyServiceImpl extends CommonService implements TopicReplyService
{

	@Override
	public void publishTopicReply(TopicReplyFormBean topicReplyFormBean, Long topicId, Long parentReplyId, Long userId)
	{
		//根据用户ID查询用户
		User user = getUserDAO().getUserById(userId);

		//根据主题帖ID查询主题帖对象
		Topic topic = getTopicDAO().queryTopicById(topicId);

		//根据父级主题回复帖ID查询主题回复帖对象
		TopicReply parentTopicReply = null;
		if(parentReplyId != null)
		{
			parentTopicReply = getTopicReplyDAO().queryTopicReplyById(parentReplyId);
		}

		//封装新的主题回复帖对象
		TopicReply topicReply = new TopicReply();
		topicReply.setDeleted(0);
		topicReply.setUser(user);
		topicReply.setContent(topicReplyFormBean.getContent());
		topicReply.setFloor(topicReplyFormBean.getFloor());
		topicReply.setParentTopicReply(parentTopicReply);
		topicReply.setTopic(topic);

		//刷新主题帖的最后修改时间
		topic.setLastModifiedTime(new Date());

		//保存新的主题回复帖对象
		getTopicReplyDAO().addTopicReply(topicReply);
	}

	@Override
	public void deleteTopicReplyById(Long topicReplyId)
	{
		TopicReply topicReply = getTopicReplyDAO().queryTopicReplyById(topicReplyId);
		topicReply.setDeleted(1);
	}

	@Override
	public void deleteTopicReply(TopicReply topicReply)
	{
		topicReply.setDeleted(1);
	}
}
