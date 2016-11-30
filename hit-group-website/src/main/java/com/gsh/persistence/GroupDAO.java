package com.gsh.persistence;

import com.gsh.domain.Group;

public interface GroupDAO
{
	/**
	 * 添加圈子分类
	 * @param group 圈子领域类
	 */
	public void addGroup(Group group);

	/**
	 * 通过圈子ID查找圈子对象
	 * @param groupId 圈子ID
	 * @return 圈子对象
	 */
	public Group getGroupByGroupId(Long groupId);

	/**
	 * 通过圈子名查找圈子对象
	 * @param groupName 圈子名
	 * @return 圈子对象
	 */
	public Group getGroupByGroupName(String groupName);
}
