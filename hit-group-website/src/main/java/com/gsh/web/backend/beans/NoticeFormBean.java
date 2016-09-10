package com.gsh.web.backend.beans;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;

@Component
public class NoticeFormBean
{
	@Size(min = 1, max = 100)
	private String content;

	public NoticeFormBean()
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
