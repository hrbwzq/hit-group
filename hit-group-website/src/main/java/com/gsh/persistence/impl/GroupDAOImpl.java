package com.gsh.persistence.impl;

import com.gsh.domain.Group;
import com.gsh.persistence.GroupDAO;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "groupDAO")
public class GroupDAOImpl extends CommonDAO implements GroupDAO
{

	@Override
	public void addGroup(Group group)
	{
		this.getSession().save(group);
	}

	@Override
	public Group getGroupByGroupId(Long groupId)
	{
		Query query = this.getSession().createQuery("from Group group where groupId=:groupId");
		query.setLong("groupId", groupId);
		return (Group)query.uniqueResult();
	}
}
