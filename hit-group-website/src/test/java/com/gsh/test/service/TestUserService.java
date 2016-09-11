package com.gsh.test.service;

import com.gsh.domain.AddFriendApply;
import com.gsh.domain.User;
import com.gsh.service.CrawlerService;
import com.gsh.service.UserService;
import com.gsh.test.util.SpringContextUtil;
import com.gsh.web.backend.beans.UserPageBean;
import com.gsh.web.crawler.beans.NewsCrawlerBean;
import com.gsh.web.news.beans.NewsReplyFormBean;
import com.gsh.web.news.beans.UserLoginFormBean;
import com.gsh.web.news.beans.UserRegistFormBean;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestUserService
{
	@Test
	public void testRegistUser()
	{
		//测试用户1
		UserRegistFormBean userRegistFormBean1 = new UserRegistFormBean();
		userRegistFormBean1.setUsername("testUser1");
		userRegistFormBean1.setPassword("000000000000000");
		userRegistFormBean1.setEmail("test1@test.com");

		//测试用户2
		UserRegistFormBean userRegistFormBean2 = new UserRegistFormBean();
		userRegistFormBean2.setUsername("testUser2");
		userRegistFormBean2.setPassword("111111111111111");
		userRegistFormBean2.setEmail("test2@test.com");

		//添加测试用户
		UserService userService = (UserService)SpringContextUtil.getContext().getBean("userService");
		userService.regist(userRegistFormBean1);
		userService.regist(userRegistFormBean2);
	}

	@Test
	public void testLoginUser()
	{
		//初始化表中数据
		this.testRegistUser();
		UserService userService = (UserService)SpringContextUtil.getContext().getBean("userService");

		//登陆成功测试
		UserLoginFormBean userLoginFormBean1 = new UserLoginFormBean();
		userLoginFormBean1.setUsername("testUser1");
		userLoginFormBean1.setPassword("000000000000000");
		User user1 = userService.login(userLoginFormBean1);
		Assert.assertNotNull(user1);

		//登陆失败测试-密码错误
		UserLoginFormBean userLoginFormBean2 = new UserLoginFormBean();
		userLoginFormBean2.setUsername("testUser1");
		userLoginFormBean2.setPassword("111100000000000");
		User user2 = userService.login(userLoginFormBean2);
		Assert.assertNull(user2);

		//登陆失败测试-用户名错误
		UserLoginFormBean userLoginFormBean3 = new UserLoginFormBean();
		userLoginFormBean3.setUsername("testUser3");
		userLoginFormBean3.setPassword("000000000000000");
		User user3 = userService.login(userLoginFormBean2);
		Assert.assertNull(user3);

	}

	@Test
	public void testQueryUserById()
	{
		this.testRegistUser();
		UserService userService = (UserService)SpringContextUtil.getContext().getBean("userService");
		User user = userService.queryUserById(1L);
		Assert.assertEquals(user.getUsername(), "testUser1");
	}

	@Test
	public void testQueryUserByUsername()
	{
		this.testRegistUser();
		UserService userService = (UserService)SpringContextUtil.getContext().getBean("userService");
		User user = userService.queryUserByUsername("testUser1");
		Assert.assertNotNull(user);
	}

	@Test
	public void testQueryUserByPage()
	{
		this.testRegistUser();
		UserService userService = (UserService)SpringContextUtil.getContext().getBean("userService");
		UserPageBean userPageBean = userService.queryUsersByPage(1, 5);
		Assert.assertEquals(userPageBean.getusers().size(), 2);
	}

	@Test
	public void testAddNewsReply()
	{
		//创建测试用户
		this.testRegistUser();

		//创建测试新闻
		NewsCrawlerBean newsCrawlerBean1 = new NewsCrawlerBean("测试新闻标题1","测试新闻内容1", "热点新闻");
		CrawlerService crawlerService = (CrawlerService)SpringContextUtil.getContext().getBean("crawlerService");
		crawlerService.addNews(newsCrawlerBean1);

		UserService userService = (UserService)SpringContextUtil.getContext().getBean("userService");
		NewsReplyFormBean newsReplyFormBean = new NewsReplyFormBean();
		newsReplyFormBean.setContent("测试新闻评论内容");
		userService.addNewsReply(newsReplyFormBean, 1L, 1L);
	}

	@Test
	public void testUpdateUserOptionalInfo()
	{
		//创建测试用户
		this.testRegistUser();

		//创建包含新新的的User对象
		User user = new User();
		user.setUsername("testUser1");
		user.setRealName("测试用户1");

		UserService userService = (UserService)SpringContextUtil.getContext().getBean("userService");
		userService.updateUserOptionalInfo(user);

		User persisted = userService.queryUserByUsername("testUser1");
		Assert.assertEquals(persisted.getRealName(), "测试用户1");
	}

	@Test
	public void testMakeFriendApply()
	{
		this.testRegistUser();
		UserService userService = (UserService)SpringContextUtil.getContext().getBean("userService");
		userService.makeAddFriendApply(1L, 2L);
	}

	@Test
	public void testGetAddFriendApply()
	{
		this.testMakeFriendApply();
		UserService userService = (UserService)SpringContextUtil.getContext().getBean("userService");
		List<AddFriendApply> addFriendApplyList = userService.getAddFriendApplies(2L);
		Assert.assertEquals(1, addFriendApplyList.size());
	}

	@Test
	public void testAcceptAddFriendApply()
	{
		this.testMakeFriendApply();
		UserService userService = (UserService)SpringContextUtil.getContext().getBean("userService");
		userService.acceptAddFriendApply(1L, 2L);
	}

	@Test
	public void testGetAllFriends()
	{
		this.testAcceptAddFriendApply();
		UserService userService = (UserService)SpringContextUtil.getContext().getBean("userService");
		List<User> userList = userService.getAllFriends(1L);
		Assert.assertEquals(1, userList.size());
		userList = userService.getAllFriends(2L);
		Assert.assertEquals(1, userList.size());
	}

	@Test
	public void testWatchUser()
	{
		this.testRegistUser();
		UserService userService = (UserService)SpringContextUtil.getContext().getBean("userService");
		userService.watchUser(1L, 2L);
		User user2 = userService.queryUserById(2L);
		Assert.assertEquals(1, user2.getWatched().intValue());
	}

	@Test
	public void testBuyMember()
	{
		this.testRegistUser();
		UserService userService = (UserService)SpringContextUtil.getContext().getBean("userService");
		userService.buyMember(1L);
	}

	@Test
	public void testBanAndRelease()
	{
		this.testRegistUser();
		UserService userService = (UserService)SpringContextUtil.getContext().getBean("userService");
		userService.banUser(1L);
		User user = userService.queryUserById(1L);
		Assert.assertEquals(1, user.getBanned().intValue());
		userService.releaseUser(1L);
		user = userService.queryUserById(1L);
		Assert.assertEquals(0, user.getBanned().intValue());
	}
}
