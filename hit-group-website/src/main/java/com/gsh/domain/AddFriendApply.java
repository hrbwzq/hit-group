package com.gsh.domain;

import javax.persistence.*;

@Entity
@Table(name = "t_friends_validate")
public class AddFriendApply
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "from_user_id")
	private Long fromUserId;

	@Column(name = "to_user_id")
	private Long toUserId;

	public AddFriendApply()
	{
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getFromUserId()
	{
		return fromUserId;
	}

	public void setFromUserId(Long fromUserId)
	{
		this.fromUserId = fromUserId;
	}

	public Long getToUserId()
	{
		return toUserId;
	}

	public void setToUserId(Long toUserId)
	{
		this.toUserId = toUserId;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AddFriendApply that = (AddFriendApply) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (fromUserId != null ? !fromUserId.equals(that.fromUserId) : that.fromUserId != null) return false;
		return !(toUserId != null ? !toUserId.equals(that.toUserId) : that.toUserId != null);

	}

	@Override
	public int hashCode()
	{
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (fromUserId != null ? fromUserId.hashCode() : 0);
		result = 31 * result + (toUserId != null ? toUserId.hashCode() : 0);
		return result;
	}
}
