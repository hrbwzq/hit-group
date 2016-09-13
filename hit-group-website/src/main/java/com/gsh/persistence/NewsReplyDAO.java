package com.gsh.persistence;

import com.gsh.domain.NewsReply;

import java.util.List;

public interface NewsReplyDAO
{
	/**
	 * 添加一条新闻评论
	 * @param newsReply 新闻评论对象
	 */
	public void addNewsReply(NewsReply newsReply);

	/**
	 * 通过新闻ID获得所有评论
	 * @param newsId 新闻ID
	 * @return 包含新闻评论的列表,可能为空列表
	 */
	public List<NewsReply> getNewsReplyByNewsId(Long newsId);
}
