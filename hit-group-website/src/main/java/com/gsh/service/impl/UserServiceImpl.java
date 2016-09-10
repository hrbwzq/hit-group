package com.gsh.service.impl;

import com.gsh.domain.*;
import com.gsh.service.UserService;
import com.gsh.service.util.CopyUserPropertyUtil;
import com.gsh.service.util.DefaultUserFactory;
import com.gsh.service.util.PageUtil;
import com.gsh.web.backend.beans.UserPageBean;
import com.gsh.web.news.beans.NewsReplyFormBean;
import com.gsh.web.news.beans.UserLoginFormBean;
import com.gsh.web.news.beans.UserRegistFormBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl extends CommonService implements UserService
{
	@Override
	public void regist(UserRegistFormBean userRegistFormBean)
	{
		//封装新的User对象
		User user = DefaultUserFactory.getDefaultInitializedUser();
		user.setUsername(userRegistFormBean.getUsername());
		user.setPassword(userRegistFormBean.getPassword());
		user.setEmail(userRegistFormBean.getEmail());

		//保存新的User对象
		getUserDAO().addUser(user);
	}

	@Override
	public User login(UserLoginFormBean userLoginFormBean)
	{
		//从FormBean中得到用户名和密码
		String username = userLoginFormBean.getUsername();
		String password = userLoginFormBean.getPassword();

		//根据用户名查询用户并核对密码
		User user = getUserDAO().getUserByUsername(username);
		if(user != null && user.getPassword().equals(password))
		{
			return user;
		}
		else
		{
			return null;
		}
	}

	@Override
	public User queryUserById(Long userId)
	{
		return getUserDAO().getUserById(userId);
	}

	@Override
	public User queryUserByUsername(String username)
	{
		return getUserDAO().getUserByUsername(username);
	}

	@Override
	public UserPageBean queryUsersByPage(int startPage, int pageSize)
	{
		//得到分页总数
		int queryCount = getUserDAO().queryAllUsersCount();
		int pageCount = PageUtil.getPageCount(queryCount, pageSize);

		//封装新的UserPageBean对象
		List<User> userList = getUserDAO().queryUsersByPage(startPage, pageSize);
		UserPageBean userPageBean = new UserPageBean();
		userPageBean.setCurrentPage(startPage);
		userPageBean.setTotalPage(pageCount);
		userPageBean.getusers().addAll(userList);

		return userPageBean;
	}

	@Override
	public void addNewsReply(NewsReplyFormBean newsReplyFormBean, Long newsId, Long userId)
	{
		//根据用户ID查询用户
		User user = getUserDAO().getUserById(userId);

		//根据新闻ID查询新闻
		News news = getNewsDAO().queryNewsById(newsId);

		//封装新的新闻评论对象
		NewsReply newsReply = new NewsReply();
		newsReply.setContent(newsReplyFormBean.getContent());
		newsReply.setUser(user);
		newsReply.setNews(news);

		//保存新的新闻评论对象
		getNewsReplyDAO().addNewsReply(newsReply);
	}

	@Override
	public void updateUserOptionalInfo(User user)
	{
		User previousUser;
		if(user.getUserId() != null)
		{
			previousUser = getUserDAO().getUserById(user.getUserId());
		}
		else if(!user.getUsername().equals(""))
		{
			previousUser = getUserDAO().getUserByUsername(user.getUsername());
		}
		else
		{
			throw new RuntimeException("参数不包含可用的查询字段");
		}
		CopyUserPropertyUtil.copyOptionalProperty(user, previousUser);
	}

	@Override
	public List<User> getAllFriends(Long userId)
	{
		Set<User> userSet =  getUserDAO().getUserById(userId).getFriends();
		List<User> userList = new ArrayList<>();
		userList.addAll(userSet);
		return userList;
	}

	@Override
	public List<AddFriendApply> getAddFriendApplies(Long userId)
	{
		return null;
	}

	@Override
	public void makeAddFriendApply(Long fromUserId, Long toUserId)
	{

	}

	@Override
	public void acceptAddFriendApply(Long fromUserId, Long toUserId)
	{

	}

	@Override
	public void watchUser(Long fromUserId, Long toUserId)
	{

	}

	@Override
	public List<Chat> getAllchats()
	{
		return null;
	}

	@Override
	public void buyMember(Long userId)
	{

	}

	@Override
	public void banUser(Long userId)
	{

	}

	@Override
	public void releaseUser(Long userId)
	{

	}
}
