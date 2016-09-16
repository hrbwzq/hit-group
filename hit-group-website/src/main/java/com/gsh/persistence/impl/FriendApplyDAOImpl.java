package com.gsh.persistence.impl;

import com.gsh.domain.AddFriendApply;
import com.gsh.persistence.FriendApplyDAO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "friendApplyDAO")
public class FriendApplyDAOImpl extends CommonDAO implements FriendApplyDAO
{

	@Override
	public List<AddFriendApply> getToUserApply(Long toUserId)
	{
		String hql = "from AddFriendApply afa where toUserId=:toUserId";
		Query query = this.getSession().createQuery(hql);
		query.setLong("toUserId", toUserId);
		@SuppressWarnings("unchecked")
		List<AddFriendApply> addFriendApplyList = query.list();
		return addFriendApplyList;
	}

	@Override
	public void addFriendApply(Long fromUserId, Long toUserId)
	{
		AddFriendApply addFriendApply = new AddFriendApply();
		addFriendApply.setFromUserId(fromUserId);
		addFriendApply.setToUserId(toUserId);
		this.getSession().save(addFriendApply);
	}

	@Override
	public void deleteFriendApply(Long fromUserId, Long toUserId)
	{
		Session session = this.getSession();
		String hql = "from AddFriendApply afa where toUserId=:toUserId and fromUserId=:fromUserId";
		Query query = session.createQuery(hql);
		query.setLong("toUserId", toUserId);
		query.setLong("fromUserId", fromUserId);
		AddFriendApply addFriendApply = (AddFriendApply)query.uniqueResult();
		if(addFriendApply != null)
		{
			session.delete(addFriendApply);
		}
	}

	@Override
	public AddFriendApply getApplyByUserId(Long fromUserId, Long toUserId)
	{
		Session session = this.getSession();
		String hql = "from AddFriendApply afa where toUserId=:toUserId and fromUserId=:fromUserId";
		Query query = session.createQuery(hql);
		query.setLong("toUserId", toUserId);
		query.setLong("fromUserId", fromUserId);
		return (AddFriendApply)query.uniqueResult();
	}

}
