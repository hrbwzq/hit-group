package com.gsh.web.backend.beans;

import com.gsh.domain.User;
import com.gsh.web.util.PageBeanSupport;

import java.util.ArrayList;
import java.util.List;

public class UserPageBean extends PageBeanSupport
{
	private List<User> users = new ArrayList<>();

	public List<User> getusers()
	{
		return users;
	}
}
