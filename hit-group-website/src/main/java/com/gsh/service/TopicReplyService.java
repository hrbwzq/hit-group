package com.gsh.service;

import com.gsh.web.forum.beans.TopicReplyFormBean;

public interface TopicReplyService
{
	/**
	 * 发布主题帖的回复帖
	 * @param topicReplyFormBean 包含回复帖信息的FormBean
	 * @param topicId 主题帖ID
	 * @param parentReplyId 父级主题回复帖ID,可以为null
	 * @param userId 用户ID
	 */
	public void publishTopicReply(TopicReplyFormBean topicReplyFormBean, Long topicId, Long parentReplyId, Long userId);

	public void deleteTopicReplyById(Long topicReplyId);


}
