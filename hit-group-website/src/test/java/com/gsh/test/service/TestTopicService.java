package com.gsh.test.service;

import com.gsh.service.TopicService;
import com.gsh.test.util.SpringContextUtil;
import com.gsh.web.forum.beans.TopicFormBean;
import com.gsh.web.forum.beans.TopicPageBean;
import org.junit.Assert;
import org.junit.Test;

public class TestTopicService
{
	@Test
	public void testPublicAndGetTopic()
	{
		//获取测试用户
		TestUserService testUserService = new TestUserService();
		testUserService.testRegistUser();

		//组织新的FormBean
		TopicFormBean topicFormBean = new TopicFormBean();
		topicFormBean.setTitle("测试主题标题");
		topicFormBean.setContent("测试主题内容");

		//发表新主题帖
		TopicService topicService = (TopicService) SpringContextUtil.getContext().getBean("topicService");
		topicService.publishTopic(topicFormBean, 1L);

		//得到主题帖
		TopicPageBean topicPageBean = topicService.getLimitTopic(1, 5);

		Assert.assertEquals("测试主题标题", topicPageBean.getTopics().get(0).getTitle());
	}

	@Test
	public void testDeleteTopic()
	{
		//获取测试用户
		TestUserService testUserService = new TestUserService();
		testUserService.testRegistUser();

		//组织新的FormBean
		TopicFormBean topicFormBean = new TopicFormBean();
		topicFormBean.setTitle("测试主题标题");
		topicFormBean.setContent("测试主题内容");

		//发表新主题帖
		TopicService topicService = (TopicService) SpringContextUtil.getContext().getBean("topicService");
		topicService.publishTopic(topicFormBean, 1L);

		//得到主题帖
		TopicPageBean topicPageBean = topicService.getLimitTopic(1, 5);

		//删除主题帖
		topicService.deleteTopicById(topicPageBean.getTopics().get(0).getTopicId());

		//重新获得主题帖
		topicPageBean = topicService.getLimitTopic(1, 5);
		Assert.assertEquals(0, topicPageBean.getTopics().size());
	}
}
