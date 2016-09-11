package com.gsh.web.forum.beans;

import com.gsh.domain.TopicReply;
import com.gsh.web.util.PageBeanSupport;

import java.util.ArrayList;
import java.util.List;

public class TopicReplyPageBean extends PageBeanSupport
{
	private List<TopicReply> topicReplyList = new ArrayList<>();

	public TopicReplyPageBean()
	{
	}

	public List<TopicReply> getTopicReplyList()
	{
		return topicReplyList;
	}
}
