package com.gsh.persistence.impl;

import com.gsh.domain.Group;
import com.gsh.domain.Topic;
import com.gsh.domain.User;
import com.gsh.persistence.UserDAO;
import com.gsh.web.consts.PageSize;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "userDAO")
public class UserDAOImpl extends CommonDAO implements UserDAO
{

	@Override
	public void addUser(User user)
	{
		this.getSession().save(user);
	}

	@Override
	public User getUserByUsername(String username)
	{
		Query query = this.getSession().createQuery("from User user where username=:username");
		query.setString("username", username);
		return (User)query.uniqueResult();
	}

	@Override
	public User getUserById(Long userId)
	{
		return (User)this.getSession().get(User.class, userId);
	}

	@Override
	public int queryAllUsersCount()
	{
		Query query = this.getSession().createQuery("select count(userId) from User user");
		Long result = (long)query.uniqueResult();
		return result.intValue();
	}

	@Override
	public List<User> queryAllUsers()
	{
		@SuppressWarnings("unchecked")
		List<User> userList = this.getSession().createQuery("from User user").list();
		return userList;
	}

	@Override
	public List<User> queryUsersByPage(int startPage, int pageSize)
	{
		String hql = "from User user";
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult((startPage - 1) * pageSize);
		query.setMaxResults(pageSize);
		@SuppressWarnings("unchecked")
		List<User> userList = query.list();
		return userList;
	}

	@Override
	public List<Topic> getUserRecentTopic(Long userId)
	{
		String hql = "from Topic topic where topic.user.userId=:userId order by lastModifiedTime desc";
		Query query = this.getSession().createQuery(hql);
		query.setLong("userId", userId);
		query.setFirstResult(0);
		query.setMaxResults(PageSize.USER_RECENT_TOPICS);
		@SuppressWarnings("unchecked")
		List<Topic> topicList = query.list();
		return topicList;
	}

}
