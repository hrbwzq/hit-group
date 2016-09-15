package com.gsh.persistence;

import com.gsh.domain.Chat;

import java.util.List;

public interface ChatDAO
{
	/**
	 * 保存一条私信
	 * @param chat 私信对象
	 */
	public void addChat(Chat chat);

	/**
	 * 根据ID查询指定私信
	 * @param chatId 私信ID
	 * @return 查询得到的私信对象,如果没有查到结果,返回null
	 */
	public Chat getChatById(Long chatId);

	/**
	 * 根据用户ID查询所有未删除的私信
	 * @param userId 用户ID
	 * @return 包含所有查询结果的列表
	 */
	public List<Chat> queryChatsByUserId(Long userId);

}
