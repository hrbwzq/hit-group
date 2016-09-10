package com.gsh.service.impl;

import com.gsh.persistence.*;
import org.springframework.stereotype.Service;

@Service
public class CommonService
{
	private ChatDAO chatDAO;

	private NewsDAO newsDAO;

	private NewsReplyDAO newsReplyDAO;

	private NoticeDAO noticeDAO;

	private TopicDAO topicDAO;

	private TopicReplyDAO topicReplyDAO;

	private UserDAO userDAO;

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
}
