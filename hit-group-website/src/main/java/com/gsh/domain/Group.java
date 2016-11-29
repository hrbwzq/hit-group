package com.gsh.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_group")
public class Group implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_id")
	private Long groupId;

	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
	private Set<Topic> topics = new HashSet<>();

	@ManyToMany(mappedBy = "groups")
	private Set<User> users = new HashSet<>();

	public Long getGroupId()
	{
		return groupId;
	}

	public void setGroupId(Long groupId)
	{
		this.groupId = groupId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Set<Topic> getTopics()
	{
		return topics;
	}

	public void setTopics(Set<Topic> topics)
	{
		this.topics = topics;
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

		Group group = (Group) o;

		if (groupId != null ? !groupId.equals(group.groupId) : group.groupId != null) return false;
		if (name != null ? !name.equals(group.name) : group.name != null) return false;
		if (topics != null ? !topics.equals(group.topics) : group.topics != null) return false;
		return !(users != null ? !users.equals(group.users) : group.users != null);

	}

	@Override
	public int hashCode()
	{
		int result = groupId != null ? groupId.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (topics != null ? topics.hashCode() : 0);
		result = 31 * result + (users != null ? users.hashCode() : 0);
		return result;
	}
}
