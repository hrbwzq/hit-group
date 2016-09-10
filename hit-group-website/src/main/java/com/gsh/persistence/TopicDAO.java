package com.gsh.persistence;

import com.gsh.domain.Topic;

import java.util.List;

public interface TopicDAO
{
	/**
	 * 添加主题帖
	 * @param topic 主题帖对象
	 */
	public void addTopic(Topic topic);
	
	/**
	 * 根据主题帖ID查询主题帖
	 * @param topicId 主题帖ID
	 * @return 主题帖对象
	 */
	public Topic queryTopicById(Long topicId);

	/**
	 * 分页查询主题帖
	 * @param startPage 起始页
	 * @param pageSize 分页大小
	 * @return 包含查询结果的列表,可能为空列表
	 */
	public List<Topic> queryTopicByPage(int startPage, int pageSize);

	/**
	 * 查询讨论区所有主题帖总条数
	 * @return 总条数
	 */
	public int queryTopicCount();
}
