package com.gsh.service.util;

import com.gsh.domain.User;

public class DefaultUserFactory
{
	/**
	 * 辅助初始化User类中需要初始化的字段
	 * @return 新的User实例
	 */
	public static User getDefaultInitializedUser()
	{
		//初始化User中需要初始化的字段
		User user = new User();
		user.setUsername("");
		user.setPassword("");
		user.setBanned(0);
		user.setEmail("");
		user.setExp(0);
		user.setMembered(0);
		user.setWatched(0);

		return user;
	}
}
