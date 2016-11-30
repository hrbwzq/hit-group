package com.gsh.service.impl;

import com.gsh.domain.Group;
import com.gsh.service.GroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "groupService")
@Transactional
public class GroupServiceImpl extends CommonService implements GroupService
{
	@Override
	public Group getGroupById(Long groupId)
	{
		return this.getGroupDAO().getGroupByGroupId(groupId);
	}

	@Override
	public Group getGroupByName(String groupName)
	{
		return this.getGroupDAO().getGroupByGroupName(groupName);
	}

	@Override
	public void createGroup(String groupName)
	{
		Group group = new Group();
		group.setName(groupName);
		this.getGroupDAO().addGroup(group);
	}
}
