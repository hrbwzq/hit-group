package com.gsh.service.util;

import com.gsh.domain.User;

public class CopyUserPropertyUtil
{
	public static void copyOptionalProperty(User fromUser, User toUser)
	{
		toUser.setSex(fromUser.getSex());
		toUser.setThumbnail(fromUser.getThumbnail());
		toUser.setRealName(fromUser.getRealName());
		toUser.setAge(fromUser.getAge());
		toUser.setMajor(fromUser.getMajor());
		toUser.setAddress(fromUser.getAddress());
		toUser.setQq(fromUser.getQq());
		toUser.setPhone(fromUser.getPhone());
	}
}
