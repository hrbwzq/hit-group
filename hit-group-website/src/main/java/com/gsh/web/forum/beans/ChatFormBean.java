package com.gsh.web.forum.beans;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;

@Component
public class ChatFormBean
{
	@Size(min = 1, max = 100)
	private String content;

	public ChatFormBean()
	{
	}

	public ChatFormBean(String content)
	{
		this.content = content;
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
