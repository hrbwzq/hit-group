package com.gsh.service;

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
	 * 发表新的主题帖
	 * @param topicFormBean 包含将要发表信息的FormBean
	 * @param userId 用户ID
	 */
	public void publishTopic(TopicFormBean topicFormBean, Long userId);

	/**
	 * 删除主题帖
	 * @param topicId 主题帖ID
	 */
	public void deleteTopicById(Long topicId);


}
