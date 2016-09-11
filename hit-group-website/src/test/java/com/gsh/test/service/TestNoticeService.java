package com.gsh.test.service;

import com.gsh.domain.Notice;
import com.gsh.service.NoticeService;
import com.gsh.test.util.SpringContextUtil;
import com.gsh.web.backend.beans.NoticeFormBean;
import org.junit.Assert;
import org.junit.Test;

public class TestNoticeService
{
	@Test
	public void testCreateAndGetNotice()
	{
		//创建测试用户
		TestUserService testUserService = new TestUserService();
		testUserService.testRegistUser();

		//创建测试公告
		NoticeFormBean noticeFormBean1 = new NoticeFormBean();
		NoticeFormBean noticeFormBean2 = new NoticeFormBean();

		noticeFormBean1.setContent("测试公告1");
		noticeFormBean2.setContent("测试公告2");

		//测试发布公告
		NoticeService noticeService = (NoticeService)SpringContextUtil.getContext().getBean("noticeService");
		noticeService.createNewNotice(noticeFormBean1, 1L);
		noticeService.createNewNotice(noticeFormBean2, 1L);

		//测试获取公告
		Notice notice = noticeService.getCurrentNotice();
		Assert.assertEquals("测试公告2", notice.getContent());
	}
}
