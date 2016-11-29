package com.gsh.service.impl;

import com.gsh.domain.*;
import com.gsh.service.UserService;
import com.gsh.service.util.CopyUserPropertyUtil;
import com.gsh.service.util.DefaultUserFactory;
import com.gsh.service.util.PageUtil;
import com.gsh.web.backend.beans.UserPageBean;
import com.gsh.web.forum.beans.UserProfileFormBean;
import com.gsh.web.news.beans.NewsReplyFormBean;
import com.gsh.web.news.beans.UserLoginFormBean;
import com.gsh.web.news.beans.UserRegistFormBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service(value = "userService")
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

		//添加普通用户权限
		Privilege privilege = this.getPrivilegeDAO().getPrivilegeByName("user");
		user.getPrivileges().add(privilege);

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
	public void updateUserOptionalInfo(UserProfileFormBean userProfileFormBean, Long userId)
	{
		User user = getUserDAO().getUserById(userId);
		if(user != null)
		{
			//恶心代码,需要重构！！
			if(!userProfileFormBean.getSex().equals(""))
			{
				user.setSex(userProfileFormBean.getSex());
			}
			else
			{
				user.setSex("secret");
			}
			if(!userProfileFormBean.getRealname().equals(""))
			{
				user.setRealName(userProfileFormBean.getRealname());
			}
			else
			{
				user.setRealName(null);
			}
			if(userProfileFormBean.getAge() != null)
			{
				user.setAge(userProfileFormBean.getAge());
			}
			else
			{
				user.setAge(null);
			}
			if(!userProfileFormBean.getMajor().equals(""))
			{
				user.setMajor(userProfileFormBean.getMajor());
			}
			else
			{
				user.setMajor(null);
			}
			if(!userProfileFormBean.getAddress().equals(""))
			{
				user.setAddress(userProfileFormBean.getAddress());
			}
			else
			{
				user.setAddress(null);
			}
			if(!userProfileFormBean.getQq().equals(""))
			{
				user.setQq(userProfileFormBean.getQq());
			}
			else
			{
				user.setQq(null);
			}
			if(!userProfileFormBean.getPhone().equals(""))
			{
				user.setPhone(userProfileFormBean.getPhone());
			}
			else
			{
				user.setPhone(null);
			}
		}
	}

	@Override
	public void updateUserThumbnail(String imgPath, Long userId)
	{
		User user = getUserDAO().getUserById(userId);
		if(user != null)
		{
			user.setThumbnail(imgPath);
		}
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
	public void deleteFriend(Long userId, Long friendId)
	{
		User user = getUserDAO().getUserById(userId);
		User friend = getUserDAO().getUserById(friendId);
		Set<User> friends = user.getFriends();
		Set<User> friendsRef = friend.getFriends();

		if(friends.contains(friend))
		{
			friends.remove(friend);
		}
		if(friendsRef.contains(user))
		{
			friendsRef.remove(user);
		}

	}

	@Override
	public List<User> getAllWatchers(Long userId)
	{
		Set<User> userSet = getUserDAO().getUserById(userId).getWatchers();
		List<User> userList = new ArrayList<>();
		userList.addAll(userSet);
		return userList;
	}

	@Override
	public List<AddFriendApply> getAddFriendApplies(Long userId)
	{
		return getFriendApplyDAO().getToUserApply(userId);
	}

	@Override
	public void makeAddFriendApply(Long fromUserId, Long toUserId)
	{
		getFriendApplyDAO().addFriendApply(fromUserId, toUserId);
	}

	@Override
	public boolean ifApplyExist(Long userId1, Long userId2)
	{
		return (this.getFriendApplyDAO().getApplyByUserId(userId1, userId2) != null || this.getFriendApplyDAO().getApplyByUserId(userId2, userId1) != null);
	}

	@Override
	public void acceptAddFriendApply(Long fromUserId, Long toUserId)
	{
		getFriendApplyDAO().deleteFriendApply(fromUserId, toUserId);
		User fromUser = getUserDAO().getUserById(fromUserId);
		User toUser = getUserDAO().getUserById(toUserId);
		toUser.getFriends().add(fromUser);
		fromUser.getFriends().add(toUser);
	}

	@Override
	public void deleteAddFriendApply(Long fromUserId, Long toUserId)
	{
		getFriendApplyDAO().deleteFriendApply(fromUserId, toUserId);
	}

	@Override
	public void watchUser(Long fromUserId, Long toUserId)
	{
		User fromUser = getUserDAO().getUserById(fromUserId);
		User toUser = getUserDAO().getUserById(toUserId);
		fromUser.getWatchersRef().add(toUser);
		toUser.getWatchers().add(fromUser);
		toUser.setWatched(toUser.getWatched() + 1);
	}

	@Override
	public List<Chat> getAllChats(Long userId)
	{
		return getChatDAO().queryChatsByUserId(userId);
	}


	@Override
	public void buyMember(Long userId)
	{
		getUserDAO().getUserById(userId).setMembered(1);
	}

	@Override
	public void banUser(Long userId)
	{
		getUserDAO().getUserById(userId).setBanned(1);
	}

	@Override
	public void releaseUser(Long userId)
	{
		getUserDAO().getUserById(userId).setBanned(0);
	}

	@Override
	public List<Topic> getUserRecentTopic(Long userId)
	{
		return getUserDAO().getUserRecentTopic(userId);
	}

	@Override
	public List<Privilege> getAllPrivileges(Long userId)
	{
		User user = this.getUserDAO().getUserById(userId);
		List<Privilege> privilegeList = new ArrayList<>();
		Set<Privilege> privilegeSet = user.getPrivileges();
		privilegeList.addAll(privilegeSet);
		return privilegeList;
	}

	@Override
	public void joinGroup(Long userId, Long groupId)
	{
		User user = this.getUserDAO().getUserById(userId);
		Group group = this.getGroupDAO().getGroupByGroupId(groupId);
		user.getGroups().add(group);
		group.getUsers().add(user);
	}

	@Override
	public void exitGroup(Long userId, Long groupId)
	{
		User user = this.getUserDAO().getUserById(userId);
		Group group = this.getGroupDAO().getGroupByGroupId(groupId);
		if(user.getGroups().contains(group))
		{
			user.getGroups().remove(group);
		}
		if(group.getUsers().contains(user))
		{
			group.getUsers().remove(user);
		}
	}

	@Override
	public List<Group> getJoinedGroups(Long userId)
	{
		User user = this.getUserDAO().getUserById(userId);
		Set<Group> groupSet = user.getGroups();
		List<Group> groupList = new ArrayList<>();
		groupList.addAll(groupSet);
		return groupList;
	}
}
