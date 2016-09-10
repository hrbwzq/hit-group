package com.gsh.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_chat")
public class Chat implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "chat_id")
	private Long chatId;

	@Column(name = "content", length = 65535, nullable = false)
	private String content;

	@Column(name = "create_time", nullable = false)
	private Date createTime;

	@Column(name = "readed", nullable = false)
	private Integer read;

	@Column(name = "deleted", nullable = false)
	private Integer deleted;

	@ManyToOne
	private User fromUser;

	@ManyToOne
	private User toUser;

	public Chat()
	{
	}

	public Long getChatId()
	{
		return chatId;
	}

	public void setChatId(Long chatId)
	{
		this.chatId = chatId;
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

	public Integer getRead()
	{
		return read;
	}

	public void setRead(Integer read)
	{
		this.read = read;
	}

	public Integer getDeleted()
	{
		return deleted;
	}

	public void setDeleted(Integer deleted)
	{
		this.deleted = deleted;
	}

	public User getFromUser()
	{
		return fromUser;
	}

	public void setFromUser(User fromUser)
	{
		this.fromUser = fromUser;
	}

	public User getToUser()
	{
		return toUser;
	}

	public void setToUser(User toUser)
	{
		this.toUser = toUser;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Chat chat = (Chat) o;

		if (chatId != null ? !chatId.equals(chat.chatId) : chat.chatId != null) return false;
		if (content != null ? !content.equals(chat.content) : chat.content != null) return false;
		if (createTime != null ? !createTime.equals(chat.createTime) : chat.createTime != null) return false;
		if (read != null ? !read.equals(chat.read) : chat.read != null) return false;
		return !(deleted != null ? !deleted.equals(chat.deleted) : chat.deleted != null);

	}

	@Override
	public int hashCode()
	{
		int result = chatId != null ? chatId.hashCode() : 0;
		result = 31 * result + (content != null ? content.hashCode() : 0);
		result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
		result = 31 * result + (read != null ? read.hashCode() : 0);
		result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
		return result;
	}
}
