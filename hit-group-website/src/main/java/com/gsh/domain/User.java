package com.gsh.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

	@Column(name = "email")
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

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
		if (username != null ? !username.equals(user.username) : user.username != null) return false;
		if (password != null ? !password.equals(user.password) : user.password != null) return false;
		if (sex != null ? !sex.equals(user.sex) : user.sex != null) return false;
		if (exp != null ? !exp.equals(user.exp) : user.exp != null) return false;
		if (membered != null ? !membered.equals(user.membered) : user.membered != null) return false;
		if (thumbnail != null ? !thumbnail.equals(user.thumbnail) : user.thumbnail != null) return false;
		if (realName != null ? !realName.equals(user.realName) : user.realName != null) return false;
		if (age != null ? !age.equals(user.age) : user.age != null) return false;
		if (major != null ? !major.equals(user.major) : user.major != null) return false;
		if (address != null ? !address.equals(user.address) : user.address != null) return false;
		if (email != null ? !email.equals(user.email) : user.email != null) return false;
		if (qq != null ? !qq.equals(user.qq) : user.qq != null) return false;
		if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
		if (watched != null ? !watched.equals(user.watched) : user.watched != null) return false;
		return !(banned != null ? !banned.equals(user.banned) : user.banned != null);

	}

	@Override
	public int hashCode()
	{
		int result = userId != null ? userId.hashCode() : 0;
		result = 31 * result + (username != null ? username.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (sex != null ? sex.hashCode() : 0);
		result = 31 * result + (exp != null ? exp.hashCode() : 0);
		result = 31 * result + (membered != null ? membered.hashCode() : 0);
		result = 31 * result + (thumbnail != null ? thumbnail.hashCode() : 0);
		result = 31 * result + (realName != null ? realName.hashCode() : 0);
		result = 31 * result + (age != null ? age.hashCode() : 0);
		result = 31 * result + (major != null ? major.hashCode() : 0);
		result = 31 * result + (address != null ? address.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (qq != null ? qq.hashCode() : 0);
		result = 31 * result + (phone != null ? phone.hashCode() : 0);
		result = 31 * result + (watched != null ? watched.hashCode() : 0);
		result = 31 * result + (banned != null ? banned.hashCode() : 0);
		return result;
	}
}
