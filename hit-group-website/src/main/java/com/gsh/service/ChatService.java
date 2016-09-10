package com.gsh.service;

import com.gsh.web.forum.beans.ChatFormBean;

public interface ChatService
{
	/**
	 * 发起私信
	 * @param chatFormBean 包含私信内容的FormBean
	 * @param fromUserId 来源用户ID
	 * @param toUserId 去向用户ID
	 */
	public void makeChat(ChatFormBean chatFormBean, Long fromUserId, Long toUserId);

	/**
	 * 将私信标为已读
	 * @param chatId 私信ID
	 */
	public void readChat(Long chatId);

	/**
	 * 将私信标为已删除
	 * @param chatId 私信ID
	 */
	public void deleteChat(Long chatId);


}
