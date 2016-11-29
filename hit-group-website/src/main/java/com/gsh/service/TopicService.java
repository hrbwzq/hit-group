package com.gsh.service;

import com.gsh.domain.Topic;
import com.gsh.web.forum.beans.TopicFormBean;
import com.gsh.web.forum.beans.TopicPageBean;

public interface TopicService
{
	/**
	 * 分页获得讨论区首页主题帖
	 * @param startPage 起始页
	 * @param pageSize 分页大小
	 * @return 包含查询结果的PageBean
	 */
	public TopicPageBean getLimitTopic(int startPage, int pageSize);

	/**
	 * 根据圈子ID查询,分页获得讨论区首页主题帖
	 * @param startPage 起始页
	 * @param pageSize 分页大小
	 * @param groupId 圈子ID
	 * @return 包含查询结果的PageBean
	 */
	public TopicPageBean getLimitTopicByGroupId(int startPage, int pageSize, Long groupId);

	/**
	 * 第二次迭代已弃用,该方法存入的数据不会显示
	 * 发表新的主题帖
	 * @param topicFormBean 包含将要发表信息的FormBean
	 * @param userId 用户ID
	 */
	@Deprecated
	public void publishTopic(TopicFormBean topicFormBean, Long userId);

	/**
	 * 发表新的主题帖,包括圈子信息
	 * @param topicFormBean 包含将要发表信息的FormBean
	 * @param userId 用户ID
	 * @param groupId 圈子ID
	 */
	public void publishTopic(TopicFormBean topicFormBean, Long userId, Long groupId);

	/**
	 * 通过ID删除主题帖
	 * @param topicId 主题帖ID
	 */
	public void deleteTopicById(Long topicId);

	/**
	 * 根据Topic ID查询Topic对象
	 * @param topicId 主题帖ID
	 * @return 查询结果,可能为null
	 */
	public Topic queryTopicById(Long topicId);

}
