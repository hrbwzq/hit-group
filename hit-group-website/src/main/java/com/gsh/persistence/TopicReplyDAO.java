package com.gsh.persistence;

import com.gsh.domain.TopicReply;

public interface TopicReplyDAO
{
	/**
	 * 保存一条主题帖回复
	 * @param topicReply 主题帖回复对象
	 */
	public void addTopicReply(TopicReply topicReply);

	/**
	 * 根据主题回复帖ID查询主题回复帖对象
	 * @param topicReplyId 主题回复帖ID
	 * @return 主题回复帖对象,如果查询结果为空,返回null
	 */
	public TopicReply queryTopicReplyById(Long topicReplyId);
}
