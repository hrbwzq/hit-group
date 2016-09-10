package com.gsh.persistence;

import com.gsh.domain.NewsReply;

public interface NewsReplyDAO
{
	/**
	 * 添加一条新闻评论
	 * @param newsReply 新闻评论对象
	 */
	public void addNewsReply(NewsReply newsReply);
}
