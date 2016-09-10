package com.gsh.web.forum.beans;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;

@Component
public class TopicReplyFormBean
{
	private int floor;

	@Size(min = 1, max = 5000, message = "您的回复字数不符合要求")
	private String content;

	public TopicReplyFormBean()
	{
	}

	public int getFloor()
	{
		return floor;
	}

	public void setFloor(int floor)
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
}
