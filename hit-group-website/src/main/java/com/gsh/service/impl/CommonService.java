package com.gsh.service.impl;

import com.gsh.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "commonService")
public class CommonService
{
	@Autowired
	private ChatDAO chatDAO;
	@Autowired
	private NewsDAO newsDAO;
	@Autowired
	private NewsReplyDAO newsReplyDAO;
	@Autowired
	private NoticeDAO noticeDAO;
	@Autowired
	private TopicDAO topicDAO;
	@Autowired
	private TopicReplyDAO topicReplyDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private FriendApplyDAO friendApplyDAO;
	@Autowired
	private PrivilegeDAO privilegeDAO;
	@Autowired
	private GroupDAO groupDAO;

	public ChatDAO getChatDAO()
	{
		return chatDAO;
	}

	public NewsDAO getNewsDAO()
	{
		return newsDAO;
	}

	public NewsReplyDAO getNewsReplyDAO()
	{
		return newsReplyDAO;
	}

	public NoticeDAO getNoticeDAO()
	{
		return noticeDAO;
	}

	public TopicDAO getTopicDAO()
	{
		return topicDAO;
	}

	public TopicReplyDAO getTopicReplyDAO()
	{
		return topicReplyDAO;
	}

	public UserDAO getUserDAO()
	{
		return userDAO;
	}

	public FriendApplyDAO getFriendApplyDAO()
	{
		return friendApplyDAO;
	}

	public PrivilegeDAO getPrivilegeDAO()
	{
		return privilegeDAO;
	}

	public GroupDAO getGroupDAO()
	{
		return groupDAO;
	}
}
