package com.gsh.web.forum.beans;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;

@Component
public class TopicReplyFormBean
{
	@Size(min = 1, max = 5000, message = "您的回复字数不符合要求")
	private String content;

	public TopicReplyFormBean()
	{
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
