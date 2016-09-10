package com.gsh.persistence;

import com.gsh.domain.User;

import java.util.List;

public interface UserDAO
{
	public void addUser(User user);

	public User getUserByUsername(String username);

	@Deprecated
	public List<User> queryAllUsers();

	public List<User> queryUsersByPage(int startPage, int pageSize);

	public void updateUserById(User user, Long userId);
}
