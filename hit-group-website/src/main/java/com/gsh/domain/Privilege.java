package com.gsh.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_privilege")
public class Privilege implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "privilege_id")
	private Long privilegeId;

	@Column(name = "privilege", nullable = false, unique = true)
	private String privilege;

	@ManyToMany(mappedBy = "privileges")
	private Set<User> users = new HashSet<>();

	public Privilege()
	{
	}

	public Long getPrivilegeId()
	{
		return privilegeId;
	}

	public void setPrivilegeId(Long privilegeId)
	{
		this.privilegeId = privilegeId;
	}

	public String getPrivilege()
	{
		return privilege;
	}

	public void setPrivilege(String privilege)
	{
		this.privilege = privilege;
	}

	public Set<User> getUsers()
	{
		return users;
	}

	public void setUsers(Set<User> users)
	{
		this.users = users;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Privilege privilege1 = (Privilege) o;

		if (privilegeId != null ? !privilegeId.equals(privilege1.privilegeId) : privilege1.privilegeId != null)
			return false;
		return !(privilege != null ? !privilege.equals(privilege1.privilege) : privilege1.privilege != null);

	}

	@Override
	public int hashCode()
	{
		int result = privilegeId != null ? privilegeId.hashCode() : 0;
		result = 31 * result + (privilege != null ? privilege.hashCode() : 0);
		return result;
	}
}
