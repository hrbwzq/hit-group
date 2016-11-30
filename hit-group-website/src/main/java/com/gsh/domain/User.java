package com.gsh.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 注意
 * watchers:关注我的人集合
 * watchedRef 我已经关注的人集合
 */
@Entity
@Table(name = "t_user")
public class User implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "sex")
	private String sex;

	@Column(name = "exp", nullable = false)
	private Integer exp;

	@Column(name = "membered", nullable = false)
	private Integer membered;

	@Column(name = "thumbnail")
	private String thumbnail;

	@Column(name = "realname")
	private String realName;

	@Column(name = "age")
	private Integer age;

	@Column(name = "major")
	private String major;

	@Column(name = "address")
	private String address;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "qq")
	private String qq;

	@Column(name = "phone")
	private String phone;

	@Column(name = "watched", nullable = false)
	private Integer watched;

	@Column(name = "banned", nullable = false)
	private Integer banned;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private Set<NewsReply> newsReplies = new HashSet<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "t_user_privilege",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "privilege_id")}
	)
	private Set<Privilege> privileges = new HashSet<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "t_user_group",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "group_id")}
	)
	private Set<Group> groups = new HashSet<>();

	@ManyToMany(mappedBy = "friends")
	private Set<User> friendsRef = new HashSet<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "t_friends",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "friend_id")}
	)
	private Set<User> friends = new HashSet<>();

	@ManyToMany(mappedBy = "watchers")
	private Set<User> watchersRef = new HashSet<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "t_watchers",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "watcher_id")}
	)
	private Set<User> watchers = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fromUser")
	private Set<Chat> sentChats = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "toUser")
	private Set<Chat> recievedChats = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Topic> topics = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private Set<TopicReply> topicReplies = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Notice> notices = new HashSet<>();

	public User()
	{
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public Integer getExp()
	{
		return exp;
	}

	public void setExp(Integer exp)
	{
		this.exp = exp;
	}

	public Integer getMembered()
	{
		return membered;
	}

	public void setMembered(Integer membered)
	{
		this.membered = membered;
	}

	public String getThumbnail()
	{
		return thumbnail;
	}

	public void setThumbnail(String thumbnail)
	{
		this.thumbnail = thumbnail;
	}

	public String getRealName()
	{
		return realName;
	}

	public void setRealName(String realName)
	{
		this.realName = realName;
	}

	public Integer getAge()
	{
		return age;
	}

	public void setAge(Integer age)
	{
		this.age = age;
	}

	public String getMajor()
	{
		return major;
	}

	public void setMajor(String major)
	{
		this.major = major;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getQq()
	{
		return qq;
	}

	public void setQq(String qq)
	{
		this.qq = qq;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public Integer getWatched()
	{
		return watched;
	}

	public void setWatched(Integer watched)
	{
		this.watched = watched;
	}

	public Integer getBanned()
	{
		return banned;
	}

	public void setBanned(Integer banned)
	{
		this.banned = banned;
	}

	public Set<NewsReply> getNewsReplies()
	{
		return newsReplies;
	}

	public void setNewsReplies(Set<NewsReply> newsReplies)
	{
		this.newsReplies = newsReplies;
	}

	public Set<Privilege> getPrivileges()
	{
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges)
	{
		this.privileges = privileges;
	}

	public Set<User> getFriendsRef()
	{
		return friendsRef;
	}

	public void setFriendsRef(Set<User> friendsRef)
	{
		this.friendsRef = friendsRef;
	}

	public Set<User> getFriends()
	{
		return friends;
	}

	public void setFriends(Set<User> friends)
	{
		this.friends = friends;
	}

	public Set<User> getWatchersRef()
	{
		return watchersRef;
	}

	public void setWatchersRef(Set<User> watchersRef)
	{
		this.watchersRef = watchersRef;
	}

	public Set<User> getWatchers()
	{
		return watchers;
	}

	public void setWatchers(Set<User> watchers)
	{
		this.watchers = watchers;
	}

	public Set<Chat> getSentChats()
	{
		return sentChats;
	}

	public void setSentChats(Set<Chat> sentChats)
	{
		this.sentChats = sentChats;
	}

	public Set<Chat> getRecievedChats()
	{
		return recievedChats;
	}

	public void setRecievedChats(Set<Chat> recievedChats)
	{
		this.recievedChats = recievedChats;
	}

	public Set<Topic> getTopics()
	{
		return topics;
	}

	public void setTopics(Set<Topic> topics)
	{
		this.topics = topics;
	}

	public Set<TopicReply> getTopicReplies()
	{
		return topicReplies;
	}

	public void setTopicReplies(Set<TopicReply> topicReplies)
	{
		this.topicReplies = topicReplies;
	}

	public Set<Notice> getNotices()
	{
		return notices;
	}

	public void setNotices(Set<Notice> notices)
	{
		this.notices = notices;
	}

	public Set<Group> getGroups()
	{
		return groups;
	}

	public void setGroups(Set<Group> groups)
	{
		this.groups = groups;
	}

}
