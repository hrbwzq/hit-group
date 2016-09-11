package com.gsh.service.impl;

import com.gsh.domain.Topic;
import com.gsh.domain.TopicReply;
import com.gsh.domain.User;
import com.gsh.service.TopicReplyService;
import com.gsh.service.util.PageUtil;
import com.gsh.web.forum.beans.TopicPageBean;
import com.gsh.web.forum.beans.TopicReplyFormBean;
import com.gsh.web.forum.beans.TopicReplyPageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service(value = "topicReplyService")
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

		//查询当前主题帖最大楼数
		int maxFloor = getTopicReplyDAO().getCurrentMaxFloor(topicId);

		//封装新的主题回复帖对象
		TopicReply topicReply = new TopicReply();
		topicReply.setDeleted(0);
		topicReply.setUser(user);
		topicReply.setContent(topicReplyFormBean.getContent());
		topicReply.setFloor(maxFloor + 1);
		topicReply.setParentTopicReply(parentTopicReply);
		topicReply.setTopic(topic);

		//刷新主题帖的最后修改时间
		topic.setLastModifiedTime(new Date());

		//保存新的主题回复帖对象
		getTopicReplyDAO().addTopicReply(topicReply);
	}

	@Override
	public TopicReplyPageBean getTopicReplyByTopicIdByPage(Long topicId, int startPage, int pageSize)
	{
		//得到分页总数
		int queryCount = getTopicReplyDAO().queryTopicReplyCount(topicId);
		int pageCount = PageUtil.getPageCount(queryCount, pageSize);

		//分页查询
		List<TopicReply> topicReplyList = this.getTopicReplyDAO().queryTopicReplyByPage(topicId, startPage, pageSize);
		TopicReplyPageBean topicReplyPageBean = new TopicReplyPageBean();
		topicReplyPageBean.getTopicReplyList().addAll(topicReplyList);
		topicReplyPageBean.setTotalPage(pageCount);
		topicReplyPageBean.setCurrentPage(startPage);

		return topicReplyPageBean;
	}

	@Override
	public void deleteTopicReplyById(Long topicReplyId)
	{
		TopicReply topicReply = getTopicReplyDAO().queryTopicReplyById(topicReplyId);
		topicReply.setDeleted(1);
	}

}
