package com.gsh.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_topic")
public class Topic implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "topic_id")
	private Long topicId;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "create_time", nullable = false)
	private Date createTime;

	@Column(name = "last_modified_time", nullable = false)
	private Date lastModifiedTime;

	@Column(name = "deleted", nullable = false)
	private Integer deleted;

	@ManyToOne
	private User user;

	@ManyToOne
	private Group group;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "topic")
	private Set<TopicReply> topicReplies = new HashSet<>();

	public Topic()
	{
	}

	public Long getTopicId()
	{
		return topicId;
	}

	public void setTopicId(Long topicId)
	{
		this.topicId = topicId;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
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

	public Date getLastModifiedTime()
	{
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime)
	{
		this.lastModifiedTime = lastModifiedTime;
	}

	public Integer getDeleted()
	{
		return deleted;
	}

	public void setDeleted(Integer deleted)
	{
		this.deleted = deleted;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Set<TopicReply> getTopicReplies()
	{
		return topicReplies;
	}

	public void setTopicReplies(Set<TopicReply> topicReplies)
	{
		this.topicReplies = topicReplies;
	}

	public String getLastModifiedTimeString()
	{
		return this.lastModifiedTime.toString().substring(0, 19);
	}

	public Group getGroup()
	{
		return group;
	}

	public void setGroup(Group group)
	{
		this.group = group;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Topic topic = (Topic) o;

		if (topicId != null ? !topicId.equals(topic.topicId) : topic.topicId != null) return false;
		if (title != null ? !title.equals(topic.title) : topic.title != null) return false;
		if (content != null ? !content.equals(topic.content) : topic.content != null) return false;
		if (createTime != null ? !createTime.equals(topic.createTime) : topic.createTime != null) return false;
		if (lastModifiedTime != null ? !lastModifiedTime.equals(topic.lastModifiedTime) : topic.lastModifiedTime != null)
			return false;
		if (deleted != null ? !deleted.equals(topic.deleted) : topic.deleted != null) return false;
		if (user != null ? !user.equals(topic.user) : topic.user != null) return false;
		if (group != null ? !group.equals(topic.group) : topic.group != null) return false;
		return !(topicReplies != null ? !topicReplies.equals(topic.topicReplies) : topic.topicReplies != null);

	}

	@Override
	public int hashCode()
	{
		int result = topicId != null ? topicId.hashCode() : 0;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (content != null ? content.hashCode() : 0);
		result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
		result = 31 * result + (lastModifiedTime != null ? lastModifiedTime.hashCode() : 0);
		result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
		result = 31 * result + (user != null ? user.hashCode() : 0);
		result = 31 * result + (group != null ? group.hashCode() : 0);
		result = 31 * result + (topicReplies != null ? topicReplies.hashCode() : 0);
		return result;
	}
}
