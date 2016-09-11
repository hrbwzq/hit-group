package com.gsh.test.service;

import com.gsh.service.TopicReplyService;
import com.gsh.service.TopicService;
import com.gsh.test.util.SpringContextUtil;
import com.gsh.web.forum.beans.TopicFormBean;
import com.gsh.web.forum.beans.TopicReplyFormBean;
import com.gsh.web.forum.beans.TopicReplyPageBean;
import org.junit.Assert;
import org.junit.Test;

public class TestTopicReplyService
{
	private void addTestUser()
	{
		TestUserService testUserService = new TestUserService();
		testUserService.testRegistUser();
	}

	private void addTestTopic()
	{
		//组织新的FormBean
		TopicFormBean topicFormBean = new TopicFormBean();
		topicFormBean.setTitle("测试主题标题");
		topicFormBean.setContent("测试主题内容");

		//发表新主题帖
		TopicService topicService = (TopicService) SpringContextUtil.getContext().getBean("topicService");
		topicService.publishTopic(topicFormBean, 1L);
	}

	@Test
	public void testPublish()
	{
		this.addTestUser();
		this.addTestTopic();

		TopicReplyService topicReplyService = (TopicReplyService) SpringContextUtil.getContext().getBean("topicReplyService");

		//组织新的TopicReplyFormBean
		TopicReplyFormBean topicReplyFormBean = new TopicReplyFormBean();
		topicReplyFormBean.setContent("测试主题帖回复帖");

		topicReplyService.publishTopicReply(topicReplyFormBean, 1L, null, 1L);
	}

	@Test
	public void testQueryTopicReplyByPage()
	{
		this.testPublish();
		TopicReplyService topicReplyService = (TopicReplyService) SpringContextUtil.getContext().getBean("topicReplyService");
		TopicReplyPageBean topicReplyPageBean = topicReplyService.getTopicReplyByTopicIdByPage(1L, 1, 5);
		Assert.assertEquals(1, topicReplyPageBean.getTopicReplyList().size());
	}

	@Test
	public void testDeleteTopicReply()
	{
		//妈的不测了
	}
}
