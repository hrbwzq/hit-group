package com.gsh.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_notice")
public class Notice
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notice_id")
	private Long noticeId;

	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "create_time", nullable = false)
	private Date createTime;

	@Column(name = "actived", nullable = false)
	private Integer actived;

	@ManyToOne
	private User user;

	public Notice()
	{
	}

	public Long getNoticeId()
	{
		return noticeId;
	}

	public void setNoticeId(Long noticeId)
	{
		this.noticeId = noticeId;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public Integer getActived()
	{
		return actived;
	}

	public void setActived(Integer actived)
	{
		this.actived = actived;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public String getCreateTimeString()
	{
		return createTime.toString().substring(0, 19);
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Notice notice = (Notice) o;

		if (noticeId != null ? !noticeId.equals(notice.noticeId) : notice.noticeId != null) return false;
		if (content != null ? !content.equals(notice.content) : notice.content != null) return false;
		if (createTime != null ? !createTime.equals(notice.createTime) : notice.createTime != null) return false;
		return !(actived != null ? !actived.equals(notice.actived) : notice.actived != null);

	}

	@Override
	public int hashCode()
	{
		int result = noticeId != null ? noticeId.hashCode() : 0;
		result = 31 * result + (content != null ? content.hashCode() : 0);
		result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
		result = 31 * result + (actived != null ? actived.hashCode() : 0);
		return result;
	}
}
