package com.gsh.service.impl;

import com.gsh.domain.Chat;
import com.gsh.service.ChatService;
import com.gsh.web.forum.beans.ChatFormBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class ChatServiceImpl extends CommonService implements ChatService
{

	@Override
	public void makeChat(ChatFormBean chatFormBean, Long fromUserId, Long toUserId)
	{
		Chat chat = new Chat();
		chat.setCreateTime(new Date());
		chat.setContent(chatFormBean.getContent());
		chat.setDeleted(0);
		chat.setFromUser(getUserDAO().getUserById(fromUserId));
		chat.setToUser(getUserDAO().getUserById(toUserId));
		chat.setRead(0);
		getChatDAO().addChat(chat);
	}

	@Override
	public void readChat(Long chatId)
	{
		Chat chat = getChatDAO().getChatById(chatId);
		if(chat != null)
		{
			chat.setRead(1);
		}
	}

	@Override
	public void deleteChat(Long chatId)
	{
		Chat chat = getChatDAO().getChatById(chatId);
		if(chat != null)
		{
			chat.setDeleted(1);
		}
	}

	@Override
	public Chat querySingleChat(Long chatId)
	{
		return getChatDAO().getChatById(chatId);
	}
}
