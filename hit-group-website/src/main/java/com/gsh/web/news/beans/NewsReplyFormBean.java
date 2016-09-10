package com.gsh.web.news.beans;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;

@Component
public class NewsReplyFormBean
{
	@Size(min = 1, max = 100, message = "您输入的评论内容长度不符合要求")
	private String content;

	public NewsReplyFormBean()
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
