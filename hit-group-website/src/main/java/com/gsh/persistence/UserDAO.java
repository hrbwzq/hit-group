package com.gsh.persistence;

import com.gsh.domain.Group;
import com.gsh.domain.Topic;
import com.gsh.domain.User;

import java.util.List;

public interface UserDAO
{
	/**
	 * 添加新的用户对象
	 * @param user 用户对象
	 */
	public void addUser(User user);

	/**
	 * 通过用户名得到用户对象
	 * @param username 用户名
	 * @return 用户对象,如果查询结果为空返回null
	 */
	public User getUserByUsername(String username);

	/**
	 * 通过用户ID得到用户对象
	 * @param userId 用户ID
	 * @return 用户对象,如果查询结果为空返回null
	 */
	public User getUserById(Long userId);

	/**
	 * 查询所有用户记录条数
	 * @return 用户记录条数
	 */
	public int queryAllUsersCount();

	/**
	 * 查询所有用户
	 * @return 包含查询结果的列表
	 */
	@Deprecated
	public List<User> queryAllUsers();

	/**
	 * 分页查询用户信息
	 * @param startPage 起始页
	 * @param pageSize 分页大小
	 * @return 包含查询结果的列表,可能为空列表
	 */
	public List<User> queryUsersByPage(int startPage, int pageSize);

	/**
	 * 得到用户最近发帖
	 * @param userId 用户ID
	 * @return 保函查询结果的列表
	 */
	public List<Topic> getUserRecentTopic(Long userId);

}
