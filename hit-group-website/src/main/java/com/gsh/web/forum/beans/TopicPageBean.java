package com.gsh.web.forum.beans;

import com.gsh.domain.Topic;
import com.gsh.web.util.PageBeanSupport;

import java.util.ArrayList;
import java.util.List;

public class TopicPageBean extends PageBeanSupport
{
	private List<Topic> topics = new ArrayList<>();

	public TopicPageBean()
	{
	}

	public List<Topic> getTopics()
	{
		return topics;
	}

}
