package com.gsh.service;

import com.gsh.web.forum.beans.TopicReplyFormBean;
import com.gsh.web.forum.beans.TopicReplyPageBean;

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

	/**
	 * 通过主题帖ID分页查询该主题帖回复
	 * @param topicId 主题帖ID
	 * @param startPage 起始页
	 * @param pageSize 结束页
	 * @return 分页对象
	 */
	public TopicReplyPageBean getTopicReplyByTopicIdByPage(Long topicId, int startPage, int pageSize);

	/**
	 * 根据ID将指定主题回复帖标记为已删除
	 * @param topicReplyId 主题回复帖ID
	 */
	public void deleteTopicReplyById(Long topicReplyId);

}
