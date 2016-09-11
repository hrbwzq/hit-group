package com.gsh.persistence.impl;

import com.gsh.domain.Privilege;
import com.gsh.persistence.PrivilegeDAO;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "privilegeDAO")
public class PrivilegeDAOImpl extends CommonDAO implements PrivilegeDAO
{

	@Override
	public Privilege getPrivilegeById(Long privilegeId)
	{
		return (Privilege)this.getSession().get(Privilege.class, privilegeId);
	}

	@Override
	public Privilege getPrivilegeByName(String privilegeName)
	{
		Query query = this.getSession().createQuery("from Privilege p where privilege=:privilege");
		query.setString("privilege", privilegeName);
		return (Privilege)query.uniqueResult();
	}
}
