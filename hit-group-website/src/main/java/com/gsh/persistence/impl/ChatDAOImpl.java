package com.gsh.persistence.impl;

import com.gsh.domain.Chat;
import com.gsh.persistence.ChatDAO;
import org.springframework.stereotype.Repository;

@Repository(value = "chatDAO")
public class ChatDAOImpl extends CommonDAO implements ChatDAO
{

	@Override
	public void addChat(Chat chat)
	{
		this.getSession().save(chat);
	}

	@Override
	public Chat getChatById(Long chatId)
	{
		return (Chat)this.getSession().get(Chat.class, chatId);
	}
}
