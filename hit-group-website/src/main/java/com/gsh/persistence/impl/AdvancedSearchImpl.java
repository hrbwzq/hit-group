package com.gsh.persistence.impl;

import com.gsh.domain.Group;
import com.gsh.domain.User;
import com.gsh.persistence.AdvancedSearch;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("advancedSearch")
@Transactional
public class AdvancedSearchImpl extends CommonDAO implements AdvancedSearch
{
	@Override
	public List<User> userFuzzyQuery(String pattern)
	{
		String hql = "from User user where user.username like CONCAT('%', :pattern, '%')";
		Query query = this.getSession().createQuery(hql);
		query.setString("pattern", pattern);
		@SuppressWarnings("unchecked")
		List<User> userList = query.list();
		return userList;
	}

	@Override
	public List<Group> groupFuzzyQuery(String pattern)
	{
		String hql = "from Group group where group.name like CONCAT('%', :pattern, '%')";
		Query query = this.getSession().createQuery(hql);
		query.setString("pattern", pattern);
		@SuppressWarnings("unchecked")
		List<Group> groupList = query.list();
		return groupList;
	}
}
