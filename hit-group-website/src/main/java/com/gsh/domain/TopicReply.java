package com.gsh.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_topic_reply")
public class TopicReply implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "topic_reply_id")
	private Long topicReplyId;

	@Column(name = "floor", nullable = false)
	private Integer floor;

	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "deleted", nullable = false)
	private Integer deleted;

	@ManyToOne
	private User user;

	@ManyToOne
	private Topic topic;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parentTopicReply")
	private Set<TopicReply> childReplies = new HashSet<>();

	@ManyToOne
	private TopicReply parentTopicReply;

	public TopicReply()
	{
	}

	public Long getTopicReplyId()
	{
		return topicReplyId;
	}

	public void setTopicReplyId(Long topicReplyId)
	{
		this.topicReplyId = topicReplyId;
	}

	public Integer getFloor()
	{
		return floor;
	}

	public void setFloor(Integer floor)
	{
		this.floor = floor;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
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

	public Topic getTopic()
	{
		return topic;
	}

	public void setTopic(Topic topic)
	{
		this.topic = topic;
	}

	public Set<TopicReply> getChildReplies()
	{
		return childReplies;
	}

	public void setChildReplies(Set<TopicReply> childReplies)
	{
		this.childReplies = childReplies;
	}

	public TopicReply getParentTopicReply()
	{
		return parentTopicReply;
	}

	public void setParentTopicReply(TopicReply parentTopicReply)
	{
		this.parentTopicReply = parentTopicReply;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TopicReply that = (TopicReply) o;

		if (topicReplyId != null ? !topicReplyId.equals(that.topicReplyId) : that.topicReplyId != null) return false;
		if (floor != null ? !floor.equals(that.floor) : that.floor != null) return false;
		if (content != null ? !content.equals(that.content) : that.content != null) return false;
		return !(deleted != null ? !deleted.equals(that.deleted) : that.deleted != null);

	}

	@Override
	public int hashCode()
	{
		int result = topicReplyId != null ? topicReplyId.hashCode() : 0;
		result = 31 * result + (floor != null ? floor.hashCode() : 0);
		result = 31 * result + (content != null ? content.hashCode() : 0);
		result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
		return result;
	}
}
