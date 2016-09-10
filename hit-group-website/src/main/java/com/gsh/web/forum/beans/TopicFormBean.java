package com.gsh.web.forum.beans;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;

@Component
public class TopicFormBean
{
	@Size(min = 1, max = 40, message = "您的主题帖标题字数不符合要求")
	private String title;

	@Size(min = 1, max = 5000, message = "您的主题帖内容字数不符合要求")
	private String content;

	public TopicFormBean()
	{
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
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
