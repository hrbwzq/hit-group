package com.gsh.service;

import com.gsh.domain.Group;

import java.util.List;

public interface GroupService
{
	/**
	 * 通过圈子ID查找圈子
	 * @param groupId 圈子ID
	 * @return 圈子对象
	 */
	public Group getGroupById(Long groupId);

	/**
	 * 通过圈子名查找圈子
	 * @param groupName 圈子名
	 * @return 圈子对象
	 */
	public Group getGroupByName(String groupName);

	/**
	 * 创建圈子
	 * @param groupName 圈子名
	 * @return 圈子对象
	 */
	public void createGroup(String groupName);
}
