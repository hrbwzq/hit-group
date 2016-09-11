package com.gsh.persistence;

import com.gsh.domain.TopicReply;

import java.util.List;

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

	/**
	 * 通过主题ID分页查询该主题所有回复帖
	 * @param topicId 主题帖ID
	 * @param startPage 分页起始页
	 * @param pageSize 分页大小
	 * @return 包含查询结果的列表,可呢为空列表
	 */
	public List<TopicReply> queryTopicReplyByPage(Long topicId, int startPage, int pageSize);

	/**
	 * 通过主题帖ID查询总主题帖回复记录条数
	 * @param topicId 主题帖ID
	 * @return 主题帖回复总条数
	 */
	public int queryTopicReplyCount(Long topicId);

	/**
	 * 通过主题帖ID获得当前最大楼数
	 * @param topicId 主题帖ID
	 * @return 当前最大楼数
	 */
	public int getCurrentMaxFloor(Long topicId);
}
