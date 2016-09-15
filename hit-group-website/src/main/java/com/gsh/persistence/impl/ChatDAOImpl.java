package com.gsh.persistence.impl;

import com.gsh.domain.Chat;
import com.gsh.persistence.ChatDAO;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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

	@Override
	public List<Chat> queryChatsByUserId(Long userId)
	{
		String hql = "from Chat chat where deleted!=1 and chat.toUser.userId=:userId order by createTime desc";
		Query query = this.getSession().createQuery(hql);
		query.setLong("userId", userId);
		@SuppressWarnings("unchecked")
		List<Chat> chatList = query.list();
		return chatList;
	}
}
