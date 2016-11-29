package com.gsh.service;

import com.gsh.domain.*;
import com.gsh.web.backend.beans.UserPageBean;
import com.gsh.web.forum.beans.UserProfileFormBean;
import com.gsh.web.news.beans.NewsReplyFormBean;
import com.gsh.web.news.beans.UserLoginFormBean;
import com.gsh.web.news.beans.UserRegistFormBean;

import java.util.List;

public interface UserService
{
	/**
	 * 用户注册
	 * @param userRegistFormBean 包含注册信息的FormBean
	 */
	public void regist(UserRegistFormBean userRegistFormBean);

	/**
	 * 用户登陆
	 * @param userLoginFormBean 包含登陆表单信息的FormBean
	 * @return 如果登陆成功返回用户对象,登陆失败返回null
	 */
	public User login(UserLoginFormBean userLoginFormBean);

	/**
	 * 通过ID查询用户
	 * @param userId 用户ID
	 * @return 如果查询成功返回用户对象,失败返回null
	 */
	public User queryUserById(Long userId);

	/**
	 * 通过用户名查询用户
	 * @param username 用户名
	 * @return 如果查询成功返回用户对象,失败返回null
	 */
	public User queryUserByUsername(String username);

	/**
	 * 分页查询所有注册用户
	 * @param startPage 起始页
	 * @param pageSize 分页大小
	 * @return 保函查询结果的PageBean
	 */
	public UserPageBean queryUsersByPage(int startPage, int pageSize);

	/**
	 * 发表新闻回复
	 * @param newsReplyFormBean 包含新闻回复表单信息的FormBean
	 * @param newsId 新闻ID
	 * @param userId 用户ID
	 */
	public void addNewsReply(NewsReplyFormBean newsReplyFormBean, Long newsId, Long userId);

	/**
	 * 更新用户个人信息,查询字段为ID或username任意一个
	 * 可更新字段包括sex,thumbnail,realName,age,major,
	 * address,qq,phone
	 * @param user 包含新的个人信息的用户类
	 */
	public void updateUserOptionalInfo(User user);

	/**
	 * 更新用户个人信息
	 * @param userProfileFormBean 包含新信息的FormBean
	 */
	public void updateUserOptionalInfo(UserProfileFormBean userProfileFormBean, Long userId);

	/**
	 * 通过用户ID更新头像图片
	 * @param imgPath 头像图片URL
	 * @param userId 用户ID
	 */
	public void updateUserThumbnail(String imgPath, Long userId);

	/**
	 * 获取所有好友信息
	 * @param userId 用户ID
	 * @return 包含所有好友的列表
	 */
	public List<User> getAllFriends(Long userId);

	/**
	 * 删除好友
	 * @param userId 用户ID
	 * @param friendId 好友ID
	 */
	public void deleteFriend(Long userId, Long friendId);

	/**
	 * 获得所有关注者
	 * @param userId 用户ID
	 * @return 包含所有关注者的列表,可能为空列表
	 */
	public List<User> getAllWatchers(Long userId);

	/**
	 * 获取所有好友申请
	 * @param userId 用户ID
	 * @return 包含所有好友申请的列表
	 */
	public List<AddFriendApply> getAddFriendApplies(Long userId);

	/**
	 * 发起好友申请
	 * @param fromUserId 好友申请发起者
	 * @param toUserId 好友申请接收者
	 */
	public void makeAddFriendApply(Long fromUserId, Long toUserId);

	/**
	 * 判断好友申请是否存在
	 * @param userId1 用户ID
	 * @param userId2 用户ID
	 * @return 好友申请存在返回true,不存在返回false
	 */
	public boolean ifApplyExist(Long userId1, Long userId2);

	/**
	 * 接受好友申请
	 * @param fromUserId 好友申请发起者
	 * @param toUserId 好友申请接收者
	 */
	public void acceptAddFriendApply(Long fromUserId, Long toUserId);

	/**
	 * 删除好友申请
	 * @param fromUserId 好友申请发起者
	 * @param toUserId 好友申请接收者
	 */
	public void deleteAddFriendApply(Long fromUserId, Long toUserId);

	/**
	 * 关注一个用户
	 * @param fromUserId 发起关注的用户
	 * @param toUserId 被关注的用户
	 */
	public void watchUser(Long fromUserId, Long toUserId);

	/**
	 * 根据用户ID获得所有发来的私信
	 * @param userId 用户ID
	 * @return 包含所有发来的私信的列表，可能为空
	 */
	public List<Chat> getAllChats(Long userId);

	/**
	 * 充值为会员
	 * @param userId 用户ID
	 */
	public void buyMember(Long userId);

	/**
	 * 封禁用户
	 * @param userId 用户ID
	 */
	public void banUser(Long userId);

	/**
	 * 解除封禁用户
	 * @param userId 用户ID
	 */
	public void releaseUser(Long userId);

	/**
	 * 得到用户最近发帖
	 * @return 保函查询结果的列表,可能为空
	 */
	public List<Topic> getUserRecentTopic(Long userId);

	/**
	 * 得到用户所有权限
	 * @param userId 用户ID
	 * @return 权限列表
	 */
	public List<Privilege> getAllPrivileges(Long userId);

	/**
	 * 用户加入圈子
	 * @param userId 用户ID
	 * @param groupId 圈子ID
	 */
	public void joinGroup(Long userId, Long groupId);

	/**
	 * 用户退出圈子
	 * @param userId 用户ID
	 * @param groupId 圈子ID
	 */
	public void exitGroup(Long userId, Long groupId);

	/**
	 * 得到用户已加入的圈子
	 * @param userId 用户ID
	 * @return 包含已加入圈子的列表
	 */
	public List<Group> getJoinedGroups(Long userId);
}
